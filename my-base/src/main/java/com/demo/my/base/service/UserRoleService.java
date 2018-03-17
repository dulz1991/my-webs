package com.demo.my.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.UserRole;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserRoleMapper;
import com.demo.my.base.util.HtmlUtil;

@Component  
public class UserRoleService extends AbstractBaseService {
	
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
	
	public List<UserRole> getBeanListByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return userRoleMapper.getBeanListByParm(parm);
	}
	
	public String getForSelect(String roleId) {
		List<UserRole> userRoles = this.getBeanListByParm(null);
		Map<Object, Object> map = new HashMap<Object, Object>();
		for(UserRole role :userRoles){
			map.put(role.getId(), role.getRoleName());
		}
		String selectHtml = HtmlUtil.SelectHtml("role", roleId, "", map);
		return selectHtml;
	}
}
