package com.demo.my.backend.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.service.DemoMenuService;
import com.demo.my.base.model.DemoMenu;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/demoMenu")
public class DemoMenuController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoMenuController.class);
	
	@Autowired
	private DemoMenuService demoMenuService;
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("demo/demo_menu_list");
		/*List<DemoMenu> demoMenuList = demoMenuService.getList(null);*/
		/*model.addObject("demoMenuList", JSONArray.toJSON(demoMenuList));*/
		model.addObject("demoMenuList", JSONArray.toJSON(demoMenuService.getForTree()));
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("demo/demo_menu_edit");
		if(id!=null){
			DemoMenu entity = demoMenuService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new DemoMenu());
		}
		model.addObject("demoMenuList", JSONArray.toJSON(demoMenuService.getForTree()));
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList() {
		Map<String, Object> parmMap = this.getParmMap();
		Page<DemoMenu> page = demoMenuService.getPage("DemoMenuMapper.getBeanListByParm", parmMap);

		Map<String, Object> resMap = responseOK();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(DemoMenu demoMenu) {
		demoMenuService.save(demoMenu);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = demoMenuService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
