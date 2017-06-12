package com.demo.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.Discovery;
import com.demo.my.base.model.User;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.Base64Util;
import com.demo.my.base.util.MD5Util;
import com.demo.my.base.util.Page;

@RestController
@RequestMapping(value="user")
public class UserCenterController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DiscoveryService discoveryService;
	
	/**
	 * 首页
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="index", method = RequestMethod.GET)
	public Map<String, Object> index() {
		Map<String, Object> resMap = this.responseOK("");
		
		String username = request.getParameter(KeyConstant.COOKIE_USER);
		username = Base64Util.decodeBase64(username);
		User user = userService.getByUsername(username);
		
		resMap.put("avatar", user.getAvatar());
		resMap.put("username", user.getUsername());
		
		Discovery discovery = new Discovery();
		discovery.setUserId(user.getId());
		int count = discoveryService.countByParm(discovery);
		resMap.put("myPostCount", count);
		
		resMap.put("myCollectionCount", 0);
		
		return resMap;
	}
	
	/**
	 *  我发布的
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="myPost", method = RequestMethod.GET)
	public Map<String, Object> myPost(int pageNo) {
		Map<String, Object> resMap = this.responseOK("");
		int pageSize = 10;
		
		Long userId = this.getUserIdFromCookie();
		Discovery discovery = new Discovery();
		discovery.setUserId(userId);
		Page<Discovery> page = discoveryService.getBeanListByParm(discovery, pageNo, pageSize, "id desc");
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="doChangepwd", method = RequestMethod.POST)
	public Map<String, Object> doChangepwd(String oldPwd, String newPwd) {
		Long userId = getUserIdFromCookie();
		User user = userService.getById(userId);
		MD5Util md5 = new MD5Util(MD5Util.default_salt, "MD5");
		if(!user.getPassword().equals(md5.encode(oldPwd))){
			return responseError(ErrorConstant.ERROR_GENERAL, "修改失败：旧密码错误");
		}
		
		newPwd = md5.encode(newPwd);
		user.setPassword(newPwd);
		userService.update(user);
		
		return this.responseOK("修改成功");
	}
	
}
