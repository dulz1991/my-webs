package com.demo.my.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.Page;
import com.demo.my.base.model.Active;
import com.demo.my.base.mybatis.mapper.ds1mapper.ActiveMapper;


@Component  
public class ActiveService extends AbstractBaseService {
	
	@Autowired
    ActiveMapper activeMapper;
    
    public int insert(Active active) {
		return activeMapper.insert(active);
	}
    
    public int update(Active active) {
		return activeMapper.update(active);
	}
	
	public int delete(Long id) {
		return activeMapper.delete(id);
	}
	
	public void save(Active active) {
		if(active.getId()!=null){
			active.setUpdateTime(new Date());
			activeMapper.update(active);
		} else {
			active.setCreateTime(new Date());
			active.setUpdateTime(new Date());
			activeMapper.insert(active);
		}
	}

	public Active getById(Long id) {
		return activeMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		return activeMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  activeMapper.getMapListByParm(parm);
	}
	
	public List<Active> getBeanListByParm(Map<String, Object> parm) {
		return activeMapper.getBeanListByParm(parm);
	}
	
	public Page<Active> getPageBeanByParm(Map<String, Object> parm) {
		Page<Active> page = new Page<Active>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Active> list = new ArrayList<Active>();
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
