package com.demo.my.backend.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.User;
import com.demo.my.base.ueditor.UeditorActionEnter;
import com.demo.my.base.util.MD5Util;
import com.demo.my.base.util.PropertiesUtil;

@Controller
public class HomeController extends BaseBackendController {
	
	@RequestMapping("/index")
	public ModelAndView index(String toUrl) {
		ModelAndView mw = new ModelAndView("index");
		mw.addObject("toUrl", toUrl);
		User user = (User) request.getAttribute("user");
		mw.addObject("user", user);
		mw.addObject("menus", sysMenuService.getByUserId(user.getId()));
		return mw;
	}
	
	@RequestMapping(value="/index_blank")
	public ModelAndView index_blank() {
		ModelAndView modelAndView = new ModelAndView();
		
		//查询数量
		modelAndView.addObject("userCount", userService.countByParm(null));
		modelAndView.addObject("codeCount", codeService.countByParm(null));
		modelAndView.addObject("blogCount", blogService.countByParm(new HashMap<String, Object>()));
		/*modelAndView.addObject("discoveryCount", discoveryService.countByParm(new HashMap<String, Object>()));*/
		modelAndView.addObject("demoCount", demoService.countByParm(null));
		
		//图片文件夹大小和数量
		modelAndView.addObject("userImg", imageFileService.getImgInfo(PropertiesUtil.get("pic_user_path_upload"), 0L, 0L));
		modelAndView.addObject("blogImg", imageFileService.getImgInfo(PropertiesUtil.get("pic_blog_path_upload"), 0L, 0L));
		modelAndView.addObject("codeImg", imageFileService.getImgInfo(PropertiesUtil.get("pic_code_path_upload"), 0L, 0L));
		/*modelAndView.addObject("discoveryImg", imageFileService.getImgInfo(PropertiesUtil.get("pic_vue_path_upload"), 0L, 0L));*/
		
		modelAndView.setViewName("index_blank");
		return modelAndView;
	}

	@RequestMapping(value = "/ueditor")
	public void ueditor() throws IOException {
		request.setCharacterEncoding( "utf-8" );
		response.setHeader("Content-Type" , "text/html");
		
		String rootPath = request.getRealPath("/");
		response.getWriter().write(new UeditorActionEnter( request, rootPath ).exec());
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value="/changePwd")
	public ModelAndView changePwd() {
		ModelAndView model = new ModelAndView("changePwd");
		return model;
	}
	@ResponseBody
	@RequestMapping(value="/doChangePwd")
	public Map<String, Object> doChangePwd(String newPwd, String rePwd, Long userId) {
		if(StringUtils.isBlank(newPwd)){
			return responseGeneralError("请输入新密码");
		} else if(newPwd.length()<6) {
			return responseGeneralError("新密码长度不能小于6位"); 
		}
		if(StringUtils.isBlank(rePwd)){
			return responseGeneralError("请输入确认密码");
		} else if(newPwd.length()<6) {
			return responseGeneralError("确认密码长度不能小于6位"); 
		}
		if(!newPwd.equals(rePwd)){
			return responseGeneralError("新密码与确认密码密码不一致，请重试");
		}
		User sysUser = new User();
		if(userId!=null){
			sysUser.setId(userId);
		} else {
			sysUser.setId(this.getCurrentUserId());	
		}
		MD5Util md5 = new MD5Util(MD5Util.default_salt, "MD5");
		sysUser.setPassword(md5.encode(newPwd));
		if(userService.update(sysUser)>0){
			return responseOK();
		} else {
			return responseGeneralError("修改密码失败，请重试");
		}
	}
	
	/**
	 * 选择头像页面
	 * @return
	 */
	@RequestMapping("/selectAvatar")
	public ModelAndView selectAvatar() {
		ModelAndView mw = new ModelAndView("selectAvatar");		
		User user = getCurrentUser();
		mw.addObject("avatar", user.getAvatar());
		return mw;
	}
	@ResponseBody
	@RequestMapping("/saveAvatar")
	public Map<String, Object> saveAvatar(String avatar) {
		if(StringUtils.isNotBlank(avatar)){
			Long userId = getCurrentUserId();
			User sysUser = new User();
			sysUser.setId(userId);
			sysUser.setAvatar(avatar);
			userService.update(sysUser);
			User sysUser1 = getCurrentUser();
			sysUser1.setAvatar(avatar);
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.getSession().setAttribute(KeyConstant.USER_INFO, sysUser1);
			return responseOK();
		} else {
			return responseGeneralError("请选择头像");
		}
	}

}
