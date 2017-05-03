package com.demo.my.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.Code;
import com.demo.my.base.bean.CodeMenu;
import com.demo.my.base.bean.CodeSubMenu;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.CodeServiceBean;
import com.demo.my.base.converter.BeanConverter;

@Controller
@RequestMapping("/auth/code")
public class CodeController extends BaseController {

	@Resource(name = "codeServiceBean")
	private CodeServiceBean codeService;
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("code/code_list");
		return modelAndView;
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("code/code_edit");
		if (null == id) {
			modelAndView.addObject("title", "����");
		} else {
			Code code = codeService.getById(id, KeyConstant.MAPPER_CODE);
			CodeSubMenu codeSubMenu = codeService.getById(code.getFatherId(), KeyConstant.MAPPER_CODE_SUB_MENU);
			modelAndView.addObject("code", code);
			modelAndView.addObject("subMenu", codeSubMenu);
			modelAndView.addObject("title", "�޸�");
		}
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public Map<String, Object> list(Long fatherId, Long codeId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("fatherId", fatherId);	
		if(codeId!=null && codeId!=0L){
			parmMap.put("codeId", codeId);	
		}else {
			parmMap.put("codeId", null);
		}
		List<Code> codeList = codeService.getBeanListByParm(null, parmMap, KeyConstant.MAPPER_CODE);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = new BeanConverter().bean2Map(codeList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("list", list);
		resMap.put("length", codeList.size());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getMenuList", method=RequestMethod.GET)
	public Map<String, Object> getMenuList() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CodeMenu> codeMenuList = codeService.getBeanListByParm(null, null, KeyConstant.MAPPER_CODE_MENU);
		resMap.put("list", codeMenuList);
		resMap.put("length", codeMenuList.size());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubMenuList", method=RequestMethod.GET)
	public Map<String, Object> getSubMenuList(Long fatherId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("fatherId", fatherId);
		List<CodeSubMenu> codeMenuList = codeService.getBeanListByParm(null, parmMap, KeyConstant.MAPPER_CODE_SUB_MENU);
		resMap.put("list", codeMenuList);
		resMap.put("length", codeMenuList.size());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getNodeMenuList", method=RequestMethod.GET)
	public Map<String, Object> getNodeMenuList(Long fatherId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("fatherId", fatherId);
		List<Code> codeList = codeService.getBeanListByParm(null, parmMap, KeyConstant.MAPPER_CODE);
		resMap.put("list", codeList);
		resMap.put("length", codeList.size());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public Map<String, Object> save(HttpServletRequest request, Code code, String mod) {
		if (StringUtils.isBlank(code.getItem())) {
			return this.responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_TITLE);
		} else if (StringUtils.isBlank(code.getContent())) {
			return this.responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_CONTENT);
		} else if (null == code.getFatherId() || code.getFatherId()==0) {
			return this.responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_SUB_MENU);
		}
		//是否是节点
		if (mod.equals("node")) {
			code.setCodeId(null);
		}
		if (null != code.getId()) {
			codeService.update(code, KeyConstant.MAPPER_CODE);
		} else {
			Integer count = codeService.countByParm(new HashMap<String, Object>(), KeyConstant.MAPPER_CODE);
			count++;
			code.setItemOrder(Long.valueOf(count.toString()));
			codeService.insert(code, KeyConstant.MAPPER_CODE);
		}
		Map<String, Object> resMap = this.responseOK("");
		resMap.put("id", code.getId());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/doMove", method=RequestMethod.POST)
	public Map<String, Object> doMove(Long id, String type) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap = codeService.doMove(id, type);
		return resMap;
	}

}
