package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.Demo;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface DemoMapper extends BaseMapper {

	int insert(Demo Demo);
	
	int delete(@Param("id") Long id);
	
	int update(Demo Demo);
	
	Demo getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<Demo> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
