package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.User;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface UserMapper extends BaseMapper{

	void insert(User user);
	
	void update(User user);
	
	User getById(@Param("userId") Long id);
	
	List<User> getByParm(@Param("page") PageUtil page, @Param("user") User user);
	
	int getByParm_count(@Param("user") User user);

	User findUserByUsernameAndPassword(@Param("user") User user);

	User getByUsername(@Param("username") String username);

}
