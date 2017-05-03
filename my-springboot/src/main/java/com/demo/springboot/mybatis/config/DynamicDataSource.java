package com.demo.springboot.mybatis.config;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {  
	@Override  
    protected Object determineCurrentLookupKey() {  
        return DataSourceContextHolder.getCustomerType();  
    }

	public Logger getParentLogger() {
		// TODO Auto-generated method stub
		return null;
	}
}
