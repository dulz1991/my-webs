package com.demo.my.base.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.Blog;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogMapper;

@Component  
public class BlogService extends AbstractBaseService {
	
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
	
}
