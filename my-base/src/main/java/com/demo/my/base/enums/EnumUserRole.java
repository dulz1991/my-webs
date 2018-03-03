package com.demo.my.base.enums;

public enum EnumUserRole {
	
	ADMIN(1, "����Ա"), 
	REGISTER(2, "ע���û�");
	
	private int key;
	private String value;
    
    private EnumUserRole(int key, String value){
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
		for (EnumUserRole e : EnumUserRole.values()) {
            if(e.getKey()==key){
            	return e.getValue();
            }
        }
		return "δ֪";
	}
}
