package com.demo.my.base.util;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class HtmlUtil {

	/**
	 * 拼接select
	 * @param name
	 * @param value
	 * @param defaultValue
	 * @param data
	 * @param onclick
	 * @return
	 */
	public static String SelectHtml(String name,String value, String defaultValue, Map<Object, Object> data) {
		Set<Object> set = data.keySet();
		String selectHtml = "<select name='"+name+"' class='form-control'>";
		for(Object obj : set){
			if(StringUtils.isNotBlank(defaultValue)){
				selectHtml += "<option value=''>"+defaultValue+"</option>";
			}
			
			if(StringUtils.isNotBlank(value) && value.equals(obj+"")){
				selectHtml += "<option value='"+obj+"' selected>"+data.get(obj)+"</option>";
			} else {
				selectHtml += "<option value='"+obj+"'>"+data.get(obj)+"</option>";
			}
		}
		selectHtml += "</select>";
		return selectHtml;
	}
	
	/**
	 * option拼接
	 * @param value
	 * @param defaultValue
	 * @param data
	 * @return
	 */
	public static String OptionHtml(String value, String defaultValue, Map<Object, Object> data) {
		Set<Object> set = data.keySet();
		String selectHtml = "";
		for(Object obj : set){
			if(StringUtils.isNotBlank(defaultValue)){
				selectHtml += "<option value=''>"+defaultValue+"</option>";
			}
			
			if(StringUtils.isNotBlank(value) && value.equals(obj+"")){
				selectHtml += "<option value='"+obj+"' selected>"+data.get(obj)+"</option>";
			} else {
				selectHtml += "<option value='"+obj+"'>"+data.get(obj)+"</option>";
			}
			
			selectHtml += "</select>";
		}
		return selectHtml;
	}
}
