package com.demo.my.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.common.BaseCommon;
import com.demo.my.base.model.Comment;
import com.demo.my.base.mybatis.mapper.ds1mapper.CommentMapper;
import com.demo.my.base.util.DateUtil;

@Component  
public class CommentService extends AbstractBaseService {
	
	@Autowired
    CommentMapper commentMapper;
    
    public int insert(Comment comment) {
		return commentMapper.insert(comment);
	}
    
    public int update(Comment comment) {
		return commentMapper.update(comment);
	}
	
	public int delete(List<String> ids) {
		return commentMapper.delete(ids);
	}
	
	public void save(Comment comment) {
		if(comment.getId()!=null){
			commentMapper.update(comment);
		} else {
			commentMapper.insert(comment);
		}
	}

	public Comment getById(Long id) {
		return commentMapper.getById(id);
	}
	
	public Map<String, Object> getCommentDetailById(Long id) {
		return commentMapper.getCommentDetailById(id);
	}
	
	public List<Map<String, Object>> getCommentListForComment(Map<String, Object> parm) {
		List<Map<String, Object>> list = commentMapper.getMapListForDrag(parm);
		return list;
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		if(parmMap==null){
			parmMap = new HashMap<String, Object>();
		}
		return commentMapper.countByParm(parmMap);
	}
	
	public int countByParm(Comment c) {
		if(c==null){
			c = new Comment();
		}
		return commentMapper.countByParm(c);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  commentMapper.getMapListByParm(parm);
	}
	
	public List<Comment> getBeanListByParm(Map<String, Object> parm) {
		return commentMapper.getBeanListByParm(parm);
	}
	
	public List<Map<String, Object>> getMapListForDrag(Comment comment, Integer pageSize, String type) {
		Map<String, Object> parm = BaseCommon.obj2Map(comment);
		parm.put("emptyCommentId", "true");
		parm.put("pageSize", pageSize);
		if(StringUtils.isNotBlank(type)){
			parm.put("type", type);	
		}
		List<Map<String, Object>> list = commentMapper.getMapListForDrag(parm);
		for(Map<String, Object> map : list){
			Date date = (Date) map.get("createTime");
			map.put("createTimeStr", DateUtil.calcDatetime(date));
		}
		return list;
	}
	
}
