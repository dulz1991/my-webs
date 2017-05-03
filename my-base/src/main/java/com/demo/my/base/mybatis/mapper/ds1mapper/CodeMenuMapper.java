package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.CodeMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface CodeMenuMapper extends BaseMapper {

	List<CodeMenu> getBeanListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);

	CodeMenu getById(@Param("id") Long id);
	
	int insert(CodeMenu codeMenu);

	int update(CodeMenu codeMenu);

	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
}
