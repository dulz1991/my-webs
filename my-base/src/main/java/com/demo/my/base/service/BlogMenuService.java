package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.BlogMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogMenuMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class BlogMenuService extends AdapterService {
	
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
	
	public int countByParm(BlogMenu blogMenu) {
		Map<String, Object> parm = queryParm(blogMenu);
		return blogMenuMapper.countByParm(parm);
	}
	
	public Page<BlogMenu> getBeanListByParm(BlogMenu blogMenu, int pageNo, Integer pageSize, String orderby) {
		Page<BlogMenu> page = new Page<BlogMenu>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(blogMenu, page, orderby);
		
		int count = blogMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<BlogMenu> list = new ArrayList<BlogMenu>();
		if(count!=0){
			list = blogMenuMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(BlogMenu blogMenu,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(blogMenu, page, orderby);
		
		int count = blogMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = blogMenuMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = blogMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = blogMenuMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(BlogMenu blogMenu, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(blogMenu!=null){
			
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(BlogMenu blogMenu) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(blogMenu!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
