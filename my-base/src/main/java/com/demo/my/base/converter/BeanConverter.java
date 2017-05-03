package com.demo.my.base.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.my.base.util.DateUtil;

public class BeanConverter {

	public List<Map<String, Object>> bean2Map(List<?> beanList, Map<String, Object> additionMap) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for(Object obj : beanList) {
			Map<String, Object> map = new HashMap<String, Object>();
			Class<?> objClass = obj.getClass();
			Method[] methods = objClass.getDeclaredMethods();
			for(Method method : methods){
				String methodName = method.getName();
				if(methodName.startsWith("get")){
					String key = methodName.substring(3, methodName.length());
					String firstWord = key.substring(0, 1).toLowerCase();
					key = firstWord + key.substring(1, key.length());
					Object value = method.invoke(obj);
					if(methodName.equals("getCreateTime") || methodName.equals("getUpdateTime")){
						value = DateUtil.dateToString((Date)value, DateUtil.DATETIME_FORMATE_2);
					}
					map.put(key, value);
				}
			}
			mapList.add(map);
		}
		if(additionMap!=null && !additionMap.isEmpty()){
			mapList.add(additionMap);	
		}
		return mapList;
	}
	
}
