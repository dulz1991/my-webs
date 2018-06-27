package com.demo.my.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.SysUserRole;
import com.demo.my.base.mybatis.mapper.ds1mapper.SysUserRoleMapper;
import com.demo.my.base.service.common.AdapterService;

@Component  
public class SysUserRoleService extends AbstractBaseService {
	
	@Autowired
    SysUserRoleMapper sysUserRoleMapper;
    
    public boolean insert(SysUserRole sysUserRole) {
		int result = sysUserRoleMapper.insert(sysUserRole);
		return result > 0;
	}
    
    public boolean update(SysUserRole sysUserRole) {
    	int result = sysUserRoleMapper.update(sysUserRole);
		return result > 0;
	}
	
	public boolean delete(Long id) {
		int result = sysUserRoleMapper.delete(id);
		return result > 0;
	}
	
	public boolean save(SysUserRole sysUserRole) {
		if(sysUserRole.getId()!=null){
			return update(sysUserRole);
		} else {
			return insert(sysUserRole);
		}
	}
	
	public SysUserRole getById(Long id) {
		return sysUserRoleMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return sysUserRoleMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return  sysUserRoleMapper.getMapListByParm(parmMap);
	}
	
	public List<SysUserRole> getBeanListByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return sysUserRoleMapper.getBeanListByParm(parmMap);
	}

	/**
	 * 获取某个系统用户的权限
	 * @param id
	 * @return
	 */
	public List<SysUserRole> getSysUserRoleByUserId(Long sysUserId) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("sysUserId", sysUserId);
		return this.getBeanListByParm(parmMap);
	}
	public List<String> getRoleCodeByUserId(Long sysUserId) {
		List<SysUserRole> sysUserRoles = getSysUserRoleByUserId(sysUserId);
		List<String> roleCodeList = new ArrayList<String>();
		if(sysUserRoles!=null && sysUserRoles.size()>0){
			for(SysUserRole sysUserRole : sysUserRoles){
				roleCodeList.add(sysUserRole.getSysRoleCode());
			}
		}
		return roleCodeList;
	}

	public void deleteByUserId(Long userId) {
		sysUserRoleMapper.deleteByUserId(userId);
	}
}
