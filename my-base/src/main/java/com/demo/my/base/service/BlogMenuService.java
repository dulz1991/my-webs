package com.demo.my.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.BlogMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogMenuMapper;

@Component  
public class BlogMenuService extends AbstractBaseService {
	
	@Autowired
    BlogMenuMapper blogMenuMapper;
    
    public int insert(BlogMenu blogMenu) {
		return blogMenuMapper.insert(blogMenu);
	}
    
    public int update(BlogMenu blogMenu) {
		return blogMenuMapper.update(blogMenu);
	}
	
	public int delete(Long id) {
		return blogMenuMapper.delete(id);
	}
	
	public void save(BlogMenu blogMenu) {
		if(blogMenu.getId()!=null){
			blogMenuMapper.update(blogMenu);
		} else {
			blogMenuMapper.insert(blogMenu);
		}
	}

	public BlogMenu getById(Long id) {
		return blogMenuMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		if(parmMap==null){
			parmMap = new HashMap<String, Object>();
		}
		return blogMenuMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return  blogMenuMapper.getMapListByParm(parm);
	}
	
	public List<BlogMenu> getBeanListByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return blogMenuMapper.getBeanListByParm(parm);
	}
	
	
}
