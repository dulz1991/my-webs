package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.BlogMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface BlogMenuMapper extends BaseMapper {

	int insert(BlogMenu BlogMenu);
	
	int delete(@Param("id") Long id);
	
	int update(BlogMenu BlogMenu);
	
	BlogMenu getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<BlogMenu> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
