package com.demo.springboot.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Comment;
import com.demo.my.base.service.CommentService;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.UserService;
import com.demo.springboot.service.file.ImageFileService;

@RestController
public class CommentController extends BaseController {
	
	@Autowired
	private DiscoveryService discoveryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private CommentService commentService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommentList", method=RequestMethod.GET)
	public Map<String, Object> getCommentList(Comment comment, String type) {
		int pageSize = 10;
		
		List<Map<String, Object>> list = commentService.getMapListForDrag(comment, pageSize, type);
		if(list.isEmpty()){
			return responseError(ErrorConstant.ERROR_GENERAL, "没有更多数据");
		}
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Long o1Id = Long.valueOf(o1.get("commentId").toString());
				Long o2Id = Long.valueOf(o2.get("commentId").toString());
				return (o1Id).compareTo(o2Id);
			}
		});
		resMap.put("list", list);
		
		resMap.put("pageSize", pageSize);
		return resMap;
	}
	
}
