package com.demo.my.blog.controller.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.demo.my.base.service.file.ImageFileService;
import com.demo.my.base.util.DateUtil;

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
				Long o1Id = Long.valueOf(o1.get("id").toString());
				Long o2Id = Long.valueOf(o2.get("id").toString());
				return (o1Id).compareTo(o2Id);
			}
		});
		resMap.put("list", list);
		
		if(list!=null && !list.isEmpty()){
			for(Map<String, Object> map : list){
				Long id = Long.valueOf(map.get("id").toString());
				Map<String, Object> parm = new HashMap<String, Object>();
				parm.put("commentId", id);
				parm.put("notEmptyCommentId", "true");
				parm.put("orderBy", "c.id desc");
				List<Map<String, Object>> list1 = commentService.excute("CommentMapper.getMapListForDrag", parm);
				if(list1!=null && !list1.isEmpty()){
					for(Map<String, Object> map1 : list1){
						Date date = (Date) map1.get("createTime");
						map1.put("createTimeStr", DateUtil.calcDatetime(date));
					}
				}
				map.put("commentList", list1);
			}
		}
		
		resMap.put("pageSize", pageSize);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCommentListForComment", method=RequestMethod.GET)
	public Map<String, Object> getCommentListForComment(Comment comment, String type) {
		int pageSize = 10;
		
		Map<String, Object> parm = this.getParmMap();
		parm.put("emptyCommentId", "false");
		parm.put("pageSize", pageSize);
		if(StringUtils.isNotBlank(type)){
			parm.put("type", type);	
		}
		
		Map<String, Object> commentEntity = commentService.getCommentDetailById(comment.getCommentId());
		resMap.put("comment", commentEntity);
		
		List<Map<String, Object>> list = commentService.getCommentListForComment(parm);
		if(!list.isEmpty()){
			Collections.sort(list, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					Long o1Id = Long.valueOf(o1.get("id").toString());
					Long o2Id = Long.valueOf(o2.get("id").toString());
					return (o1Id).compareTo(o2Id);
				}
			});
			resMap.put("list", list);
		}
		
		if(list!=null && !list.isEmpty()){
			for(Map<String, Object> map : list){
				Long id = Long.valueOf(map.get("id").toString());
				Map<String, Object> parm1 = new HashMap<String, Object>();
				parm1.put("commentId", id);
				parm1.put("notEmptyCommentId", "true");
				parm1.put("orderBy", "c.id desc");
				map.put("commentList", commentService.excute("CommentMapper.getMapListForDrag", parm1));
			}
		}
		
		resMap.put("pageSize", pageSize);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/doComment", method=RequestMethod.POST)
	public Map<String, Object> doCommentForComment(Comment comment) {
		if(StringUtils.isBlank(comment.getMessageContent())){
			return responseError(ErrorConstant.ERROR_GENERAL, "请输入评论内容");
		}
		
		Long userId = this.getCurrentUserId();
		if(userId==null){
			return responseNoLogin();
		}
		
		comment.setFromId(userId);
		comment.setCreateTime(new Date());
		commentService.insert(comment);
		
		Map<String, Object> resMap = responseOK("");
		resMap.put("item", comment);
		return resMap;
	}
	
}
