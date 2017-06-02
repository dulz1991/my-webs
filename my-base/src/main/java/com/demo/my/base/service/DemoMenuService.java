package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.DemoMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.DemoMenuMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class DemoMenuService extends AdapterService {
	
	@Autowired
    DemoMenuMapper demoMenuMapper;
    
    public int insert(DemoMenu demoMenu) {
		return demoMenuMapper.insert(demoMenu);
	}
    
    public int update(DemoMenu demoMenu) {
		return demoMenuMapper.update(demoMenu);
	}
	
	public int delete(Long id) {
		return demoMenuMapper.delete(id);
	}
	
	public void save(DemoMenu demoMenu) {
		if(demoMenu.getId()!=null){
			demoMenuMapper.update(demoMenu);
		} else {
			demoMenuMapper.insert(demoMenu);
		}
	}

	public DemoMenu getById(Long id) {
		return demoMenuMapper.getById(id);
	}
	
	public int countByParm(DemoMenu demoMenu) {
		Map<String, Object> parm = queryParm(demoMenu);
		return demoMenuMapper.countByParm(parm);
	}
	
	public Page<DemoMenu> getBeanListByParm(DemoMenu demoMenu, int pageNo, Integer pageSize, String orderby) {
		Page<DemoMenu> page = new Page<DemoMenu>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(demoMenu, page, orderby);
		
		int count = demoMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<DemoMenu> list = new ArrayList<DemoMenu>();
		if(count!=0){
			list = demoMenuMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(DemoMenu demoMenu,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(demoMenu, page, orderby);
		
		int count = demoMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = demoMenuMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = demoMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = demoMenuMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(DemoMenu demoMenu, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(demoMenu!=null){
			
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(DemoMenu demoMenu) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(demoMenu!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
