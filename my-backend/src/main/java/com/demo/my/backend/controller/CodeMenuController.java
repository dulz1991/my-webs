package com.demo.my.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.enums.EnumCodeMenuStatus;
import com.demo.my.base.enums.EnumCodeSubMenuStatus;
import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.util.HtmlUtil;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/codeMenu")
public class CodeMenuController extends BaseBackendController {
	
	private static final Log logger = LogFactory.getLog(CodeMenuController.class);
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("code/menu_list");
		List<Map<String, Object>> menuList = codeMenuService.getListForZtree();
		for(Map<String, Object> map : menuList){
			map.put("statusStr", EnumCodeSubMenuStatus.getValueByKey(toInt(map.get("status"))));
		}
		model.addObject("menuTreeNode", JSONArray.toJSON(menuList));
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("code/menu_edit");
		if(id!=null){
			CodeMenu entity = codeMenuService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new CodeMenu());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(CodeMenu codeMenu,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		Page<Map<String, Object>> page = codeMenuService.getPage("CodeMenuMapper.getMapListByParm", parmMap);
		for(Map<String, Object> m : page.getList()){
			m.put("statusStr",EnumCodeMenuStatus.getValueByKey(Integer.valueOf(m.get("status")+"")));
			
			if((m.get("status")+"").equals(EnumCodeMenuStatus.STOP.getKey()+"")){
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
	public Map<String, Object> save(CodeMenu codeMenu) {
		if(codeMenu.getOrderBy()==null || codeMenu.getOrderBy()<0){
			return responseGeneralError("分类排序不能为空且大于0");
		}
		if(StringUtils.isBlank(codeMenu.getName())){
			return responseGeneralError("分类名称不能为空");
		}
		codeMenuService.save(codeMenu);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		CodeMenu codeMenu = new CodeMenu();
		codeMenu.setId(id);
		codeMenu.setStatus(EnumCodeMenuStatus.STOP.getKey()+"");
		int i = codeMenuService.update(codeMenu);
		/*int i = codeMenuService.delete(id);*/
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
