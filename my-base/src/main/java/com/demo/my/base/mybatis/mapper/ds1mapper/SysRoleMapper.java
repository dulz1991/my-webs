package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.SysRole;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;


public interface SysRoleMapper extends BaseMapper {

	int insert(SysRole sysrole);
	
	int delete(@Param("id") Long id);
	
	int update(SysRole sysrole);
	
	SysRole getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<SysRole> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getSysUserByRoleCode(@Param("roleCode") String roleCode);

	List<String> getListByUserId(@Param("userId") Long userId);

}
