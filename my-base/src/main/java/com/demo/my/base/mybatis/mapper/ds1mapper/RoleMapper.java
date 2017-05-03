package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.Role;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface RoleMapper extends BaseMapper{

	int insert(Role role);
	
	int update(Role role);
	
	Role getById(@Param("id") Long id);
	
	Role getByName(@Param("roleName") String name);
	
	List<Role> getList();

	Role getRoleByUsername(@Param("username") String username);
	
}
