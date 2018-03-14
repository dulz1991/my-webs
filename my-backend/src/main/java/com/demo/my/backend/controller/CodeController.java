package com.demo.my.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.demo.my.base.service.SysCfgService;
import com.demo.my.base.common.BaseCommon;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.Code;
import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.model.CodeSubMenu;
import com.demo.my.base.model.SysCfg;
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
	@Autowired
	private SysCfgService sysCfgService;
	
	@RequestMapping(value="/list")
	public ModelAndView index(String fatherId) {
		ModelAndView model = new ModelAndView("code/code_list");
		
		if(StringUtils.isNotBlank(fatherId)){
			model.addObject("codeSubMenuId", fatherId);
			CodeSubMenu codeSubMenu = codeSubMenuService.getById(Long.valueOf(fatherId));
			if(codeSubMenu!=null){
				model.addObject("codeMenuId", codeSubMenu.getFatherId());	
			}
		}
		
		List<CodeMenu> codeMenuList = codeMenuService.excute("CodeMenuMapper.getBeanListByParm", null);
		model.addObject("codeMenuList", codeMenuList);
		
		return model;
	}
	
	@RequestMapping(value="/edit")
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
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(Code code, Long levelOne,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		parmMap.put("orderBy", "c.item_order asc");
		Page<Map<String, Object>> page = codeService.getPage("CodeMapper.getMapListByParm", parmMap);

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Code code) {
		if (StringUtils.isBlank(code.getItem())) {
			return BaseCommon.responseError(ErrorConstant.ERROR_GENERAL, ErrorConstant.ERROR_EMPTY_TITLE);
		} else if (StringUtils.isBlank(code.getContent())) {
			return responseGeneralError(ErrorConstant.ERROR_EMPTY_CONTENT);
		} else if (null == code.getFatherId() || code.getFatherId()==0) {
			return responseGeneralError(ErrorConstant.ERROR_EMPTY_SUB_MENU);
		}
		
		if(code.getCodeId()==null || code.getCodeId()<=0){
			code.setCodeId(null);
			code.setCodeLevel(1);
		} else {
			code.setCodeLevel(2);
		}
		
		if (null != code.getId()) {
			codeService.update(code);
		} else {
			Integer count = codeService.countByParm(null);
			count++;
			code.setItemOrder(Long.valueOf(count.toString()));
			codeService.insert(code);
		} 
		Map<String, Object> resMap = responseOK();
		resMap.put("id", code.getId());
		return resMap;
	}
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.POST)
	public Map<String, Object> doDelete(Code code) {
		int i= codeService.update(code);
		if(i>0){
			return responseOK();
		} else {
			return responseGeneralError("删除失败");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
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
		Map<String, Object> resMap = responseOK();
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
		return resMap; 
	}
	
	@RequestMapping(value="/viewDetail")
	public ModelAndView viewDetail(Long id) {
		ModelAndView model = new ModelAndView("code/code_detail");
		Code entity = codeService.getById(id);
		entity.setContent(entity.getContent().replace("http://my.demo", "/api_img"));
		model.addObject("entity", entity);
		return model;
	}
	@ResponseBody
	@RequestMapping(value="/viewDetailReload")
	public Map<String, Object> viewDetailReload(Long id) {
		Map<String, Object> resMap = responseOK();
		Code entity = codeService.getById(id);
		entity.setContent(entity.getContent().replace("http://my.demo", "/api_img"));
		resMap.put("entity", entity);
		return resMap;
	}
	
	@RequestMapping(value="/zTreelist")
	public ModelAndView zTreelist() throws JsonGenerationException, JsonMappingException, IOException {
		ModelAndView model = new ModelAndView("code/code_ztree_list");
		List<Map<String, Object>> codeMenuList = codeMenuService.getListForZtree();
		ObjectMapper mapper = new ObjectMapper();  
		model.addObject("codeMenuList", mapper.writeValueAsString(codeMenuList));
		SysCfg sysCfg = sysCfgService.getByKey(KeyConstant.DEFAULT_CODE_SUB_MENU_ID);
		if(sysCfg!=null){
			model.addObject("defaultNodeId", sysCfg.getValue());	
		} else {
			model.addObject("defaultNodeId", "1");	
		}
		return model;
	}
	@ResponseBody
	@RequestMapping(value="/zTreeCodelist")
	public Map<String, Object> zTreeCodelist(Long faltherId) {
		try {
			Map<String, Object> resMap = responseOK();
			List<Map<String, Object>> codeList = codeService.getCodeListForZtree(faltherId);
			ObjectMapper mapper = new ObjectMapper();  
			resMap.put("codeList", mapper.writeValueAsString(codeList));
			resMap.put("count", codeList.size());
			return resMap;
		} catch (Exception e) {
			return responseGeneralError("系统异常："+e.getMessage());
		}
	}
}
