package com.demo.my.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	
	public static final String DATE_FORMATE_1 = "yyyyMMdd";
	public static final String DATE_FORMATE_2 = "yyyy-MM-dd";
	public static final String DATE_FORMATE_3 = "yyyy/MM/dd";
	public static final String TIME_FORMATE_1 = "HHmmss";
	public static final String TIME_FORMATE_2 = "HH:mm:ss";
	public static final String TIME_FORMATE_3 = "HHmm";
	public static final String TIME_FORMATE_4 = "HH:mm";
	public static final String TIME_FORMATE_5 = "mmss";
	public static final String TIME_FORMATE_6 = "mm:ss";
	public static final String DATETIME_FORMATE_1 = "yyyyMMddHHmmss";
	public static final String DATETIME_FORMATE_2 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_FORMATE_3 = "yyyy/MM/dd HH:mm:ss";

	/**
	 * String to Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String dateStr, String formate) {
		try {
			Date date = null;
			if (StringUtils.isNotBlank(dateStr)) {
				SimpleDateFormat dateFormatter = new SimpleDateFormat(formate);
				date = dateFormatter.parse(dateStr);
			}
			return date;
		} catch (ParseException e) {
			System.out.print(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Date to String
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date, String formate) {
		String dateStr;
		if (date != null) {
			try {
				SimpleDateFormat dateFormatter = new SimpleDateFormat(formate);
				dateStr = dateFormatter.format(date);
			} catch (Exception e) {
				dateStr = null;
			}
		} else {
			dateStr = null;
		}
		return dateStr;
	}
	
	/**
	 * 计算时间间隔
	 * @param date
	 * @return
	 */
	public static String calcDatetime(Date date) {
		Long currentTime = new Date().getTime();
		Long beforeTime = date.getTime();
		//计算秒
		Long diff = (currentTime-beforeTime)/1000;
		if(diff>=0 && diff<59){
			return (diff+1) + "秒钟前";
		} 
		//计算分钟
		diff = diff/60;
		if(diff>=0 && diff<59){
			return (diff+1) + "分钟前";
		} 
		//计算小时
		diff = diff/60;
		if(diff>=0 && diff<23){
			return (diff+1) + "小时前";
		}
		//计算天数
		diff = diff/24;
		if(diff>=0 && diff<29){
			return (diff+1) + "天前";
		}
		//计算月数
		diff = diff/30;
		if(diff>=0 && diff<11){
			return (diff+1) + "月前";
		}
		//计算年数
		diff = diff/12;
		return (diff+1) + "年前";
	}

}
