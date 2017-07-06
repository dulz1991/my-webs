package com.demo.my.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	public int countByParm(Map<String, Object> parmMap) {
		return blogMenuMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  blogMenuMapper.getMapListByParm(parm);
	}
	
	public List<BlogMenu> getBeanListByParm(Map<String, Object> parm) {
		return blogMenuMapper.getBeanListByParm(parm);
	}
	
	public Page<BlogMenu> getPageBeanByParm(Map<String, Object> parm) {
		Page<BlogMenu> page = new Page<BlogMenu>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<BlogMenu> list = new ArrayList<BlogMenu>();
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
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = this.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
}
