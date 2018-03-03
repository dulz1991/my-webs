package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.ActiveUser;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface ActiveUserMapper extends BaseMapper {

	int insert(ActiveUser ActiveUser);
	
	int delete(@Param("id") Long id);
	
	int update(ActiveUser ActiveUser);
	
	ActiveUser getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<ActiveUser> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

}
