package com.demo.my.backend.controller;

import java.util.List;
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
import com.demo.my.base.enums.EnumUserStatus;
import com.demo.my.base.model.SysRole;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("sysRole/sysRole_list");
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("sysRole/sysRole_edit");
		if(id!=null){
			SysRole entity = sysRoleService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new SysRole());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList() {
		//查询参数
		Map<String, Object> parmMap =  getParmMap();
		//查询
		Page<SysRole> page = sysRoleService.getPage("SysRoleMapper.getBeanListByParm", parmMap);

		//返回参数
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave")
	public Map<String, Object> save(SysRole sysRole) {
		sysRoleService.save(sysRole);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = sysRoleService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/getSysUserByRoleCode")
	public Map<String, Object> getSysUserByRoleCode(String roleCode) {
		if(StringUtils.isBlank(roleCode)){
			return responseGeneralError("查询失败：参数异常");
		}
		List<Map<String, Object>> list = sysRoleService.getSysUserByRoleCode(roleCode);
		if(list!=null){
			for(Map<String, Object> map : list){
				map.put("statusStr", EnumUserStatus.getValueByKey(Integer.valueOf(map.get("status").toString())));
			}
		}
		Map<String, Object> resMap = responseOK();
		resMap.put("list", list);
		return resMap;
	}

	
	@RequestMapping(value="/addMenu")
	public ModelAndView addMenu(Long roleId) {
		ModelAndView model = new ModelAndView("sysRole/edit_menu");
		model.addObject("roleId", roleId);
		model.addObject("menuTree", JSONArray.toJSON(sysMenuService.getMenuTree(roleId)));
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveMenu")
	public Map<String, Object> saveMenu(Long roleId, String menuIds) {
		if(roleId==null){
			return responseGeneralError("参数异常");
		}
		if(StringUtils.isBlank(menuIds)){
			return responseGeneralError("请先勾选菜单");
		}
		sysRoleService.saveMenu(roleId, menuIds);
		return responseOK();
	}
}
