package com.demo.my.backend.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.ueditor.ActionEnter;
import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.service.CodeMenuService;
import com.demo.my.base.service.CodeSubMenuService;


@Controller
@RequestMapping("/backend")
public class HomeController extends BaseBackendController {
	
	@Autowired
	private CodeMenuService codeMenuService;
	@Autowired
	private CodeSubMenuService codeSubMenuService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value = "/ueditor", method=RequestMethod.GET)
	public void ueditor() throws IOException {
		request.setCharacterEncoding( "utf-8" );
		response.setHeader("Content-Type" , "text/html");
		
		String rootPath = request.getRealPath("/");
		response.getWriter().write(new ActionEnter( request, rootPath ).exec());
		
	}
	

}
