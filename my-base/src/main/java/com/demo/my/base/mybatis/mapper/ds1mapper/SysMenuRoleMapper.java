package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.SysMenuRole;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface SysMenuRoleMapper extends BaseMapper {

	int insert(SysMenuRole sysMenuRole);
	
	int delete(@Param("id") Long id);
	
	int update(SysMenuRole sysMenuRole);
	
	SysMenuRole getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<SysMenuRole> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	void deleteByRoleId(@Param("roleId")Long roleId);

}
