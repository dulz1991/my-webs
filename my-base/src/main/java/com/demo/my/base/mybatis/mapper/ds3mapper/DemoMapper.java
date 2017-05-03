package com.demo.my.base.mybatis.mapper.ds3mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.Demo;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface DemoMapper extends BaseMapper {

	int insert(Demo demo);
	
	int update(Demo demo);
	
	Demo getById(@Param("id") Long id);
	
	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
	
	List<Demo> getBeanListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
}
