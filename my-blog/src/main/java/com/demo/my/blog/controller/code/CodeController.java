package com.demo.my.blog.controller.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.model.Code;
import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.model.CodeSubMenu;
import com.demo.my.base.model.User;
import com.demo.my.base.service.CodeMenuService;
import com.demo.my.base.service.CodeService;
import com.demo.my.base.service.CodeSubMenuService;
import com.demo.my.blog.controller.common.BaseController;

@Controller
@RequestMapping("/code")
public class CodeController extends BaseController {

	@Autowired
	private CodeService codeService;
	@Autowired
	private CodeSubMenuService codeSubMenuService;
	@Autowired
	private CodeMenuService codeMenuService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("code/index");
		List<CodeMenu> codeMenuList = codeService.excute("CodeMenuMapper.getBeanListByParm", null);
		mv.addObject("codeMenuList", codeMenuList);
		Map<String, Object> subMenuList = new HashMap<String, Object>();
		for(CodeMenu menu : codeMenuList){
			List<CodeSubMenu> codeSubMenus = getSubMenuList(menu.getId());
			subMenuList.put(menu.getId().toString() +","+ menu.getName(), codeSubMenus);	
		}
		mv.addObject("subMenuList", subMenuList);
		User user = getCurrentUser();
		if (user != null) {
			mv.addObject("isLogin", true);
		} else {
			mv.addObject("isLogin", false);
		}
		return mv;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public ModelAndView list(Long codeId, Long subMenuId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("code/list");
		
		Code code = new Code();
		List<Code> codeList = new ArrayList<Code>();
		Map<String, Object> parmMap = new HashMap<String, Object>();
		if (codeId != null) {
			code = codeService.getById(codeId);
			subMenuId = code.getFatherId();
			parmMap.put("fatherId", subMenuId);
			codeList = codeService.excute("CodeMapper.getBeanListByParm", parmMap);
		} else {
			parmMap.put("fatherId", subMenuId);
			codeList = codeService.excute("CodeMapper.getBeanListByParm", parmMap);
			if (codeList.size() != 0) {
				code = codeService.getById(codeList.get(0).getId());
			}
		}
		
		CodeSubMenu subMenu = new CodeSubMenu();
		subMenu = codeSubMenuService.getById(subMenuId);
		
		modelAndView.addObject("code", code);
		modelAndView.addObject("codeList", codeList);
		modelAndView.addObject("subMenu", subMenu);
		User user = getCurrentUser();
		if (user != null) {
			modelAndView.addObject("isLogin", true);
		} else {
			modelAndView.addObject("isLogin", false);
		}
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getMenuList", method=RequestMethod.GET)
	public Map<String, Object> getMenuList() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CodeMenu> codeMenuList = codeMenuService.excute("CodeMenuMapper.getBeanListByParm", null);
		resMap.put("list", codeMenuList);
		resMap.put("length", codeMenuList.size());
		return resMap;
	}
	
	public List<CodeSubMenu> getSubMenuList(Long fatherId) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("fatherId", fatherId);
		List<CodeSubMenu> codeMenuList = codeSubMenuService.excute("CodeSubMenuMapper.getBeanListByParm", parmMap);
		return codeMenuList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCodeById", method=RequestMethod.GET)
	public Map<String, Object> getCodeById(Long codeId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		Code code = codeService.getById(codeId);
		resMap.put("code", code);
		
		return resMap;
	}
	
	@RequestMapping(value = "/guide", method=RequestMethod.GET)
	public ModelAndView guide(Long codeId, Long subMenuId) throws JsonGenerationException, JsonMappingException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("code/guide");
		
		Code code = new Code();
		List<Code> codeList = new ArrayList<Code>();
		Map<String, Object> parmMap = new HashMap<String, Object>();
		if (codeId != null) {
			code = codeService.getById(codeId);
			subMenuId = code.getFatherId();
			parmMap.put("fatherId", subMenuId);
			parmMap.put("codeLevel", 1);
			codeList = codeService.excute("CodeMapper.getBeanListByParm", parmMap);
		} else {
			parmMap.put("fatherId", subMenuId);
			codeList = codeService.excute("CodeMapper.getBeanListByParm", parmMap);
			if (codeList.size() != 0) {
				code = codeService.getById(codeList.get(0).getId());
			}
		}
		
		//�˵���
		if(!codeList.isEmpty()){
			List<Map<String, Object>> menuTreeData = getMenuTreeData(codeList);
			ObjectMapper objectMapper = new ObjectMapper();
			modelAndView.addObject("menuTreeData", objectMapper.writeValueAsString(menuTreeData));
		}
		
		CodeSubMenu subMenu = new CodeSubMenu();
		subMenu = codeSubMenuService.getById(subMenuId);
		
		modelAndView.addObject("code", code);
		/*modelAndView.addObject("codeList", codeList);*/
		modelAndView.addObject("subMenu", subMenu);
		User user = getCurrentUser();
		if (user != null) {
			modelAndView.addObject("isLogin", true);
		} else {
			modelAndView.addObject("isLogin", false);
		}
		return modelAndView;
	}
	private List<Map<String, Object>> getMenuTreeData(List<Code> codeList){
		List<Map<String, Object>> menuTreeData = new ArrayList<Map<String,Object>>();
		for(Code c : codeList){
			Map<String, Object> menuTree = new HashMap<String, Object>();
			menuTree.put("name", c.getItem());
			menuTree.put("id", c.getId());
			menuTree.put("url", "/code/guide?codeId="+c.getId());
			if(c.getCodeId()==null){
				Map<String, Object> parmMap = new HashMap<String, Object>();
				parmMap.put("codeId", c.getId());
				List<Code> codes = codeService.excute("CodeMapper.getBeanListByParm", parmMap);
				if(!codes.isEmpty()){
					List<Map<String, Object>> subMenuTreeData = getMenuTreeData(codes);
					menuTree.put("children", subMenuTreeData);	
				}
			}
			menuTreeData.add(menuTree);
		}
		return menuTreeData;
	}
}
