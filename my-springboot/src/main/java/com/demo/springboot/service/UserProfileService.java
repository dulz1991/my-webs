package com.demo.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.springboot.bean.UserProfile;
import com.demo.springboot.mybatis.mapper.UserProfileMapper;

@Component
public class UserProfileService {
	
	@Autowired
	private UserProfileMapper userProfileMapper;
	
	public UserProfile getById(Long userId){
		return userProfileMapper.getById(userId);
	}
}
