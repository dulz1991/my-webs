package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.SysUserRole;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;


public interface SysUserRoleMapper extends BaseMapper {

	int insert(SysUserRole sysUserRole);
	
	int delete(@Param("id") Long id);
	
	int update(SysUserRole sysUserRole);
	
	SysUserRole getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<SysUserRole> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	void deleteByUserId(@Param("userId") Long userId);

}
