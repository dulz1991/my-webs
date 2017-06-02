package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.UserLog;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface UserLogMapper extends BaseMapper {

	int insert(UserLog UserLog);
	
	int delete(@Param("id") Long id);
	
	int update(UserLog UserLog);
	
	UserLog getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<UserLog> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
