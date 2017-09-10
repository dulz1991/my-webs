package com.demo.my.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
			blog.setUpdateTime(new Date());
			blogMapper.update(blog);
		} else {
			blog.setCreateTime(new Date());
			blog.setUpdateTime(new Date());
			blogMapper.insert(blog);
		}
	}

	public Blog getById(Long id) {
		return blogMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		return blogMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  blogMapper.getMapListByParm(parm);
	}
	
	public List<Blog> getBeanListByParm(Map<String, Object> parm) {
		return blogMapper.getBeanListByParm(parm);
	}
	
	public Page<Blog> getPageBeanByParm(Map<String, Object> parm) {
		Page<Blog> page = new Page<Blog>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Blog> list = new ArrayList<Blog>();
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
		
		int count = blogMapper.getMapListByParm_count(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = this.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	
	
	

}
