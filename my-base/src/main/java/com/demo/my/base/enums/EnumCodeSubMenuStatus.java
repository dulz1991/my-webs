package com.demo.my.base.enums;

public enum EnumCodeSubMenuStatus {
	
	NOT_START(0, "δ��ʼ"), 
	EDIT(1, "�༭��"), 
	COMPLETE(2, "�����");
	
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
		return "δ֪";
	}
}