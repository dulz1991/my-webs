package com.demo.my.base.util;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	/**
     * 对象转json字符串
     * 
     * @param object
     * @return
     */
    public static String objectToJsonStr(Object object) {
    	try {
    		if (object != null) {
            	ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(object);
    		} else {
    			return null;
    		}	
		} catch (Exception e) {
			return null;
		}
    }
    
    /**
     * json字符串转对象
     * @param json
     * @param clazz
     * @return
     */
    public static Object jsonStrToObject(String json, Class<?> clazz) {
    	try {
    		if (json!=null) {
            	ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(json, clazz);
    		} else {
    			return null;
    		}	
		} catch (Exception e) {
			return null;
		}
    }
    
    @SuppressWarnings("unchecked")
	public static Map<String, Object> jsonStrToMap(String json) {
    	try {
    		if (json!=null) {
            	ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(json, new HashMap().getClass());
    		} else {
    			return null;
    		}	
		} catch (Exception e) {
			return null;
		}
    }
    
    
    public static void main(String[] args) {
    	/*User user = new User();
    	user.setAvatar("xxxx");
    	user.setId(1L);
    	String str= objectToJsonStr(user);
        System.out.println(str);*/
    	
    	/*String str = "{\"id\":1,\"username\":\"esferw\"}";
    	User user =  (User) jsonStjsonStrToObjectrToObj(str, new User().getClass());*/
    	
    	/*String str = "{\"id\":1,\"username\":\"esferw\"}";
    	Map<String, Object> map =jsonStrToMap(str);*/
    	
    }

    
}
