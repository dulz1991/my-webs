package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.CodeSubMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface CodeSubMenuMapper extends BaseMapper {

	List<CodeSubMenu> getBeanListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);

	CodeSubMenu getById(@Param("id") Long subMenuId);

	int insert(CodeSubMenu codeSubMenu);

	int update(CodeSubMenu codeSubMenu);

	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
}
