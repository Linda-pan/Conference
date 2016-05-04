package com.elin4it.ssm.mybatis.pagination.dialect;



import com.elin4it.ssm.mybatis.pagination.Order;

import java.util.List;


public class Dialect {
	
    public boolean supportsLimit(){
    	return false;
    }

    public boolean supportsLimitOffset() {
    	return supportsLimit();
    }
   
    public String getLimitString(String sql, int offset, int limit) {
    	return getLimitString(sql,offset,Integer.toString(offset),limit,Integer.toString(limit));
    }
  
    public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit,String limitPlaceholder) {
    	throw new UnsupportedOperationException("paged queries not supported");
    }

    public String getCountString(String sql){
        return "select count(1) from (" + sql + ") tmp_count";
    }
  
    public String getSortString(String sql, List<Order> orders){
        if(orders == null || orders.isEmpty()){
            return sql;
        }

        StringBuffer sb = new StringBuffer();
        for(Order order : orders){
            if(order == null){
            	continue;            	
            }
            
            String orderString = order.toString();
        	if ((orderString==null) || (orderString.length()==0)){
        		continue;
        	} 
        	
        	if (sb.length()>0){
    			sb.append(", ");
    		}
    		sb.append(orderString);
        }
        
        if (sb.length()>0){
			return "select * from (" + sql + ") temp_order order by " + sb.toString();
		}        
        
        return sql;
    }
    
}
