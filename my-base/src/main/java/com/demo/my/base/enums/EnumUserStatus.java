package com.demo.my.base.enums;

public enum EnumUserStatus {
	
	NORMAL(1, "正常"), 
	FORBIDDEN(2, "禁用");
	
	private int key;
	private String value;
    
    private EnumUserStatus(int key, String value){
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
		for (EnumUserStatus e : EnumUserStatus.values()) {
            if(e.getKey()==key){
            	return e.getValue();
            }
        }
		return "未知";
	}
}
