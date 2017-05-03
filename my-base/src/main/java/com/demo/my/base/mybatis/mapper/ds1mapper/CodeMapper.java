package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.Code;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface CodeMapper extends BaseMapper {

	int insert(Code code);
	
	int update(Code code);
	
	Code getById(@Param("id") Long id);
	
	List<Code> getBeanListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);

	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
}
