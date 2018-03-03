package com.demo.my.base.enums;

public enum EnumSysCfgFlag {
	
	BLOG_SITE(0, "博客网站首页"),
	EXCUTE(1, "任务执行"),
	PUBLIC(2, "公共");
	
	private int key;
	private String value;
    
    private EnumSysCfgFlag(int key, String value){
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
		for (EnumSysCfgFlag e : EnumSysCfgFlag.values()) {
            if(e.getKey()==key){
            	return e.getValue();
            }
        }
		return "未知";
	}
}
