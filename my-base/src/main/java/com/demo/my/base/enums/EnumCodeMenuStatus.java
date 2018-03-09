package com.demo.my.base.enums;

public enum EnumCodeMenuStatus {
	
	STOP(-1, "已停用"), 
	EDIT(1, "使用中"); 
	
	private int key;
	private String value;
    
    private EnumCodeMenuStatus(int key, String value){
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
		for (EnumCodeMenuStatus e : EnumCodeMenuStatus.values()) {
            if(e.getKey()==key){
            	return e.getValue();
            }
        }
		return "δ֪";
	}
}
