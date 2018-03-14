package com.demo.my.backend.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.User;
import com.demo.my.base.service.UserService;
import com.demo.my.base.service.file.ImageFileService;
import com.demo.my.base.util.ImageUtil;
import com.demo.my.base.util.JsonUtil;

@Controller
@RequestMapping("/backend/profile")
public class ProfileController extends BaseBackendController {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private ImageFileService imageFileService;

	//用户信息首页
	@RequestMapping(value="/index")
	public ModelAndView index(String userId) {
		ModelAndView mv = new ModelAndView("profile/index");
		
		if(StringUtils.isBlank(userId)){
			User user = this.getCurrentUser();
			mv.addObject("user", user);
			mv.addObject("isSelf", true);
		} else {
			User user = userService.getById(Long.valueOf(userId));
			mv.addObject("user", user);
			mv.addObject("isSelf", false);
		}
		
		return mv;
	}
	
	//修改用户状态
	@ResponseBody
	@RequestMapping(value="/changeUserStatus")
	public Map<String, Object> changeUserStatus(User user) {
		int i = userService.update(user);
		if(i>0){
			return responseOK("");
		} else {
			return responseGeneralError("更新数据库失败");
		}
	}
	
	//头像上传
	@ResponseBody
	@RequestMapping(value="/doUploadAvatar")
	public Map<String, Object> doUploadAvatar(String avatar_src,String avatar_data,Long hideUserId,
			@RequestParam(value = "avatar_file",required=false) MultipartFile avatar_file) {
		if(hideUserId==null){
			return responseGeneralError("上传失败：未获取到用户信息");
		}
		
		Map<String, Object> uploadMap = imageFileService.uploadUserImage(avatar_file);
		if(!(uploadMap.get(ErrorConstant.ERROR_NO)+"").equals("200")){
			return responseGeneralError("上传失败：头像保存失败");
		}
		
		try {
			String srcImagePath = uploadMap.get("uploadPath").toString();
			Map<String, Object> cutMap = JsonUtil.jsonStrToMap(avatar_data);
			// 用户经过剪辑后的图片的大小  
			int x = (int) cutMap.get("x");
			int y = (int) cutMap.get("y");
			int w =  (int) cutMap.get("width");
			int h =  (int) cutMap.get("height");
			//这里开始截取操作
			ImageUtil.cut(x,  y, w, h, srcImagePath);
			
			User user = userService.getById(hideUserId);
			user.setAvatar(uploadMap.get("savePath").toString());
			userService.update(user);
		} catch (Exception e) {
			return responseGeneralError("图片上传成功, 但裁剪失败");
		}
		
		return responseOK();
	}
	
}
