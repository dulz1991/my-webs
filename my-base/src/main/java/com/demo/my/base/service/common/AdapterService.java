package com.demo.my.base.service.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.demo.my.base.common.BaseCommon;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.SpringContextUtil;

public class AdapterService extends BaseCommon {
	
	private static String BASE_MAPPER_PATH = "com.demo.my.base.mybatis.mapper.ds1mapper.";

	/*public <T> int insert(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int update(T t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	/**
	 * sample: super.getByParm("TProductMapper.xmlId", parmMap)
	 * @param mapperNameAndXmlId
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T excute(String mapperNameAndXmlId, Map<String, Object> paramMap) {
		if(StringUtils.isBlank(mapperNameAndXmlId)){
			return null;
		}
		String[] arr = mapperNameAndXmlId.split("\\.");
		if(arr==null || arr.length<2){
			return null;
		}
		if(paramMap==null){
			paramMap = new HashMap<String, Object>();
		}
		return  (T) invokeMapper(arr[0], arr[1], paramMap);
	}
	
	@SuppressWarnings("unchecked")
	private Object invokeMapper(String mapperName, String sqlId, Object... args){
		try {
			Class<?> mapperClass = Class.forName(BASE_MAPPER_PATH+mapperName); 
			if (BaseMapper.class.isAssignableFrom(mapperClass)) {
				for (Method mapperMethod : mapperClass.getMethods()) {
					if (mapperMethod.getName().equals(sqlId)) {
						if (args==null) {
							args = (Object[]) new Object();
						}
						Map<String, Object> map = (Map<String, Object>) SpringContextUtil.getBean(mapperClass);
						Object mapperInstance = map.get(mapperName.substring(0, 1).toLowerCase()+mapperName.subSequence(1, mapperName.length())); //获取bean实例
						return mapperMethod.invoke(mapperInstance, args);
					}
				}		
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
}
