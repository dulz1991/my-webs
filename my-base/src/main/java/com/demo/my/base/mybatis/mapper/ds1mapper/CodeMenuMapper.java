package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface CodeMenuMapper extends BaseMapper {

	int insert(CodeMenu CodeMenu);
	
	int delete(@Param("id") Long id);
	
	int update(CodeMenu CodeMenu);
	
	CodeMenu getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<CodeMenu> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getListForZtree();

}
