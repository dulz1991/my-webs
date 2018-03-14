package com.demo.my.base.enums;

public enum EnumCodeStatus {
	
	DELETE(0, "删除"), 
	NORMAL(1, "正常"); 
	
	private int key;
	private String value;
    
    private EnumCodeStatus(int key, String value){
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
		for (EnumCodeStatus e : EnumCodeStatus.values()) {
            if(e.getKey()==key){
            	return e.getValue();
            }
        }
		return "δ֪";
	}
}
