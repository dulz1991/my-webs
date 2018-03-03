package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.Active;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface ActiveMapper extends BaseMapper {

	int insert(Active Active);
	
	int delete(@Param("id") Long id);
	
	int update(Active Active);
	
	Active getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<Active> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
