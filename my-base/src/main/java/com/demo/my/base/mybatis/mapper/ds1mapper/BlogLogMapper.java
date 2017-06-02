package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.BlogLog;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface BlogLogMapper extends BaseMapper {

	int insert(BlogLog BlogLog);
	
	int delete(@Param("id") Long id);
	
	int update(BlogLog BlogLog);
	
	BlogLog getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<BlogLog> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
