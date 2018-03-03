package com.demo.my.base.service.common;
/*package com.demo.my.base.service;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.util.SpringContextUtil;

public abstract class AbstractService extends DefaultBaseService implements AbstractBaseService {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractService.class);
	
	public <T> Integer insert(T entity) {
		Integer count = 0;
		try {
			count = (Integer) invokeMapper(this, "insert", entity);
			if (count == null) {
				count = 0;
			}
			log.info("invoke insert. param:{}, result:{}", entity, count);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}
	*//**
	 * 插入
	 *//*
	public <T> Integer insert(T entity, String mapperName) {
		Integer count = 0;
		try {
			count = (Integer) invokeMapper(this, mapperName, "insert", entity);
			if (count == null) {
				count = 0;
			}
			log.info("invoke insert. param:{}, result:{}", entity, count);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}

	public <T> Integer update(T entity) {
		Integer count = 0;
		try {
			count = (Integer) invokeMapper(this, "update", entity);
			if (count == null) {
				count = 0;
			}
			log.info("invoke update. param:{}, result:{}", entity, count);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}
	*//**
	 * 更新
	 *//*
	public <T> Integer update(T entity, String mapperName) {
		Integer count = 0;
		try {
			count = (Integer) invokeMapper(this, mapperName, "update", entity);
			if (count == null) {
				count = 0;
			}
			log.info("invoke update. param:{}, result:{}", entity, count);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}
	
	public Integer delete(Long id) {
		Integer count = 0;
		try {
			count = (Integer) invokeMapper(this, "delete", id);
			if (count == null) {
				count = 0;
			}
			log.info("invoke delete. param:{}, result:{}", id, count);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}
	*//**
	 * 删除
	 *//*
	public Integer delete(Long id, String mapperName) {
		Integer count = 0;
		try {
			count = (Integer) invokeMapper(this, mapperName, "delete", id);
			if (count == null) {
				count = 0;
			}
			log.info("invoke delete. param:{}, result:{}", id, count);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}

	*//**
	 * getById
	 *//*
	@SuppressWarnings("unchecked")
	public <T> T getById(Long id, String mapperName) {
		T t =  null;
		try {
			t = (T) invokeMapper(this, mapperName, "getById", id);
			if (t == null) {
				t = (T) new Object();
			}
			log.info("invoke getById. param:{}, result:{}", id, t);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return t;
	}

	*//**
	 * 获取bean列表
	 *//*
	@SuppressWarnings("unchecked")
	public <T> List<T> getBeanListByParm(PageUtil pageUtil, Map<String, Object> parmMap, String mapperName) {
		List<T> result = new ArrayList<T>();
		try {
			result = (List<T>) invokeMapper(this, mapperName, "getBeanListByParm", pageUtil, parmMap);
			if (result == null) {
				result = new ArrayList<T>();
			}
			log.info("invoke getBeanListByParm. param:{}, result:{}", parmMap, result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	*//**
	 * 获取map列表
	 *//*
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByParm(PageUtil pageUtil, Map<String, Object> parmMap, String mapperName) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = (List<Map<String, Object>>) invokeMapper(this, mapperName, "getMapListByParm", pageUtil, parmMap);
			if (result == null) {
				result = new ArrayList<Map<String,Object>>();
			}
			log.info("invoke getMapListByParm. param:{}, result:{}", parmMap, result);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	*//**
	 * 获取数量
	 *//*
	public Integer countByParm(Map<String, Object> parmMap, String mapperName) {
		Integer count = 0;
		try {
			count = (Integer) invokeMapper(this, mapperName, "countByParm", parmMap);
			if (count == null) {
				count = 0;
			}
			log.info("invoke getCountByParm. param:{}, result:{}", parmMap, count);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	private Object invokeMapper(Object object, String methodName, Object... args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IntrospectionException{
		Class<?> objClass = object.getClass();
		Field[] fields = objClass.getDeclaredFields();
		Method[] methods = objClass.getDeclaredMethods();
		for (Field field : fields) {
			String fieldTypeName = field.getType().getName();
			Class<?> mapperClass = Class.forName(fieldTypeName);
			if (BaseMapper.class.isAssignableFrom(mapperClass)) {
				Map<String, Object> map = (Map<String, Object>) SpringContextUtil.getBean(mapperClass);
				String key = fieldTypeName.substring(fieldTypeName.lastIndexOf(".")+1,fieldTypeName.length());
				Object mapperInstance = map.get(key.substring(0, 1).toLowerCase()+key.substring(1, key.length()));  
				for (Method mapperMethod : mapperClass.getDeclaredMethods()) {
					if (mapperMethod.getName().equals(methodName)) {
						return mapperMethod.invoke(mapperInstance, args);
					}
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private Object invokeMapper(Object object, String mapperName, String sqlId, Object... args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IntrospectionException{
		Class<?> objClass = object.getClass(); //service反射
		Field[] fields = objClass.getDeclaredFields(); //获取字段列表
		Method[] methods = objClass.getDeclaredMethods();
		for (Field field : fields) {
			String fieldName = field.getName(); //获取字段名
			if(fieldName.equals(mapperName)){
				String fieldTypeName = field.getType().getName(); //获取字段类型名
				Class<?> mapperClass = Class.forName(fieldTypeName); //获取该字段的反射
				if (BaseMapper.class.isAssignableFrom(mapperClass)) {//是否继承BaseMapper
					Map<String, Object> map = (Map<String, Object>) SpringContextUtil.getBean(mapperClass);
					Object mapperInstance = map.get(mapperName); //获取bean实例
					for (Method mapperMethod : mapperClass.getDeclaredMethods()) {
						if (mapperMethod.getName().equals(sqlId)) {
							if (args==null) {
								args = (Object[]) new Object();
							}
							return mapperMethod.invoke(mapperInstance, args);
						}
					}		
				}
			}
		}			
		return null;
	}

}
*/