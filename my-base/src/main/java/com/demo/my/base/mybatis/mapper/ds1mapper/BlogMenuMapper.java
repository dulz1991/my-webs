package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.BlogMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface BlogMenuMapper extends BaseMapper {

	int insert(BlogMenu blogMenu);
	
	int update(BlogMenu blogMenu);
	
	BlogMenu getById(@Param("id") Long id);
	
	List<BlogMenu> getBeanListByParm(@Param("page")PageUtil pageUtil, @Param("parm")Map<String, Object> parmMap);
	
	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
}
