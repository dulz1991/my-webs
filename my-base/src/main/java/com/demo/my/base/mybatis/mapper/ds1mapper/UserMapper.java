package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.User;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface UserMapper extends BaseMapper {

	int insert(User User);
	
	int delete(@Param("id") Long id);
	
	int update(User User);
	
	User getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<User> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);
	
	User findUserByUsernameAndPassword(@Param("user") User user);

	User getByUsername(@Param("username") String username);

}
