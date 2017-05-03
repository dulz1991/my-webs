package com.demo.my.base.common;

public class ErrorConstant {

	public static String ERROR_NO = "errorNo";
	public static String ERROR_INFO = "errorInfo";
	
	public static Integer ERROR_200 = 200; //成功
	public static Integer ERROR_500 = 500; //错误，抛出错误信息，可能原因：客户端提交请求数据有误
	public static Integer ERROR_400 = 400; //错误，需要登录
	public static Integer ERROR_401 = 401; //错误，没有权限
	public static Integer ERROR_404 = 404; //错误，资源未找到

	//异常
	public static String ERROR_SYS_EXCEPTION = "系统异常，请稍后再试或联系管理员";
	public static String ERROR_UNKNOW_EXCEPTION = "未知错误，请稍后再试或联系管理员";
	//登陆
	public static String ERROR_EMPTY_USERNAME = "请输入用户名";
	public static String ERROR_EMPTY_PWD = "请输入密码";
	public static String ERROR_USERNAME_OR_PASSWORD_WRONG = "用户名或密码错误";
	//修改密码
	public static String ERROR_NEW_CFM_PWD_NOT_SAME = "修改的新密码和确认密码不匹配";
	public static String ERROR_PWD_MODIFY_SUCCESS = "密码修改成功";
	//保存博客
	public static String ERROR_EMPTY_TITLE = "请输入标题";
	public static String ERROR_EMPTY_MENU = "请选择菜单";
	public static String ERROR_EMPTY_CONTENT = "请输入内容";
	public static String ERROR_EMPTY_ID = "请选择删除的条目";
	//handbook
	public static String ERROR_EMPTY_SUB_MENU = "请选择子菜单";
	//move
	public static String ERROR_MOVE_FIRST = "第一个不能移动";
	public static String ERROR_MOVE_LAST = "最后一个不能移动";
	//菜单
	public static String ERROR_EMPTY_MENU_NAME = "请输入菜单名称";
	public static String ERROR_EMPTY_SUB_MENU_STATUS = "子菜单状态不能为空";
	//demo
	public static String ERROR_NO_RECORD = "没有查到该记录";
	public static String ERROR_EMPTY_DESCRIPTIPN = "请输入描述";
	//提示语
	public static String TIP_SUCCESS = "操作成功";
	public static String TIP_FAILED = "操作失败";
	public static String TIP_DELETE_SUCCESS = "删除成功";
	public static String TIP_MODIFY_SUCCESS = "修改成功";
	public static String TIP_SAVE_SUCCESS = "保存成功";
	//错误提示
	public static String TIP_NOT_EXIST = "记录未找到";
	public static String NOT_LOGIN = "请先登录";
	public static String FAILED_REFRESH = "刷新失败";
	
	
}
