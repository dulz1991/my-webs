package com.demo.my.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;

import com.demo.my.base.model.User;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserMapper;
import com.demo.my.base.service.common.AdapterService;
import com.demo.my.base.util.MD5Util;
import com.demo.my.base.util.RegularUtil;

@Component
public class LoginService extends AdapterService {
	
	@Autowired
	private UserMapper userMapper;

	public Map<String, Object> login(User user) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(user.getUsername())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_USERNAME);
		} 
		if(StringUtils.isBlank(user.getPassword())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_PWD);
		} else {
			MD5Util md5 = new MD5Util(MD5Util.default_salt, "MD5");
			user.setPassword(md5.encode(user.getPassword()));
		}
		User u = userMapper.findUserByUsernameAndPassword(user);
		if (u != null) {
			u.setPassword(null);
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(true);	
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_SYS_EXCEPTION);
			}
			if (currentUser.isAuthenticated()) {
				currentUser.getSession().setAttribute(KeyConstant.USER_INFO, u);
				SavedRequest savedRequest = getSavedRequest();
				if (savedRequest != null){
					String requestUrl = savedRequest.getRequestUrl();
					resMap.put("url", requestUrl);
				} else {
					if (u.getRole() == 1) {
						resMap.put("url", "/");
					} else if (u.getRole() == 2) {
						resMap.put("url", "/user");
					}	
				}
			} else {
				return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_UNKNOW_EXCEPTION);
			}
		} else {
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_USERNAME_OR_PASSWORD_WRONG);
		}
		resMap.put(ErrorConstant.ERROR_NO, ErrorConstant.ERROR_200);
		return resMap;
	}
	
	public Map<String, Object> logout() {
		Map<String, Object> resMap = responseOK("");
		Subject currentUser = SecurityUtils.getSubject();
		User u = (User) currentUser.getSession().getAttribute(KeyConstant.USER_INFO);
		if(u==null){
			return resMap;
		}
		resMap.put("role", u.getRole());
		if (u.getRole() == 1) {
			resMap.put("url", "/");	
		} else if (u.getRole() == 2) {
		}
		currentUser.logout();
		return resMap;
	}
	
	public Map<String, Object> regist(User user) {
		Map<String, Object> resMap = responseOK("");
		
		if(StringUtils.isBlank(user.getUsername())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_USERNAME);
		} 
		if(StringUtils.isBlank(user.getPassword())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_PWD);
		} else if(!RegularUtil.isMatch(RegularUtil.REG_PASSWORD, user.getPassword())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_FORMATE_PWD);
		} else {
			MD5Util md5 = new MD5Util(MD5Util.default_salt, "MD5");
			user.setPassword(md5.encode(user.getPassword()));
		}
		
		User existUser = userMapper.getByUsername(user.getUsername());
		if(existUser!=null){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_USER_EXIST);
		}
		
		user.setRole(2L);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setStatus(1);
		user.setAvatar("/upload/img/user/user.png");
		userMapper.insert(user);
		
		return resMap;
	}
	
}
