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

import com.demo.my.base.service.CollectionService;
import com.demo.my.base.service.CommentService;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.model.Comment;
import com.demo.my.base.model.Discovery;
import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/discovery")
public class DiscoveryController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(DiscoveryController.class);
	
	@Autowired
	private DiscoveryService discoveryService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectionService collectionService;
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("discovery/discovery_list");
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("discovery/discovery_edit");
		if(id!=null){
			Discovery entity = discoveryService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new Discovery());
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(Discovery discovery,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		
		Map<String, Object> parmMap = this.getParmMap();
		parmMap.put("orderBy", "d.id desc");
		Page<Map<String, Object>> page = discoveryService.getPage("DiscoveryMapper.getMapListByParm", parmMap);
		
		//返回参数
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Discovery discovery) {
		discoveryService.save(discovery);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = discoveryService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}
	
	
	/*评论*/
	@RequestMapping(value="/commentLlist")
	public ModelAndView commentLlist() {
		ModelAndView model = new ModelAndView("discovery/comment_list");
		return model;
	}
	@ResponseBody
	@RequestMapping(value="/getCommentLlist")
	public Map<String, Object> getCommentLlist(Comment comment,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		parmMap.put("orderBy", "c.id desc");
		Page<Map<String, Object>> page = commentService.getPage("CommentMapper.getMapListByParm", parmMap);

		//返回参数
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	/*收藏*/
	@RequestMapping(value="/collectionLlist")
	public ModelAndView collectionLlist() {
		ModelAndView model = new ModelAndView("discovery/collection_list");
		return model;
	}
	@ResponseBody
	@RequestMapping(value="/getCollectionLlist")
	public Map<String, Object> getCollectionLlist(Comment comment,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		parmMap.put("orderBy", "c.id desc");
		Page<Map<String, Object>> page = collectionService.getPage("CollectionMapper.getMapListByParm", parmMap);
		
		//返回参数
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
}
