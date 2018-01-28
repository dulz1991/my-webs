package com.demo.my.base.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.UserRole;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserRoleMapper;

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
	
}
