package com.demo.springboot.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.User;
import com.demo.my.base.service.LoginService;
import com.demo.my.base.util.Base64Util;
import com.demo.my.base.util.CookieUtil;

@RestController
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doLogin", method=RequestMethod.POST)
	public Map<String, Object> doLogin(User user) {
		Map<String, Object> resMap = loginService.login(user);
		resMap.put("tip", "登录成功");
		resMap.put("username", user.getUsername());
		
		String userNameBase64 = Base64Util.encodeBase64(user.getUsername());
		resMap.put("userNameBase64", userNameBase64);

		/*CookieUtil cookieUtil = new CookieUtil(request, response, Integer.MAX_VALUE);
		cookieUtil.addCookie(KeyConstant.COOKIE_USER, userNameBase64);*/
		
		return resMap;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/doRegist", method=RequestMethod.POST)
	public Map<String, Object> doRegist(User user) {
		Map<String, Object> resMap = loginService.regist(user);
		resMap.put("tip", "注册成功, 请登录");
		return resMap;
	}
	
	/**
	 * logout
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public Map<String, Object> logout(HttpServletRequest request, User user) {
		return loginService.logout();
	}
	
}
