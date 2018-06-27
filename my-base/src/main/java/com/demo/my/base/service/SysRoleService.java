package com.demo.my.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.SysMenuRole;
import com.demo.my.base.model.SysRole;
import com.demo.my.base.mybatis.mapper.ds1mapper.SysMenuRoleMapper;
import com.demo.my.base.mybatis.mapper.ds1mapper.SysRoleMapper;
import com.demo.my.base.service.common.AdapterService;


@Component  
public class SysRoleService extends AbstractBaseService {
	
	@Autowired
    SysRoleMapper sysroleMapper;
	@Autowired
    SysMenuRoleMapper sysMenuRoleMapper;
    
    public int insert(SysRole sysrole) {
		return sysroleMapper.insert(sysrole);
	}
    
    public int update(SysRole sysrole) {
		return sysroleMapper.update(sysrole);
	}
	
	public int delete(Long id) {
		return sysroleMapper.delete(id);
	}
	
	public void save(SysRole sysrole) {
		if(sysrole.getId()!=null){
			sysroleMapper.update(sysrole);
		} else {
			sysroleMapper.insert(sysrole);
		}
	}

	public SysRole getById(Long id) {
		return sysroleMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return sysroleMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return  sysroleMapper.getMapListByParm(parmMap);
	}
	
	public List<SysRole> getBeanListByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return sysroleMapper.getBeanListByParm(parmMap);
	}

	public List<Map<String, Object>> getSysUserByRoleCode(String roleCode) {
		return sysroleMapper.getSysUserByRoleCode(roleCode);
	}

	public List<String> getListByUserId(Long userId) {
		return sysroleMapper.getListByUserId(userId);
	}

	public void saveMenu(Long roleId, String menuIds) {
		sysMenuRoleMapper.deleteByRoleId(roleId);
		String[] arr = menuIds.split(",");
		for(String str : arr){
			str = str.trim();
			if(StringUtils.isNotBlank(str)){
				SysMenuRole sysMenuRole = new SysMenuRole();
				sysMenuRole.setSysRoleId(roleId);
				sysMenuRole.setSysMenuId(str);
				sysMenuRoleMapper.insert(sysMenuRole);
			}
		}
	}


}
