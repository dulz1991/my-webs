package com.demo.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/login")
public class LoginController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@ResponseBody
	@RequestMapping("/isLogin")
	public boolean isLogin(@PathVariable String name){
		return true;
	}
	
}
