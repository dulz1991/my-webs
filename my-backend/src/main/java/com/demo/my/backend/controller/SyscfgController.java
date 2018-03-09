package com.demo.my.backend.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.enums.EnumSysCfgFlag;
import com.demo.my.base.model.SysCfg;
import com.demo.my.base.service.SysCfgService;

@Controller
@RequestMapping("/backend/sys")
public class SyscfgController extends BaseBackendController {
	
	@Autowired
	private SysCfgService sysCfgService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(String active) {
		if(StringUtils.isBlank(active)){
			active = "0";
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("syscfg/index");
		
		if(active.equals(EnumSysCfgFlag.BLOG_SITE.getKey()+"")){
			SysCfg sysCfg = sysCfgService.getByKey(KeyConstant.BLOG_SLOGAN);
			if(sysCfg!=null){
				modelAndView.addObject(KeyConstant.BLOG_SLOGAN, sysCfg.getValue());	
			}
		} else if(active.equals(EnumSysCfgFlag.PUBLIC.getKey()+"")){
			
		} else if(active.equals(EnumSysCfgFlag.BACKEND.getKey()+"")){
			SysCfg sysCfg = sysCfgService.getByKey(KeyConstant.DEFAULT_CODE_SUB_MENU_ID);
			if(sysCfg!=null){
				modelAndView.addObject(KeyConstant.DEFAULT_CODE_SUB_MENU_ID, sysCfg.getValue());	
			}
		}
		
		modelAndView.addObject("active", active);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/doSave")
	public Map<String, Object> doSave(SysCfg sysCfg){
		if(StringUtils.isBlank(sysCfg.getKey())){
			return responseGeneralError("参数异常：key不存在");
		}
		
		int i = 0;
		SysCfg cfg = sysCfgService.getByKey(sysCfg.getKey());
		if(cfg!=null){
			cfg.setValue(sysCfg.getValue());
			i = sysCfgService.update(cfg);
		} else {
			i = sysCfgService.insert(sysCfg);
		}
		
		if(i>0){
			return responseOK("");	
		} else {
			return responseGeneralError("修改失败");
		}
	}
	

}
