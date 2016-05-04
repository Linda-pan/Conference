package com.elin4it.ssm.mybatis.pagination;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CleanupMybatisPaginationListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	
    	if(PaginationInterceptor.Pool != null)
    		PaginationInterceptor.Pool.shutdownNow();
    }
}
