package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.UserRole;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface UserRoleMapper extends BaseMapper {

	int insert(UserRole UserRole);
	
	int delete(@Param("id") Long id);
	
	int update(UserRole UserRole);
	
	UserRole getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<UserRole> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
