package com.demo.my.user.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.PicBlog;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.SysCfgServiceBean;

@Controller
@RequestMapping("/auth/sys")
public class SyscfgController extends BaseController {
	
	@Resource(name = "sysCfgServiceBean")
	private SysCfgServiceBean sysCfgService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/syscfg/syscfg_list");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/refresh", method=RequestMethod.GET)
	public HashMap<String, Object> refresh(@RequestParam(value="id", defaultValue="0")String id) {
		try {
			sysCfgService.callProcCount();	
			return responseOK("");
		} catch (Exception e) {
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.FAILED_REFRESH);
		}
		
		
	}
	
}
