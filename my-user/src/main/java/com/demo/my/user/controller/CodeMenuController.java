package com.demo.my.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.CodeMenu;
import com.demo.my.base.bean.CodeSubMenu;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.CodeServiceBean;

@Controller
@RequestMapping("/auth/codemenu")
public class CodeMenuController extends BaseController {

	@Resource(name = "codeServiceBean")
	private CodeServiceBean codeMenuService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView users(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("code/code_menu");
		return mv;
	}
	
	//menu
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public Map<String, Object> getCodeMenulist() {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		List<CodeMenu> list = codeMenuService.getBeanListByParm(null, null, KeyConstant.MAPPER_CODE_MENU);
		resMap.put("list", list);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubMenuByFatherId", method=RequestMethod.GET)
	public Map<String, Object> getSubMenuByFatherId(Long fatherId) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		HashMap<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("fatherId", fatherId);
		List<CodeSubMenu> list = codeMenuService.getBeanListByParm(null, parmMap, KeyConstant.MAPPER_CODE_SUB_MENU);
		resMap.put("list", list);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveMenu", method=RequestMethod.POST)
	public Map<String, Object> saveMenu(CodeMenu codeMenu) {
		if(StringUtils.isBlank(codeMenu.getName())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_MENU_NAME);
		}
		if (codeMenu.getId() != null && codeMenu.getId() != 0L) {
			CodeMenu cm = codeMenuService.getById(codeMenu.getId(), KeyConstant.MAPPER_CODE_MENU);
			if(StringUtils.isNotBlank(codeMenu.getName())){
				cm.setName(codeMenu.getName());	
			}
			codeMenuService.update(cm, KeyConstant.MAPPER_CODE_MENU);
		} else {
			codeMenuService.insert(codeMenu, KeyConstant.MAPPER_CODE_MENU);
		}
		return responseOK("");
	}
	
	@ResponseBody
	@RequestMapping(value = "/doMenuMove", method=RequestMethod.GET)
	public Map<String, Object> doMenuMove(Long menuId, String type) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap = codeMenuService.doMenuMove(menuId, type);
		return resMap;
	}
	
	
	
	//sub menu
	@ResponseBody
	@RequestMapping(value = "/saveSubMenu", method=RequestMethod.POST)
	public Map<String, Object> saveSubMenu(CodeSubMenu subMenu) {
		if(StringUtils.isBlank(subMenu.getName())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_MENU_NAME);
		}
		if(subMenu.getStatus()==-1){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_SUB_MENU_STATUS);
		}
		if (subMenu.getId() != null && subMenu.getId() != 0L) {
			CodeSubMenu csm = codeMenuService.getById(subMenu.getId(), KeyConstant.MAPPER_CODE_SUB_MENU);
			csm.setName(subMenu.getName());
			csm.setStatus(subMenu.getStatus());
			codeMenuService.update(csm, KeyConstant.MAPPER_CODE_SUB_MENU);
		} else {
			codeMenuService.insert(subMenu, KeyConstant.MAPPER_CODE_SUB_MENU);
		}
		return responseOK("");
	}
	
}
