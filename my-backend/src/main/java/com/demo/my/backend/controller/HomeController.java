package com.demo.my.backend.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.ueditor.ActionEnter;
import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.service.BlogService;
import com.demo.my.base.service.CodeMenuService;
import com.demo.my.base.service.CodeService;
import com.demo.my.base.service.CodeSubMenuService;
import com.demo.my.base.service.DemoService;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.service.file.ImageFileService;
import com.demo.my.base.util.PropertiesUtil;


@Controller
public class HomeController extends BaseBackendController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private DemoService demoService;
	@Autowired
	private DiscoveryService discoveryService;
	@Autowired
	private ImageFileService imageFileService;
	
	@RequestMapping(value="/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		
		//查询数量
		modelAndView.addObject("userCount", userService.countByParm(null));
		modelAndView.addObject("codeCount", codeService.countByParm(null));
		modelAndView.addObject("blogCount", blogService.countByParm(new HashMap<String, Object>()));
		modelAndView.addObject("discoveryCount", discoveryService.countByParm(new HashMap<String, Object>()));
		modelAndView.addObject("demoCount", demoService.countByParm(null));
		
		//图片文件夹大小和数量
		modelAndView.addObject("userImg", imageFileService.getImgInfo(PropertiesUtil.get("pic_user_path_upload"), 0L, 0L));
		modelAndView.addObject("blogrImg", imageFileService.getImgInfo(PropertiesUtil.get("pic_blog_path_upload"), 0L, 0L));
		modelAndView.addObject("codeImg", imageFileService.getImgInfo(PropertiesUtil.get("pic_code_path_upload"), 0L, 0L));
		modelAndView.addObject("discoveryImg", imageFileService.getImgInfo(PropertiesUtil.get("pic_vue_path_upload"), 0L, 0L));
		
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = "/ueditor")
	public void ueditor() throws IOException {
		request.setCharacterEncoding( "utf-8" );
		response.setHeader("Content-Type" , "text/html");
		
		String rootPath = request.getRealPath("/");
		response.getWriter().write(new ActionEnter( request, rootPath ).exec());
		
	}
	

}
