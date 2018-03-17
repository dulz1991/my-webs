package com.demo.my.base.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumCodeSubMenuStatus {
	
	STOP(-1, "已停用"),
	NOT_START(0, "未开始"), 
	EDIT(1, "编辑中"), 
	COMPLETE(2, "已完成");
	
	private int key;
	private String value;
    
    private EnumCodeSubMenuStatus(int key, String value){
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
		for (EnumCodeSubMenuStatus e : EnumCodeSubMenuStatus.values()) {
            if(e.getKey()==key){
            	return e.getValue();
            }
        }
		return "֪";
	}
	
	public static Map<Object, Object> getListForSelect() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (EnumCodeSubMenuStatus e : EnumCodeSubMenuStatus.values()) {
			map.put(e.getKey(), e.getValue());
        }
		return map;
	}
}
