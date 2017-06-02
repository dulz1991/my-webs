package com.demo.my.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesUtil {
	
	public static Properties props = new Properties();
	
	public static String PIC_MAX_SIZE = "pic_max_size";
	public static String PIC_MIN_SIZE = "pic_min_size";

	//pic
	public static String PIC_PIC_PATH = "pic_pic_path";
	//blog
	public static String PIC_BLOG_PATH = "pic_blog_path";
	//code
	public static String PIC_CODE_PATH = "pic_code_path";
	//user
	public static String PIC_USR_PATH = "pic_user_path";
	//demo
	public static String FILE_DEMO_PATHE = "file_demo_path";
	public static String FILE_DEMO_PIC_PATH = "file_demo_pic_path";
	public static String FILE_DEMO_URL_PATH = "file_demo_url_path";
	public static String FILE_DEMO_RESOURCE_PATH = "file_demo_resource_path";
     
    static {
        InputStream in = PropertiesUtil.class.getResourceAsStream("/properties.properties");
        try {
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static String get(String key) {
		try {
			if (StringUtils.isBlank(key)) {
				return "";
			} else {
				return (String) props.get(key);
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String get(String key, String defaultValue) {
		try {
			if (StringUtils.isBlank(key)) {
				return defaultValue;
			} else {
				return (String) props.get(key);
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Integer getInt(String key) {
		try {
			if (StringUtils.isBlank(key)) {
				return 0;
			} else {
				return (Integer) props.get(key);
			}
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static Integer getInt(String key, int defaultValue) {
		try {
			if (StringUtils.isBlank(key)) {
				return defaultValue;
			} else {
				return (Integer) props.get(key);
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Long getLong(String key) {
		try {
			if (StringUtils.isBlank(key)) {
				return 0L;
			} else {
				return (Long) props.get(key);
			}
		} catch (Exception e) {
			return 0L;
		}
	}
	
	public static Long getLong(String key, Long defaultValue) {
		try {
			if (StringUtils.isBlank(key)) {
				return defaultValue;
			} else {
				return (Long) props.get(key);
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}
	

}
