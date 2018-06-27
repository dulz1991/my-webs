package com.demo.my.base.service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.SysMenuRole;
import com.demo.my.base.mybatis.mapper.ds1mapper.SysMenuRoleMapper;
import com.demo.my.base.service.common.AdapterService;
import com.demo.my.base.util.Page;


@Component  
public class SysMenuRoleService extends AbstractBaseService {
	
	@Autowired
    SysMenuRoleMapper sysMenuRoleMapper;
    
    public boolean insert(SysMenuRole sysMenuRole) {
		int result = sysMenuRoleMapper.insert(sysMenuRole);
		return result > 0;
	}
    
    public boolean update(SysMenuRole sysMenuRole) {
    	int result = sysMenuRoleMapper.update(sysMenuRole);
		return result > 0;
	}
	
	public boolean delete(Long id) {
		int result = sysMenuRoleMapper.delete(id);
		return result > 0;
	}
	
	public SysMenuRole getById(Long id) {
		return sysMenuRoleMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return sysMenuRoleMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return  sysMenuRoleMapper.getMapListByParm(parmMap);
	}
	
	public List<SysMenuRole> getBeanListByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return sysMenuRoleMapper.getBeanListByParm(parmMap);
	}
	
	public Page<SysMenuRole> getList(Map<String, Object> parmMap) {
		return this.getPage("SysMenuRoleMapper.getList", parmMap);
	}
}
