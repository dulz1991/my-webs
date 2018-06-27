package com.demo.my.base.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumRoleCode {
	ADMIN("ROLE001", "管理员"),
	FRONT("ROLE002", "前台"),
	TEACHER("ROLE003", "教师"); 
	
	private String key;
	private String value;
    
    private EnumRoleCode(String key, String value){
        this.setKey(key) ;
        this.setValue(value);
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValueByKey(String key) {
		for (EnumRoleCode e : EnumRoleCode.values()) {
            if(e.getKey().equals(key)){
            	return e.getValue();
            }
        }
		return "";
	}
	
	public static Map<Object, Object> getListForSelect() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (EnumRoleCode e : EnumRoleCode.values()) {
			map.put(e.getKey(), e.getValue());
        }
		return map;
	}
}
