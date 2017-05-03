package com.demo.my.base.servicebean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.my.base.bean.Role;
import com.demo.my.base.bean.User;
import com.demo.my.base.mybatis.mapper.ds1mapper.RoleMapper;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserMapper;
import com.demo.my.base.service.AbstractService;
import com.demo.my.base.util.PageUtil;

@Service("userServiceBean")  
public class UserServiceBean extends AbstractService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;

	public void insert(User user) {
		userMapper.insert(user);
	}

	public void update(User user) {
		userMapper.update(user);
	}

	public List<User> getByParm(PageUtil page, User user) {
		return userMapper.getByParm(page, user);
	}

	public User getById(Long id) {
		return userMapper.getById(id);
	}

	public Integer getByParm_count(User user) {
		return userMapper.getByParm_count(user);
	}

	public User findUserByUsernameAndPassword(User user) {
		return userMapper.findUserByUsernameAndPassword(user);
	}

	public Role getByRoleName(String name) {
		return roleMapper.getByName(name);
	}

	public Role getByRoleId(Long id) {
		return roleMapper.getById(id);
	}

	public List<Role> getList() {
		return roleMapper.getList();
	}

	public Role getRoleByUsername(String username) {
		return roleMapper.getRoleByUsername(username);
	}

	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

}
