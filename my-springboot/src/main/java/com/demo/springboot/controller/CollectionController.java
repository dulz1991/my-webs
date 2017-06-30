package com.demo.springboot.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Collection;
import com.demo.my.base.service.CollectionService;
import com.demo.my.base.service.CommentService;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.UserService;
import com.demo.springboot.service.file.ImageFileService;

@RestController
public class CollectionController extends BaseController {
	
	@Autowired
	private DiscoveryService discoveryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectionService collectionService;
	
	/**
	 * 收藏discovery
	 * @param col
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/addDiscoveryCollection", method=RequestMethod.POST)
	public Map<String, Object> addDiscoveryCollection(Collection col) {
		Long userId = this.getUserIdFromCookie();
		if(userId==null){
			return responseError(ErrorConstant.ERROR_400, "请先登录");
		}
		
		col.setUserId(userId);
		int count = collectionService.countByParm(col);
		if(count>0){
			return responseOK("已收藏");
		}
		
		col.setCreateTime(new Date());
		collectionService.insert(col);
		
		return responseOK("收藏成功");
	}
	
}
