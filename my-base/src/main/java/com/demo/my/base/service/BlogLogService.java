package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.BlogLog;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogLogMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class BlogLogService extends AdapterService {
	
	@Autowired
    BlogLogMapper blogLogMapper;
    
    public int insert(BlogLog blogLog) {
		return blogLogMapper.insert(blogLog);
	}
    
    public int update(BlogLog blogLog) {
		return blogLogMapper.update(blogLog);
	}
	
	public int delete(Long id) {
		return blogLogMapper.delete(id);
	}
	
	public void save(BlogLog blogLog) {
		if(blogLog.getId()!=null){
			blogLogMapper.update(blogLog);
		} else {
			blogLogMapper.insert(blogLog);
		}
	}

	public BlogLog getById(Long id) {
		return blogLogMapper.getById(id);
	}
	
	public int countByParm(BlogLog blogLog) {
		Map<String, Object> parm = queryParm(blogLog);
		return blogLogMapper.countByParm(parm);
	}
	
	public Page<BlogLog> getBeanListByParm(BlogLog blogLog, int pageNo, Integer pageSize, String orderby) {
		Page<BlogLog> page = new Page<BlogLog>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(blogLog, page, orderby);
		
		int count = blogLogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<BlogLog> list = new ArrayList<BlogLog>();
		if(count!=0){
			list = blogLogMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(BlogLog blogLog,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(blogLog, page, orderby);
		
		int count = blogLogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = blogLogMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = blogLogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = blogLogMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(BlogLog blogLog, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(blogLog!=null){
			
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(BlogLog blogLog) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(blogLog!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
