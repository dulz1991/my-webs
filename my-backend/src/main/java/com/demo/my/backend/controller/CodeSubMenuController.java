package com.demo.my.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.service.CodeMenuService;
import com.demo.my.base.service.CodeSubMenuService;
import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.model.CodeSubMenu;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/codeSubMenu")
public class CodeSubMenuController extends BaseBackendController {
	
	private static final Log logger = LogFactory.getLog(CodeMenuController.class);
	
	@Autowired
	private CodeMenuService codeMenuService;
	@Autowired
	private CodeSubMenuService codeSubMenuService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index(String codeMenuId) {
		ModelAndView model = new ModelAndView("code/code_sub_menu_list");
		
		if(StringUtils.isNotBlank(codeMenuId)){
			model.addObject("codeMenuId", codeMenuId);
		}
		
		List<CodeMenu> codeMenuList = codeMenuService.excute("CodeMenuMapper.getBeanListByParm", null);
		model.addObject("codeMenuList", codeMenuList);
		
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("code/code_sub_menu_edit");
		List<CodeMenu> codeMenuList = codeMenuService.excute("CodeMenuMapper.getBeanListByParm", null);
		model.addObject("codeMenuList", codeMenuList);
		if(id!=null){
			CodeSubMenu entity = codeSubMenuService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new CodeSubMenu());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(CodeSubMenu codeSubMenu,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Page<Map<String, Object>> page = codeSubMenuService.getMapListByParm(codeSubMenu, pageNo, pageSize, "csm.`NAME` asc");

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(CodeSubMenu codeSubMenu) {
		codeSubMenuService.save(codeSubMenu);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = codeSubMenuService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

	@ResponseBody
	@RequestMapping(value = "/getCodeSubMenuListByFatherId", method=RequestMethod.GET)
	public Map<String, Object> getCodeSubMenuListByFatherId(Long id) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("fatherId", id);
		
		List<CodeSubMenu> codeSubMenuList = codeSubMenuService.excute("CodeSubMenuMapper.getBeanListByParm", resMap);
		resMap.put("codeSubMenuList", codeSubMenuList);
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		for(CodeSubMenu codeSubMenu : codeSubMenuList){
			Map<String, Object>  map = new HashMap<String, Object>();
			map.put("key", codeSubMenu.getId());
			map.put("value", codeSubMenu.getName());
			listMap.add(map);
		}
		resMap.put("list", listMap);
		
		resMap.put("errorNo", 200);
		return resMap; 
	}
	
}
