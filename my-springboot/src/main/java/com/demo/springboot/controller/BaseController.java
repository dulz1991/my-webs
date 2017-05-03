package com.demo.springboot.controller;

import java.util.HashMap;

public class BaseController {

	protected HashMap<String, Object> responseOK(String msg){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("errorNo", 0);
		resMap.put("msg", msg);
		return resMap;
	}
	protected HashMap<String, Object> responseError(Integer errorNO, String errorInfo){
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("errorNo", errorNO);
		resMap.put("errorInfo", errorInfo);
		return resMap;
	}
	
}
