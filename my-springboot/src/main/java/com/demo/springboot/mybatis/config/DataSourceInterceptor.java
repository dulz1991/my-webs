package com.demo.springboot.mybatis.config;

public class DataSourceInterceptor {

	 public void setdataSourceOne() {  
		 DataSourceContextHolder.setCustomerType("dataSourceOne");  
	 }  
	      
	 public void setdataSourceTwo() {  
		 DataSourceContextHolder.setCustomerType("dataSourceTwo");  
	 }
	 
	 public void setdataSourceThree() {  
		 DataSourceContextHolder.setCustomerType("dataSourceThree");  
	 }
	 
}
