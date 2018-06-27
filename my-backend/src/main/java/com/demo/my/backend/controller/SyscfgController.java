package com.demo.my.backend.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.model.SysCfg;
import com.demo.my.base.service.SysCfgService;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/sys")
public class SyscfgController extends BaseBackendController {
	
	@Autowired
	private SysCfgService sysCfgService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("syscfg/sysCfg_list");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList() {
		//查询参数
		Map<String, Object> parmMap =  getParmMap();
		//查询
		Page<SysCfg> page = sysCfgService.getPage("SysCfgMapper.getBeanListByParm", parmMap);
		//返回参数
		Map<String, Object> resMap = responseOK();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("syscfg/sysCfg_edit");
		if(id!=null){
			SysCfg entity = sysCfgService.getById(id);
			model.addObject("entity", entity);
			model.addObject("isUpdate", true);
			model.addObject("title", "修改系统参数");
		} else {
			model.addObject("entity", new SysCfg());
			model.addObject("title", "新增系统参数");
			model.addObject("isNew", true);
		}
		return model;
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
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseGeneralError("删除的记录不存在");
		}
		if(sysCfgService.delete(id)>0){
			return responseOK();
		}
		return responseGeneralError("删除失败");
	}

	
}
