package com.demo.my.backend.controller;

import java.util.HashMap;
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
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/codeMenu")
public class CodeMenuController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeMenuController.class);
	
	@Autowired
	private CodeMenuService codeMenuService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("code/code_menu_list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("code/code_menu_edit");
		if(id!=null){
			CodeMenu entity = codeMenuService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new CodeMenu());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(CodeMenu codeMenu,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Page<CodeMenu> page = codeMenuService.getBeanListByParm(codeMenu, pageNo, pageSize, "ORDER_BY asc");

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(CodeMenu codeMenu) {
		if(codeMenu.getOrderBy()==null || codeMenu.getOrderBy()<0){
			return responseError(ErrorConstant.ERROR_500, "分类排序不能为空且大于0");
		}
		if(StringUtils.isBlank(codeMenu.getName())){
			return responseError(ErrorConstant.ERROR_500, "分类名称不能为空");
		}
		codeMenuService.save(codeMenu);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = codeMenuService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
