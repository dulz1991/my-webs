package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.Comment;
import com.demo.my.base.mybatis.mapper.ds1mapper.CommentMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class CommentService extends AdapterService {
	
	@Autowired
    CommentMapper commentMapper;
    
    public int insert(Comment comment) {
		return commentMapper.insert(comment);
	}
    
    public int update(Comment comment) {
		return commentMapper.update(comment);
	}
	
	public int delete(Long id) {
		return commentMapper.delete(id);
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
	
	public int countByParm(Comment comment) {
		Map<String, Object> parm = queryParm(comment);
		return commentMapper.countByParm(parm);
	}
	
	public Page<Comment> getBeanListByParm(Comment comment, int pageNo, Integer pageSize, String orderby) {
		Page<Comment> page = new Page<Comment>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(comment, page, orderby);
		
		int count = commentMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Comment> list = new ArrayList<Comment>();
		if(count!=0){
			list = commentMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Comment comment,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(comment, page, orderby);
		
		int count = commentMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = commentMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = commentMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = commentMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(Comment comment, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(comment!=null){
			
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(Comment comment) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(comment!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
