package com.demo.my.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.demo.my.base.service.BlogMenuService;
import com.demo.my.base.service.BlogService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.model.Blog;
import com.demo.my.base.model.BlogMenu;
import com.demo.my.base.model.User;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/blog")
public class BlogController extends BaseBackendController {
	
	private Log logger = LogFactory.getLog(this.getClass());  
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;
	@Autowired
	private BlogMenuService blogMenuService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index(Long blogMenuId) {
		ModelAndView model = new ModelAndView("blog/blog_list");
		
		List<BlogMenu> menuList = blogMenuService.getBeanListByParm(new HashMap<String, Object>());
		model.addObject("menuList", menuList);
		
		if(blogMenuId!=null){
			model.addObject("blogMenuId", blogMenuId);
			for(BlogMenu menu : menuList){
				if(menu.getId().equals(blogMenuId)){
					model.addObject("currentMenu", menu);
				}
			}
		}
		
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("blog/blog_edit");
		if(id!=null){
			Blog entity = blogService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Blog blog,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		//查询参数
		Map<String, Object> parmMap =  this.getParmMap(blog);
		parmMap.put("orderBy", "b.id desc");
		parmMap.put("pageNo", pageNo);
		parmMap.put("pageSize", pageSize);
		
		//查询
		Page<Map<String, Object>> page = blogService.getPageMapByParm(parmMap);
		
		//返回参数
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@RequestMapping(value="/getDetail", method = RequestMethod.GET)
	public ModelAndView getDetail(Long id) {
		ModelAndView model = new ModelAndView("blog/blog_detail");
		
		Blog blog = blogService.getById(id);
		User user = userService.getById(blog.getUserId());
		
		model.addObject("entity", blog);
		model.addObject("username", user.getUsername());
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Blog blog) {
		blogService.save(blog);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = blogService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
