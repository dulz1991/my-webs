package com.demo.my.base.common;

import java.util.HashMap;
import java.util.Map;

public class ErrorConstant {

	public static String ERROR_NO = "errorNo";
	public static String ERROR_INFO = "errorInfo";
	
	public static Integer ERROR_OK = 0; //正确的业务处理结果
	public static Integer ERROR_400 = 400; //未登录
	public static Integer ERROR_404 = 404; //未找到资源
	public static Integer ERROR_GENERAL = -1; //ͬ通用错误

	//�쳣
	public static String ERROR_SYS_EXCEPTION = "系统异常";
	public static String ERROR_UNKNOW_EXCEPTION = "未知异常";
	//��½
	public static String ERROR_EMPTY_USERNAME = "用户名为空";
	public static String ERROR_EMPTY_PWD = "密码为空";
	public static String ERROR_FORMATE_PWD = "密码格式错误";
	public static String ERROR_USERNAME_OR_PASSWORD_WRONG = "用户名或密码错误";
	public static String ERROR_USER_EXIST = "用户名已存在";
	//�޸�����
	public static String ERROR_NEW_CFM_PWD_NOT_SAME = "新密码和确认密码不一致";
	public static String ERROR_PWD_MODIFY_SUCCESS = "密码修改成功";
	//���沩��
	public static String ERROR_EMPTY_TITLE = "标题为空";
	public static String ERROR_EMPTY_MENU = "未选择菜单";
	public static String ERROR_EMPTY_CONTENT = "内容为空";
	public static String ERROR_EMPTY_ID = "ID为空";
	//handbook
	public static String ERROR_EMPTY_SUB_MENU = "未选择子菜单";
	//move
	public static String ERROR_MOVE_FIRST = "上移错误";
	public static String ERROR_MOVE_LAST = "下移错误";
	//�˵�
	public static String ERROR_EMPTY_MENU_NAME = "菜单名称为空";
	public static String ERROR_EMPTY_SUB_MENU_STATUS = "子菜单状态为空";
	//demo
	public static String ERROR_NO_RECORD = "未找到记录";
	public static String ERROR_EMPTY_DESCRIPTIPN = "描述为空";
	//��ʾ��
	public static String TIP_SUCCESS = "操作成功";
	public static String TIP_FAILED = "操作失败";
	public static String TIP_DELETE_SUCCESS = "删除成功";
	public static String TIP_MODIFY_SUCCESS = "修改成功";
	public static String TIP_SAVE_SUCCESS = "保存成功";
	//������ʾ
	public static String TIP_NOT_EXIST = "不存在";
	public static String NOT_LOGIN = "未登录";
	public static String FAILED_REFRESH = "刷新失败";
	
	public static Map<String, Object> responseOK(){
		return responseError(ErrorConstant.ERROR_OK, "");
	}
	public static Map<String, Object> responseNoLogin(){
		return responseError(ErrorConstant.ERROR_400, "未登录");
	}
	public static Map<String, Object> response404(){
		return responseError(ErrorConstant.ERROR_404, "未找到资源");
	}
	public static Map<String, Object> responseOK(String result){
		return responseError(ErrorConstant.ERROR_OK, result);
	}
	public static Map<String, Object> responseGeneralError(String errorInfo){
		return responseError(ErrorConstant.ERROR_GENERAL, errorInfo);
	}
	public static Map<String, Object> responseError(Integer errorNo, String errorInfo){
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put(ErrorConstant.ERROR_NO, errorNo);
		resMap.put(ErrorConstant.ERROR_INFO, errorInfo);
		return resMap;
	}
}
