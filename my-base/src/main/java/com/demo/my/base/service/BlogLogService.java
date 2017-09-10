package com.demo.my.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, Object> getDetail(Long id) {
		return blogLogMapper.getDetail(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		return blogLogMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  blogLogMapper.getMapListByParm(parm);
	}
	
	public List<BlogLog> getBeanListByParm(Map<String, Object> parm) {
		return blogLogMapper.getBeanListByParm(parm);
	}
	
	public Page<BlogLog> getPageBeanByParm(Map<String, Object> parm) {
		Page<BlogLog> page = new Page<BlogLog>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<BlogLog> list = new ArrayList<BlogLog>();
		if(count!=0){
			list = this.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getPageMapByParm(Map<String, Object> parm) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = blogLogMapper.getMapListByParm_count(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = this.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
}
