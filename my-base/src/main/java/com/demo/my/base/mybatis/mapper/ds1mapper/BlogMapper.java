package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.Blog;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface BlogMapper extends BaseMapper {

	int insert(Blog blog);
	
	int update(Blog blog);
	
	Blog getById(@Param("id") Long id);
	
	List<Blog> getBeanListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
	
	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
}
