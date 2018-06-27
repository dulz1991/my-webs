package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.SysMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;


public interface SysMenuMapper extends BaseMapper {

	int insert(SysMenu sysMenu);
	
	int delete(@Param("id") Long id);
	
	int update(SysMenu sysMenu);
	
	SysMenu getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<SysMenu> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	List<SysMenu> getListByUserId(@Param("userId")Long userId,@Param("menuLevel")int menuLevel);

	List<Map<String, Object>> getMenuTree();

	List<SysMenu> getSelectedMenuForRole(@Param("roleId")Long roleId);

}
