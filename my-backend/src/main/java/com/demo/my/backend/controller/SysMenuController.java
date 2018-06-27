package com.demo.my.backend.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.enums.EnumIsEnable;
import com.demo.my.base.model.SysMenu;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);
	
	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("sysMenu/sysMenu_list");
		model.addObject("levelOneList", JSONArray.toJSON(sysMenuService.getLevelOneMenuList()));
		return model;
	}
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList() {
		//查询参数
		Map<String, Object> parmMap =  getParmMap();
		//查询
		Page<SysMenu> page = sysMenuService.getList(parmMap);
		//返回参数
		Map<String, Object> resMap = responseOK();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	/**
	 * 编辑
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("sysMenu/sysMenu_edit");
		
		model.addObject("list", sysMenuService.getLevelOneMenuList());
		if(id!=null){
			SysMenu entity = sysMenuService.getById(id);
			model.addObject("entity", entity);
			model.addObject("title", "修改【"+entity.getMenuName()+"】");
		} else {
			model.addObject("entity", new SysMenu());
			model.addObject("title", "添加");
		}
		return model;
	}
	
	/**
	 * 保存
	 * @param schoolSubject
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSave")
	public Map<String, Object> save(SysMenu sysMenu) {
		if(StringUtils.isBlank(sysMenu.getMenuName())){
			return responseGeneralError("请输入菜单名称");
		}
		
		SysMenu parent = null;
		if(sysMenu.getParentId()!=-1){
			parent = sysMenuService.getById(sysMenu.getParentId());
		}
		
		if(parent!=null){
			sysMenu.setMenuLevel(parent.getMenuLevel()+1);
		} else {
			sysMenu.setMenuLevel(1);
		}
		
		boolean b = false;
		if(sysMenu.getId()!=null){
			b = sysMenuService.update(sysMenu);
		} else {
			sysMenu.setStatus(EnumIsEnable.ENABLE.getKey());
			SysMenu last = sysMenuService.getLast();
			if(last!=null){
				sysMenu.setPriority((Integer.valueOf(last.getPriority())+1)+"");
			}
			b = sysMenuService.insert(sysMenu);
		}
		if(b){
			return responseOK();			
		}
		return responseGeneralError("保存失败");
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseGeneralError("删除的记录不存在");
		}
		if(!sysMenuService.delete(id)){
			return responseGeneralError("删除失败");
		}
		return responseOK();
	}

}
