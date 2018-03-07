package com.demo.my.backend.controller;

import java.util.ArrayList;
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

import com.demo.my.base.service.CommentService;
import com.demo.my.base.model.Comment;
import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/comment")
public class CommentController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("comment/comment_list");
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("comment/comment_edit");
		if(id!=null){
			Comment entity = commentService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new Comment());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(Comment comment,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		
		Map<String, Object> parmMap = this.getParmMap();
		Page<Map<String, Object>> page = commentService.getPage("CommentMapper.getPageMapByParm", parmMap);
		
		//返回参数
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Comment comment) {
		commentService.save(comment);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(String ids) {
		if(StringUtils.isBlank(ids)){
			return responseGeneralError("删除的记录不存在");
		}
		String arr[] = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for(String str : arr){
			if(StringUtils.isNotBlank(str)){
				idList.add(str);
			}
		}
		
		int i = commentService.delete(idList);
		if(i>0){
			return responseOK("删除成功");
		}
		return responseGeneralError("删除失败");
	}

}
