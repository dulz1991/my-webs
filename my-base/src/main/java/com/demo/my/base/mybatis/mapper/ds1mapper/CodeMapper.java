package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.Code;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface CodeMapper extends BaseMapper {

	int insert(Code Code);
	
	int delete(@Param("id") Long id);
	
	int update(Code Code);
	
	Code getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<Code> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
