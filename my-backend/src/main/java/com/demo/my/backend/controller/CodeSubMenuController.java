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
import com.demo.my.base.enums.EnumCodeMenuStatus;
import com.demo.my.base.enums.EnumCodeSubMenuStatus;
import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.model.CodeSubMenu;
import com.demo.my.base.util.HtmlUtil;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/codeSubMenu")
public class CodeSubMenuController extends BaseBackendController {
	
	private static final Log logger = LogFactory.getLog(CodeMenuController.class);
	
	@Autowired
	private CodeMenuService codeMenuService;
	@Autowired
	private CodeSubMenuService codeSubMenuService;
	
	@RequestMapping(value="/list")
	public ModelAndView index(String codeMenuId) {
		ModelAndView model = new ModelAndView("code/code_sub_menu_list");
		
		if(StringUtils.isNotBlank(codeMenuId)){
			model.addObject("codeMenuId", codeMenuId);
		}
		
		List<CodeMenu> codeMenuList = codeMenuService.excute("CodeMenuMapper.getBeanListByParm", null);
		model.addObject("codeMenuList", codeMenuList);
		
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("code/code_sub_menu_edit");
		List<CodeMenu> codeMenuList = codeMenuService.excute("CodeMenuMapper.getBeanListByParm", null);
		model.addObject("codeMenuList", codeMenuList);
		
		String statusValue = "";
		String codeMenuValue = "";
		if(id!=null){
			CodeSubMenu entity = codeSubMenuService.getById(id);
			model.addObject("entity", entity);
			statusValue = entity.getStatus()+"";
			codeMenuValue = entity.getFatherId()+"";
		} else {
			model.addObject("entity", new CodeSubMenu());
		}
		
		model.addObject("statusSelectHtml", HtmlUtil.SelectHtml("status", statusValue, "", EnumCodeSubMenuStatus.getListForSelect()));
		model.addObject("codeMenuSelectHtml", HtmlUtil.SelectHtml("fatherId", codeMenuValue, "", codeMenuService.getListForSelect()));
		
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(CodeSubMenu codeSubMenu,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		parmMap.put("orderBy", "csm.`NAME` asc");
		Page<Map<String, Object>> page = codeSubMenuService.getPage("CodeSubMenuMapper.getMapListByParm", parmMap);
		for(Map<String, Object> m : page.getList()){
			m.put("statusStr", EnumCodeSubMenuStatus.getValueByKey(Integer.valueOf(m.get("menuStatus")+"")));
			if((m.get("menuStatus")+"").equals(EnumCodeSubMenuStatus.STOP.getKey()+"")){
				m.put("hideEnable", false);
				m.put("hideBan", true);
			} else {
				m.put("hideEnable", true);
				m.put("hideBan", false);
			}
		}
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave")
	public Map<String, Object> save(CodeSubMenu codeSubMenu) {
		codeSubMenuService.save(codeSubMenu);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		CodeSubMenu codeSubMenu = new CodeSubMenu();
		codeSubMenu.setId(id);
		codeSubMenu.setStatus(EnumCodeSubMenuStatus.STOP.getKey());
		int i = codeSubMenuService.update(codeSubMenu);
		/*int i = codeSubMenuService.delete(id);*/
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
