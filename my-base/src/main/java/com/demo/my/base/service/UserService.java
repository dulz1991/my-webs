package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.Page;
import com.demo.my.base.model.User;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserMapper;
import com.demo.my.base.service.common.AdapterService;

@Component  
public class UserService extends AdapterService {
	
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
	
	public int countByParm(User user) {
		Map<String, Object> parm = queryParm(user);
		return userMapper.countByParm(parm);
	}
	
	public Page<User> getBeanListByParm(User user, int pageNo, Integer pageSize, String orderby) {
		Page<User> page = new Page<User>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(user, page, orderby);
		
		int count = userMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<User> list = new ArrayList<User>();
		if(count!=0){
			list = userMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(User user,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(user, page, orderby);
		
		int count = userMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = userMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(User user, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(user!=null){
			if(StringUtils.isNotBlank(user.getUsername())){
				parm.put("username", user.getUsername());
			}
			if(StringUtils.isNotBlank(user.getPhone())){
				parm.put("phone", user.getPhone());
			}
			if(user.getStatus()!=null && user.getStatus()>=0){
				parm.put("status", user.getStatus());
			}
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(User user) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(user!=null){
			if(StringUtils.isNotBlank(user.getUsername())){
				parm.put("username", user.getUsername());
			}
			if(StringUtils.isNotBlank(user.getPhone())){
				parm.put("phone", user.getPhone());
			}
			if(user.getStatus()!=null && user.getStatus()>0){
				parm.put("status", user.getStatus());
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

	public User findUserByUsernameAndPassword(User user) {
		return userMapper.findUserByUsernameAndPassword(user);
	}

	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

}
