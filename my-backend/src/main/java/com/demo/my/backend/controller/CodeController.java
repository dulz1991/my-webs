package com.demo.my.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.service.CodeMenuService;
import com.demo.my.base.service.CodeService;
import com.demo.my.base.service.CodeSubMenuService;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Code;
import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.model.CodeSubMenu;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/code")
public class CodeController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
	
	@Autowired
	private CodeService codeService;
	@Autowired
	private CodeMenuService codeMenuService;
	@Autowired
	private CodeSubMenuService codeSubMenuService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("code/code_list");
		List<CodeMenu> codeMenuList = codeMenuService.excute("CodeMenuMapper.getBeanListByParm", null);
		model.addObject("codeMenuList", codeMenuList);
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("code/code_edit");
		
		List<CodeMenu> codeMenuList = codeMenuService.excute("CodeMenuMapper.getBeanListByParm", null);
		model.addObject("codeMenuList", codeMenuList);
		
		if(id!=null){
			Code entity = codeService.getById(id);
			model.addObject("entity", entity);
			
			CodeSubMenu codeSubMenu = codeSubMenuService.getById(entity.getFatherId());
			model.addObject("codeMenuId", codeSubMenu.getFatherId());
			
			Map<String, Object> parmMap = new HashMap<String, Object>();
			parmMap.put("fatherId", codeSubMenu.getFatherId());
			List<CodeSubMenu> codeSubMenuList = codeSubMenuService.excute("CodeSubMenuMapper.getBeanListByParm", parmMap);
			model.addObject("codeSubMenuList", codeSubMenuList);
			
			parmMap = new HashMap<String, Object>();
			parmMap.put("codeLevel", 1);
			parmMap.put("codeId", entity.getCodeId());
			parmMap.put("fatherId", codeSubMenu.getId());
			List<Code> codeIdList = codeService.excute("CodeMapper.getBeanListByParm", parmMap);
			model.addObject("codeIdList", codeIdList);
		} else {
			model.addObject("entity", new Code());
			model.addObject("codeMenuId", "");
			model.addObject("codeSubMenuList", new ArrayList<CodeSubMenu>());
			model.addObject("codeIdList", new ArrayList<Code>());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Code code, Long levelOne,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Page<Map<String, Object>> page = codeService.getMapListByParm(code, pageNo, pageSize, "c.item_order asc");

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Code code) {
		if (StringUtils.isBlank(code.getItem())) {
			return this.responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_TITLE);
		} else if (StringUtils.isBlank(code.getContent())) {
			return this.responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_CONTENT);
		} else if (null == code.getFatherId() || code.getFatherId()==0) {
			return this.responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_SUB_MENU);
		}
		if (null != code.getId()) {
			codeService.update(code);
		} else {
			Integer count = codeService.countByParm(new Code());
			count++;
			code.setItemOrder(Long.valueOf(count.toString()));
			codeService.insert(code);
		}
		Map<String, Object> resMap = this.responseOK("");
		resMap.put("id", code.getId());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = codeService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

	@ResponseBody
	@RequestMapping(value="/getCodeListByFatherId",  method = RequestMethod.GET)
	public Map<String, Object> getCodeListByFatherId(Long id) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("fatherId", id);
		resMap.put("codeLevel", 1);
		
		List<Code> codeList = codeService.excute("CodeMapper.getBeanListByParm", resMap);
		resMap.put("codeList", codeList);
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		for(Code code : codeList){
			Map<String, Object>  map = new HashMap<String, Object>();
			map.put("key", code.getId());
			map.put("value", code.getItem());
			listMap.add(map);
		}
		resMap.put("list", listMap);
		
		resMap.put("errorNo", 200);
		return resMap; 
	}
	
	@RequestMapping(value="/viewDetail", method = RequestMethod.GET)
	public ModelAndView viewDetail(Long id) {
		ModelAndView model = new ModelAndView("code/code_detail");
		Code entity = codeService.getById(id);
		model.addObject("entity", entity);
		return model;
	}
}
