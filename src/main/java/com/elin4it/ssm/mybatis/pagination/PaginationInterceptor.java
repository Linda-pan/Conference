package com.elin4it.ssm.mybatis.pagination;

import com.elin4it.ssm.mybatis.pagination.dialect.Dialect;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;


@Intercepts({@Signature(
		type= Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);
	
    static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

    static ExecutorService Pool;
    
    Dialect dialect;
	boolean async = false;
	
	public Object intercept(final Invocation invocation) throws Throwable {
		
        final Executor executor = (Executor) invocation.getTarget();
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds)queryArgs[ROWBOUNDS_INDEX];
        final PageBounds pageBounds = (rowBounds instanceof PageBounds) ? (PageBounds)rowBounds : null; 
        
        final int offset = rowBounds.getOffset();
        final int limit = rowBounds.getLimit();
        
        if (pageBounds==null){
        	if (offset == RowBounds.NO_ROW_OFFSET && limit == RowBounds.NO_ROW_LIMIT){
        		return invocation.proceed();
        	} else {
        		if (!dialect.supportsLimit() ){
            		return invocation.proceed();
            	}
        	}        	
        }        		
        
        final BoundSql boundSql = ms.getBoundSql(parameter);
        final StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
        if(bufferSql.lastIndexOf(";") == bufferSql.length()-1){
            bufferSql.deleteCharAt(bufferSql.length()-1);
        }
        String sql = bufferSql.toString();

        if((pageBounds!=null) && (pageBounds.getOrders() != null)){
            sql = dialect.getSortString(sql, pageBounds.getOrders());
        }       

        if(dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
           
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit);
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }
            queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        }

        queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms,boundSql,sql);
        
        if (pageBounds==null){
        	return invocation.proceed();
        } else {
        	 Future<List<?>> listFuture = call(new Callable<List<?>>() {
                 public List<?> call() throws Exception {
                     return (List<?>)invocation.proceed();
                 }
             }, async);
             
             Future<Integer> totalCountFutrue = call(new Callable<Integer>() {
                 public Integer call() throws Exception {
                     Integer totalCount = null;
                     Cache cache = ms.getCache();
                     if(cache != null && ms.isUseCache()){
                         CacheKey cacheKey = executor.createCacheKey(ms,parameter,new PageBounds(),copyFromBoundSql(ms,boundSql,bufferSql.toString()));
                         totalCount = (Integer)cache.getObject(cacheKey);
                         if(totalCount == null){
                         	totalCount = getTotalCount(bufferSql.toString(),ms,parameter,boundSql,dialect);
                             cache.putObject(cacheKey, totalCount);
                         }
                     }else{
                     	totalCount = getTotalCount(bufferSql.toString(),ms,parameter,boundSql,dialect);
                     }
                     return totalCount;
                 }
             }, async);
             
             List<?> lst = listFuture.get();
             
             Integer totalCount = totalCountFutrue.get();             
             PageList pageList = new PageList(
            		 pageBounds.getPageNo(), 
            		 pageBounds.getPageSize(), 
            		 (totalCount==null)? 0 : totalCount.intValue(),
            				 lst);
             pageBounds.setPageList(pageList);
             
             return lst;           
        }
	}
	
	private int getTotalCount(
			final String sql,
			final MappedStatement mappedStatement, final Object parameterObject,
			final BoundSql boundSql, Dialect dialect) throws SQLException {
		final String count_sql = dialect.getCountString(sql);
		logger.debug("Total count SQL [{}] ", count_sql);
		logger.debug("Total count Parameters: {} ", parameterObject);

		Connection connection = null;
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
			countStmt = connection.prepareStatement(count_sql);
			//Page SQL和Count SQL的参数是一样的，在绑定参数时可以使用一样的boundSql
			DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement,parameterObject,boundSql);
			handler.setParameters(countStmt);
			
			rs = countStmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			logger.debug("Total count: {}", count);
			return count;
		} finally {
			try {
				if (rs != null) {
				  rs.close();
				}
			} finally {
				try {
				  if (countStmt != null) {
				      countStmt.close();
				  }
				} finally {
				  if (connection != null && !connection.isClosed()) {
				      connection.close();
				  }
				}
			}
		}
	}

    private <T> Future<T> call(Callable<T> callable, boolean async){
        if(async){
             return Pool.submit(callable);
        }else{
            FutureTask<T> future = new FutureTask<T>(callable);
            future.run();
            return future;
        }
    }

    private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql, String sql){
        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
        return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
									  String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
		    String prop = mapping.getProperty();
		    if (boundSql.hasAdditionalParameter(prop)) {
		        newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
		    }
		}
		return newBoundSql;
	}
	
	//see: MapperBuilderAssistant
	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
		
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if(ms.getKeyProperties() != null && ms.getKeyProperties().length !=0){
            StringBuffer keyProperties = new StringBuffer();
            for(String keyProperty : ms.getKeyProperties()){
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length()-1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		
		//setStatementTimeout()
		builder.timeout(ms.getTimeout());
		
		//setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		
		//setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
	    
		//setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		
		return builder.build();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		
		String dialectClass = properties.getProperty("dialectClass");
		try {
            setDialect((Dialect) Class.forName(dialectClass).newInstance());
		} catch (Exception e) {
			throw new RuntimeException("cannot create dialect instance by dialectClass:"+dialectClass,e);
		}
		
		try{
	        setAsync(Boolean.valueOf(properties.getProperty("async", "false")));
		} catch (Exception e){
			setAsync(false);
		}
		
		try{			
			setPoolMaxSize(Integer.valueOf(properties.getProperty("poolMaxSize", "0")));
		} catch (Exception e){
			setPoolMaxSize(0);
		}
	}
	
	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
	
    public void setDialect(Dialect dialect) {
        //logger.debug("dialectClass: {} ", dialect.getClass().getName());
        this.dialect = dialect;
    }

    public void setAsync(boolean async) {
        //logger.debug("async: {} ", async);
        this.async = async;
    }

    public void setPoolMaxSize(int poolMaxSize) {	
        	
        if(poolMaxSize > 0){
            //logger.debug("poolMaxSize: {} ", poolMaxSize);
            Pool = Executors.newFixedThreadPool(poolMaxSize);
        }else{
            Pool = Executors.newCachedThreadPool();
        }		
    	
    }

}

