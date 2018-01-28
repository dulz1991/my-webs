package com.demo.my.backend.controller;

import java.util.Map;

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
import com.demo.my.base.service.BlogMenuService;
import com.demo.my.base.model.BlogMenu;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/blogMenu")
public class BlogMenuController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogMenuController.class);
	
	@Autowired
	private BlogMenuService blogMenuService;
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("blog/blog_menu_list");
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("blog/blog_menu_edit");
		if(id!=null){
			BlogMenu entity = blogMenuService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new BlogMenu());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(BlogMenu blogMenu,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		Page<BlogMenu> page = blogMenuService.getPage("BlogMenuMapper.getBeanListByParm", parmMap);
		
		//返回参数
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(BlogMenu blogMenu) {
		blogMenuService.save(blogMenu);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = blogMenuService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
