package com.demo.my.base.mybatis.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	/**
	 * 随机生成六位数验证码
	 * 
	 * @return
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;// (Math.random()*(999999-100000)+100000)
	}

	/**
	 * �?测字符串是否不为�?(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * �?测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数�?
	 * 
	 * @param str
	 *            字符�?
	 * @param splitRegex
	 *            分隔�?
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔�?(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符�?
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 写txt里的单行内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            写入的内�?
	 */
	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			// Pattern regex =
			// Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			// Pattern regex =
			// Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18([0-3]|[5-9])))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			// Pattern regex =
			// Pattern.compile("^((13[0,1,2,3,5,6,7,8,9]\\d{8}$)|(15[0,1,2,3,5,6,7,8,9]\\d{8}$)|(18[0,1,2,3,5,6,7,8,9]\\d{8}$)|(17[0,6,7,8]\\d{8}$)|(147\\d{8})$)");
			// Pattern regex =
			// Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?1[0|1|2|3|4|5|6|7|8|9]\\d{9}$");

			Pattern regex = Pattern.compile("^1(0|1|2|3|4|5|6|7|8|9)\\d{9}$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 验证固定电话
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkTelephone(String telephone) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^\\d{3,4}-\\d{7,8}$");
			Matcher matcher = regex.matcher(telephone);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 正则校验
	 * @param reg
	 * @param value
	 * @return
	 */
	public static boolean regularCheck(String reg, String value) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile(reg);
			Matcher matcher = regex.matcher(value);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 读取txt里的单行内容
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格�?
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正�?:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}

	/**
	 * 
	 * @Description: 剔除文本中的html标签
	 * @param content
	 *            剔除html标签之前的内�?
	 * @return 剔除html标签之后的内�?
	 * @Author: sgwu
	 * @Time:2016�?6�?24日上�?11:22:02
	 */
	public static String HtmlToText(String content) {
		String txtcontent = content.replaceAll("</?[^>]+>", ""); // 剔出<html>的标�?
		txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");// 去除字符串中的空�?,回车,换行�?,制表�?
		return txtcontent;
	}

	public static void main(String[] args) {
		// System.out.println(getRandomNum());
		// String contentString =
		// "您的《医疗机构执业许可证》校验已预约成功.<br>�?2016-06-16 星期�?8:00-9:00前去自贸区预约大厅办�?,预约大厅地址如下�?<br><p>大厅地址</p><p>姓名</p><p>电话</p>";
		// contentString = HtmlToText(contentString);
		// System.out.println(contentString);
		//
		/*boolean ff = checkMobileNumbers("13920045211,18202232442");*/
		
		boolean ff = checkTelephone("400-4918758");
		System.out.println(ff);
		/*String ff = filterNotMemberMobile("13920045211,18202232442");
		System.out.println(ff);*/
	}

	/**
	 * 验证手机号是否正�?
	 * @param mobileNumbers 多个手机号已英文逗号隔开
	 * @return
	 */
	public static boolean checkMobileNumbers(String mobileNumbers) {
		boolean flag = false;
		
		if(mobileNumbers != null && !mobileNumbers.isEmpty()){
			String mobileNumberArry[] = mobileNumbers.split(",");
			int count = 0;
			if(mobileNumberArry != null && mobileNumberArry.length > 0){
				for (String mobileNumber : mobileNumberArry) {
					Boolean isMobileNumber = checkMobileNumber(mobileNumber);
					if(isMobileNumber){
						count++;
					}
				}
				if(count == mobileNumberArry.length){
					flag = true;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 过滤非成员手机号
	 * @param mobileNumbers 手机号拼接符，多个手机号以英文�?�号分隔
	 * @return
	 */
	public static String filterNotMemberMobile(String mobileNumbers) {
		String memberMobiles = "";
		StringBuffer sb = new StringBuffer();
		if(mobileNumbers != null && !mobileNumbers.isEmpty()){
			String mobileNumberArry[] = mobileNumbers.split(",");
			if(mobileNumberArry != null && mobileNumberArry.length > 0){
				for (String mobileNumber : mobileNumberArry){
					if(storeMemberMobileList.contains(mobileNumber)){
						if(sb != null && sb.length() > 0){
							sb.append(",");
						}
						sb.append(mobileNumber);
					}
				}
			}
			
		}
		if(sb != null && sb.length() > 0){
			memberMobiles = sb.toString();
		}
		return memberMobiles;
	}
	
	/**
	 * 商城成员手机�?
	 */
	public static final List<String> storeMemberMobileList = new ArrayList<String>() {
        private static final long serialVersionUID = 1L;

        {
            add("15715721262");
            add("18757573224");
            add("18767126315");
            add("13838337262");
            add("18569543550");
            add("15988112282");
            add("18551535372");
            add("13675878246");
            add("13588089560");
            add("15158064977");
            add("18768120799");
            add("15510897739");
            add("18202232442");
            add("18698081822");
            add("13820120084");
            add("15122391665");
            add("13186978276");
            add("15858180961");
            add("13656818210");
        }
    };
	
}
