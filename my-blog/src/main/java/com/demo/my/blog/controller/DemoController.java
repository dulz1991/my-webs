package com.demo.my.blog.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.model.Demo;
import com.demo.my.base.model.DemoMenu;
import com.demo.my.base.service.DemoMenuService;
import com.demo.my.base.service.DemoService;
import com.demo.my.base.service.file.BaseService;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{

	@Autowired
	private DemoService demoService;
	@Autowired
	private DemoMenuService demoMenuService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(Long menuId) throws UnsupportedEncodingException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("demo/demo_list");
		
		//菜单
		List<DemoMenu> demoMenus = demoMenuService.getList(null);
		modelAndView.addObject("demoMenus", demoMenus);
		
		//demo 列表
		Map<String, Object> parmMap = new HashMap<String, Object>();
		if(menuId!=null){
			parmMap.put("menuId", menuId);
		}
		parmMap.put("orderby", "updateTime desc");
		List<Demo> demos = demoService.getList(parmMap);
		modelAndView.addObject("demos", demos);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/viewDetail")
	public ModelAndView viewDetail(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("demo/demo_detail");
		
		Demo demo = demoService.getById(id);
		modelAndView.addObject("demo", demo);
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/downloadResource")
	public void downloadResource(Long id){
		if(id==null){
			return;
			/*return responseGeneralError("下载失败:参数异常");*/
		}
		Demo demo = demoService.getById(id);
		if(demo==null || StringUtils.isBlank(demo.getResourcePath())){
			/*return responseGeneralError("下载失败:资源不存在");*/
			return;
		}
		try {
			BaseService.downloadFile(response, demo.getResourcePath());
			demo.setDownloadTimes(demo.getDownloadTimes()+1);
			demoService.update(demo);
			/*return responseOK();*/
			return;
		} catch (Exception e) {
			/*return responseGeneralError("下载失败:文件异常");*/
			return;
		}
	}
	
	
}
