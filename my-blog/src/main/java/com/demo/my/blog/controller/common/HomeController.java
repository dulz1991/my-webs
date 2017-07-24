package com.demo.my.blog.controller.common;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.User;
import com.demo.my.blog.controller.common.BaseController;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		User user = getCurrentUser();
		if (user != null) {
			modelAndView.addObject("role", user.getRole());
			modelAndView.addObject("username", user.getUsername());
		} else {
			modelAndView.addObject("role", null);
		}
		return modelAndView;
	}
	
	/**
	 * 是否登陆
	 */
	@ResponseBody
	@RequestMapping(value="loginResult", method = RequestMethod.GET)
	public Map<String, Object> loginResult() {
		if(this.getCurrentUserId()!=null){
			return this.responseOK("已登录");
		}
		return this.responseError(ErrorConstant.ERROR_400, "未登录");
	}
	
	/**
	 * 404页面
	 */
	@ResponseBody
	@RequestMapping(value="404", method = RequestMethod.GET)
	public Map<String, Object> error404(ModelAndView model) {
		return this.responseError(ErrorConstant.ERROR_404, "Page not found!");
	}
	/**
	 * 500页面
	 */
	@ResponseBody
	@RequestMapping(value="500", method = RequestMethod.GET)
	public Map<String, Object> error500(ModelAndView model) {
		return this.responseError(ErrorConstant.ERROR_500, "Internal exception!");
	}
	
}
