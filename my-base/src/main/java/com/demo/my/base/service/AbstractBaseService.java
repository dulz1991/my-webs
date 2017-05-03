package com.demo.my.base.service;

import java.util.List;
import java.util.Map;

import com.demo.my.base.util.PageUtil;


public interface AbstractBaseService {
	/*新增*/
	<T> Integer insert(T entity, String mapperName);
	
	/*更新*/
	<T> Integer update(T entity, String mapperName);
	
	/*删除*/
	Integer delete(Long id, String mapperName);
	
	/*根据ID查询*/
	<T> T getById(Long id, String mapperName);
	
	/*查询数量*/
	Integer countByParm(Map<String, Object> parmMap, String mapperName);
	
	/*单表查询*/
	<T> List<T> getBeanListByParm(PageUtil pageUtil, Map<String, Object> parmMap, String mapperName);
	
	/*多表查询*/
	List<Map<String, Object>> getMapListByParm(PageUtil pageUtil, Map<String, Object> parmMap, String mapperName);
	
}
