package com.demo.my.base.service;

import java.util.List;
import java.util.Map;

import com.demo.my.base.util.PageUtil;


public interface AbstractBaseService {
	/*����*/
	<T> Integer insert(T entity, String mapperName);
	
	/*����*/
	<T> Integer update(T entity, String mapperName);
	
	/*ɾ��*/
	Integer delete(Long id, String mapperName);
	
	/*����ID��ѯ*/
	<T> T getById(Long id, String mapperName);
	
	/*��ѯ����*/
	Integer countByParm(Map<String, Object> parmMap, String mapperName);
	
	/*�����ѯ*/
	<T> List<T> getBeanListByParm(PageUtil pageUtil, Map<String, Object> parmMap, String mapperName);
	
	/*����ѯ*/
	List<Map<String, Object>> getMapListByParm(PageUtil pageUtil, Map<String, Object> parmMap, String mapperName);
	
}
