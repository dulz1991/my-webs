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
import com.demo.my.base.service.BlogLogService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.model.BlogLog;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/blogLog")
public class BlogLogController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogLogController.class);
	
	@Autowired
	private BlogLogService blogLogService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("blog/blog_log_list");
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("blog/blog_log_edit");
		if(id!=null){
			BlogLog entity = blogLogService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(BlogLog blogLog,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		Page<Map<String, Object>> page = blogLogService.getPage("BlogLogMapper.getMapListByParm", parmMap);

		//返回参数
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(BlogLog blogLog) {
		blogLogService.save(blogLog);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = blogLogService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

	@RequestMapping(value="/getDetail")
	public ModelAndView getDetail(Long id) {
		ModelAndView model = new ModelAndView("blog/blog_detail");
		
		Map<String, Object> blog = blogLogService.getDetail(id);
		
		model.addObject("entity", blog);
		model.addObject("username", blog.get("username"));
		return model;
	}
}
