package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.Blog;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class BlogService extends AdapterService {
	
	@Autowired
    BlogMapper blogMapper;
    
    public int insert(Blog blog) {
		return blogMapper.insert(blog);
	}
    
    public int update(Blog blog) {
		return blogMapper.update(blog);
	}
	
	public int delete(Long id) {
		return blogMapper.delete(id);
	}
	
	public void save(Blog blog) {
		if(blog.getId()!=null){
			blogMapper.update(blog);
		} else {
			blogMapper.insert(blog);
		}
	}

	public Blog getById(Long id) {
		return blogMapper.getById(id);
	}
	
	public int countByParm(Blog blog) {
		Map<String, Object> parm = queryParm(blog);
		return blogMapper.countByParm(parm);
	}
	
	public Page<Blog> getBeanListByParm(Blog blog, int pageNo, Integer pageSize, String orderby) {
		Page<Blog> page = new Page<Blog>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(blog, page, orderby);
		
		int count = blogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Blog> list = new ArrayList<Blog>();
		if(count!=0){
			list = blogMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Blog blog,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(blog, page, orderby);
		
		int count = blogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = blogMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = blogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = blogMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(Blog blog, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(blog!=null){
			
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(Blog blog) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(blog!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
