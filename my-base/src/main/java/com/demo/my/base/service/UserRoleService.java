package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.UserRole;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserRoleMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class UserRoleService extends AdapterService {
	
	@Autowired
    UserRoleMapper userRoleMapper;
    
    public int insert(UserRole userRole) {
		return userRoleMapper.insert(userRole);
	}
    
    public int update(UserRole userRole) {
		return userRoleMapper.update(userRole);
	}
	
	public int delete(Long id) {
		return userRoleMapper.delete(id);
	}
	
	public void save(UserRole userRole) {
		if(userRole.getId()!=null){
			userRoleMapper.update(userRole);
		} else {
			userRoleMapper.insert(userRole);
		}
	}

	public UserRole getById(Long id) {
		return userRoleMapper.getById(id);
	}
	
	public int countByParm(UserRole userRole) {
		Map<String, Object> parm = queryParm(userRole);
		return userRoleMapper.countByParm(parm);
	}
	
	public Page<UserRole> getBeanListByParm(UserRole userRole, int pageNo, Integer pageSize, String orderby) {
		Page<UserRole> page = new Page<UserRole>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(userRole, page, orderby);
		
		int count = userRoleMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<UserRole> list = new ArrayList<UserRole>();
		if(count!=0){
			list = userRoleMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(UserRole userRole,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(userRole, page, orderby);
		
		int count = userRoleMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userRoleMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = userRoleMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userRoleMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(UserRole userRole, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(userRole!=null){
			
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(UserRole userRole) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(userRole!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
