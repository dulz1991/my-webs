package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.CodeSubMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface CodeSubMenuMapper extends BaseMapper {

	int insert(CodeSubMenu CodeSubMenu);
	
	int delete(@Param("id") Long id);
	
	int update(CodeSubMenu CodeSubMenu);
	
	CodeSubMenu getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<CodeSubMenu> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
