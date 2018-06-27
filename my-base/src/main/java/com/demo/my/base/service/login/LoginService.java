package com.demo.my.base.service.login;

import java.util.Date;
import java.util.List;
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
import com.demo.my.base.model.UserLog;
import com.demo.my.base.common.BaseCommon;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.enums.EnumUserLogType;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserLogMapper;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserMapper;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserRoleMapper;
import com.demo.my.base.service.SysUserRoleService;
import com.demo.my.base.util.MD5Util;
import com.demo.my.base.util.RegularUtil;

@Component
public class LoginService extends BaseCommon {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserLogMapper userLogMapper;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	public Map<String, Object> login(User user) {
		Map<String, Object> resMap = responseOK();
		if(StringUtils.isBlank(user.getUsername())){
			return responseGeneralError(ErrorConstant.ERROR_EMPTY_USERNAME);
		} 
		if(StringUtils.isBlank(user.getPassword())){
			return responseGeneralError(ErrorConstant.ERROR_EMPTY_PWD);
		} else {
			MD5Util md5 = new MD5Util(MD5Util.default_salt, "MD5");
			user.setPassword(md5.encode(user.getPassword()));
		}
		User u = userMapper.findUserByUsernameAndPassword(user);
		if (u != null) {
			u.setPassword(null);
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(u.getUsername(), user.getPassword());
			token.setRememberMe(true);	
			try {
				List<String> roleCodeList = sysUserRoleService.getRoleCodeByUserId(u.getId());
				if(!roleCodeList.isEmpty()){
					u.setRoleCodeList(roleCodeList);
				}
				currentUser.login(token);
			} catch (AuthenticationException e) {
				return loginResult(u.getId(), ErrorConstant.ERROR_GENERAL, "系统异常："+e.getMessage());
			}
			if (currentUser.isAuthenticated()) {
				currentUser.getSession().setAttribute(KeyConstant.USER_INFO, u);
				SavedRequest savedRequest = getSavedRequest();
				resMap = loginResult(u.getId(), ErrorConstant.ERROR_OK, "");
				if (savedRequest != null){
					String requestUrl = savedRequest.getRequestUrl();
					resMap.put("url", requestUrl);
				} else {
					resMap.put("url", "/");	
				}
			} else {
				return loginResult(u.getId(), ErrorConstant.ERROR_GENERAL, ErrorConstant.ERROR_UNKNOW_EXCEPTION);
			}
		} else {
			return responseGeneralError(ErrorConstant.ERROR_USERNAME_OR_PASSWORD_WRONG);
		}
		return resMap;
	}
	
	private Map<String, Object> loginResult(Long userId, Integer errorNo,  String errorInfo) {
		String logRemark = ""; 
		if(errorNo.equals(ErrorConstant.ERROR_OK)){
			logRemark = "密码登录成功";
		} else {
			logRemark = "密码登录失败";
		}
		UserLog userLog = new UserLog();
        userLog.setCreateTime(new Date());
        userLog.setRemark(logRemark);
        userLog.setUserId(userId);
        userLog.setType(EnumUserLogType.PWD_LOGIN.getKey());
        userLogMapper.insert(userLog);
		return responseError(errorNo, errorInfo);
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
			return responseError(ErrorConstant.ERROR_GENERAL, ErrorConstant.ERROR_EMPTY_USERNAME);
		} 
		if(StringUtils.isBlank(user.getPassword())){
			return responseError(ErrorConstant.ERROR_GENERAL, ErrorConstant.ERROR_EMPTY_PWD);
		} else if(!RegularUtil.isMatch(RegularUtil.REG_PASSWORD, user.getPassword())){
			return responseError(ErrorConstant.ERROR_GENERAL, ErrorConstant.ERROR_FORMATE_PWD);
		} else {
			MD5Util md5 = new MD5Util(MD5Util.default_salt, "MD5");
			user.setPassword(md5.encode(user.getPassword()));
		}
		
		User existUser = userMapper.getByUsername(user.getUsername());
		if(existUser!=null){
			return responseError(ErrorConstant.ERROR_GENERAL, ErrorConstant.ERROR_USER_EXIST);
		}
		existUser = userMapper.getByPhone(user.getPhone());
		if(existUser!=null){
			return responseError(ErrorConstant.ERROR_GENERAL, "用户不存在");
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
