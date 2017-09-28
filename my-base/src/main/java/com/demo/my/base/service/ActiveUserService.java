package com.demo.my.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.ActiveUser;
import com.demo.my.base.mybatis.mapper.ds1mapper.ActiveUserMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class ActiveUserService extends AdapterService {
	
	@Autowired
    ActiveUserMapper activeUserMapper;
    
    public int insert(ActiveUser activeUser) {
		return activeUserMapper.insert(activeUser);
	}
    
    public int update(ActiveUser activeUser) {
		return activeUserMapper.update(activeUser);
	}
	
	public int delete(Long id) {
		return activeUserMapper.delete(id);
	}
	
	public void save(ActiveUser activeUser) {
		if(activeUser.getId()!=null){
			activeUserMapper.update(activeUser);
		} else {
			activeUserMapper.insert(activeUser);
		}
	}

	public ActiveUser getById(Long id) {
		return activeUserMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		return activeUserMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  activeUserMapper.getMapListByParm(parm);
	}
	
	public List<ActiveUser> getBeanListByParm(Map<String, Object> parm) {
		return activeUserMapper.getBeanListByParm(parm);
	}
	
	public Page<ActiveUser> getPageBeanByParm(Map<String, Object> parm) {
		Page<ActiveUser> page = new Page<ActiveUser>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<ActiveUser> list = new ArrayList<ActiveUser>();
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
