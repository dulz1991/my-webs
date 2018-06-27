package com.demo.my.base.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumIsEnable {
	DISABLE(0, "屏蔽"), 
	ENABLE(1, "启用"); 
	
	private int key;
	private String value;
    
    private EnumIsEnable(int key, String value){
        this.setKey(key) ;
        this.setValue(value);
    }

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValueByKey(int key) {
		for (EnumIsEnable e : EnumIsEnable.values()) {
            if(e.getKey()==key){
            	return e.getValue();
            }
        }
		return "";
	}
	
	public static Map<Object, Object> getListForSelect() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (EnumIsEnable e : EnumIsEnable.values()) {
			map.put(e.getKey(), e.getValue());
        }
		return map;
	}
}
