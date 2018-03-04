package com.demo.my.base.enums;

public enum EnumUserLogType {
	
	PWD_LOGIN(1, "密码登录"), 
	AUTO_LOGIN(2, "自动登录");
	
	private int key;
	private String value;
    
    private EnumUserLogType(int key, String value){
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
		for (EnumUserLogType e : EnumUserLogType.values()) {
            if(e.getKey()==key){
            	return e.getValue();
            }
        }
		return "δ֪";
	}
}
