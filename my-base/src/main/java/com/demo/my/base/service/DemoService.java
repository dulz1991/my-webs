package com.demo.my.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.Page;
import com.demo.my.base.model.Demo;
import com.demo.my.base.mybatis.mapper.ds1mapper.DemoMapper;
import com.demo.my.base.service.common.AdapterService;

@Component  
public class DemoService extends AdapterService {
	
	@Autowired
    DemoMapper demoMapper;
    
    public int insert(Demo demo) {
    	demo.setUpdateTime(new Date());
    	demo.setCreateTime(new Date());
    	demo.setDownloadTimes(0);
		return demoMapper.insert(demo);
	}
    
    public int update(Demo demo) {
    	demo.setUpdateTime(new Date());
		return demoMapper.update(demo);
	}
	
	public int delete(Long id) {
		return demoMapper.delete(id);
	}
	
	public void save(Demo demo) {
		if(demo.getId()!=null){
			demoMapper.update(demo);
		} else {
			demoMapper.insert(demo);
		}
	}

	public Demo getById(Long id) {
		return demoMapper.getById(id);
	}
	
	public int countByParm(Demo demo) {
		Map<String, Object> parm = queryParm(demo);
		return demoMapper.countByParm(parm);
	}
	
	public Page<Demo> getBeanListByParm(Demo demo, int pageNo, Integer pageSize, String orderby) {
		Page<Demo> page = new Page<Demo>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(demo, page, orderby);
		
		int count = demoMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Demo> list = new ArrayList<Demo>();
		if(count!=0){
			list = demoMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Demo demo,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(demo, page, orderby);
		
		int count = demoMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = demoMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = demoMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = demoMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(Demo demo, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(demo!=null){
			if(demo.getMenuId()!=null && demo.getMenuId()>0){
				parm.put("menuId", demo.getMenuId());
			}
			if(StringUtils.isNotBlank(demo.getTitle())){
				parm.put("title", demo.getTitle());
			}
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(Demo demo) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(demo!=null){
			if(demo.getMenuId()!=null && demo.getMenuId()>0){
				parm.put("menuId", demo.getMenuId());
			}
			if(StringUtils.isNotBlank(demo.getTitle())){
				parm.put("title", demo.getTitle());
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
