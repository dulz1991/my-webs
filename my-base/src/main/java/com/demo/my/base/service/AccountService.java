package com.demo.my.base.service;

import java.util.Map;

import com.demo.my.base.bean.User;

public interface AccountService {
	
	Map<String, Object> login(User user);
	
	Map<String, Object> logout();

}
