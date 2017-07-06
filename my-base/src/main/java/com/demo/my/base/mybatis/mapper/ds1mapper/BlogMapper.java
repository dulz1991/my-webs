package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.Blog;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface BlogMapper extends BaseMapper {

	int insert(Blog Blog);
	
	int delete(@Param("id") Long id);
	
	int update(Blog Blog);
	
	Blog getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<Blog> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	int getMapListByParm_count(@Param("parm") Map<String, Object> parm);

}
