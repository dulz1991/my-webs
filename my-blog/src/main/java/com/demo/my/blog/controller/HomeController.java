package com.demo.my.blog.controller.common;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.User;
import com.demo.my.base.service.UserService;
import com.demo.my.blog.controller.common.BaseController;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private UserService userService;
	
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
		
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated() && subject.isRemembered()) {
            Object principal = subject.getPrincipal();
            if (null != principal) {
                User user = userService.getByUsername(String.valueOf(principal));
                String password = user.getPassword();
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), password);
                token.setRememberMe(true);
                subject.login(token);//登录
                user.setPassword(null);
                subject.getSession().setAttribute(KeyConstant.USER_INFO, user);
            }
        }
		
		User user = this.getCurrentUser();
		if(user!=null){
			Map<String, Object> resMap = responseOK("已登录");
			resMap.put("username", user.getUsername());
			resMap.put("avatar", user.getAvatar());
			return resMap;
		}
		return responseNoLogin();
	}
	
	/**
	 * 404页面
	 */
	@ResponseBody
	@RequestMapping(value="404", method = RequestMethod.GET)
	public Map<String, Object> error404(ModelAndView model) {
		return this.response404();
	}
	/**
	 * 500页面
	 */
	@ResponseBody
	@RequestMapping(value="500", method = RequestMethod.GET)
	public Map<String, Object> error500(ModelAndView model) {
		return responseGeneralError("Internal exception!");
	}
	
}
