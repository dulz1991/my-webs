package com.demo.my.base.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.User;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserMapper;

@Component  
public class UserService extends AbstractBaseService {
	
	@Autowired
    UserMapper userMapper;
    
    public int insert(User user) {
		return userMapper.insert(user);
	}
    
    public int update(User user) {
		return userMapper.update(user);
	}
	
	public int delete(Long id) {
		return userMapper.delete(id);
	}
	
	public void save(User user) {
		if(user.getId()!=null){
			userMapper.update(user);
		} else {
			userMapper.insert(user);
		}
	}

	public User getById(Long id) {
		return userMapper.getById(id);
	}
	
	public User findUserByUsernameAndPassword(User user) {
		return userMapper.findUserByUsernameAndPassword(user);
	}

	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	public int countByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return userMapper.countByParm(parm);
	}
}
