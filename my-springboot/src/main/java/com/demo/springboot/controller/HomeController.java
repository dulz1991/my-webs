package com.demo.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Discovery;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.util.Page;


@RestController
public class HomeController extends BaseController {
	
	/**
	 * 404页面
	 */
	@RequestMapping(value="404", method = RequestMethod.GET)
	public Map<String, Object> error404(ModelAndView model) {
		return this.responseError(ErrorConstant.ERROR_404, "Page not found!");
	}
	/**
	 * 500页面
	 */
	@RequestMapping(value="500", method = RequestMethod.GET)
	public Map<String, Object> error500(ModelAndView model) {
		return this.responseError(ErrorConstant.ERROR_500, "Internal exception!");
	}
}
