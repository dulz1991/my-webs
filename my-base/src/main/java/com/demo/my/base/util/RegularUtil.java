package com.demo.my.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {
	
	public final static String REG_PASSWORD = "^\\w*$";
	
	public static boolean isMatch(String reg, String value) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(value);
		return  m.matches();
	}
	
	/**
	 * 截取字符串
	 * @return
	 */
	public static String cutContent(String reg, String value) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(value);
		String result = "";
		if(m.find()){
			result = m.group();
		}
		return result;
	}
	
	 public static void main(String[] args) {
		 
	        String test="<img height='231' alt='装饰模式类图1' width='528' src='http://dl.iteye.com/upload/picture/pic/44707/d591f98f-6ebd-39a2-8450-0fc94a0ef4e1.jpg' style='border:0px;font-family:helvetica, tahoma, arial, sans-serif;font-size:14px;line-height:25.2px;background-color:#ffffff;' />";
	        String reg = "(?<=src=\")[\\S\\s]+?(?=\")";
	        String b=cutContent(reg, test);
	 
	        System.out.println(b);
	    }
	 
	 /**
	  * 过滤html标签，style，script 返回纯文本
	  * @param inputString
	  * @return
	  */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

}
