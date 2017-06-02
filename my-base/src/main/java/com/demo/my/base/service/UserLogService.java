package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.UserLog;
import com.demo.my.base.mybatis.mapper.ds1mapper.UserLogMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class UserLogService extends AdapterService {
	
	@Autowired
    UserLogMapper userLogMapper;
    
    public int insert(UserLog userLog) {
		return userLogMapper.insert(userLog);
	}
    
    public int update(UserLog userLog) {
		return userLogMapper.update(userLog);
	}
	
	public int delete(Long id) {
		return userLogMapper.delete(id);
	}
	
	public void save(UserLog userLog) {
		if(userLog.getId()!=null){
			userLogMapper.update(userLog);
		} else {
			userLogMapper.insert(userLog);
		}
	}

	public UserLog getById(Long id) {
		return userLogMapper.getById(id);
	}
	
	public int countByParm(UserLog userLog) {
		Map<String, Object> parm = queryParm(userLog);
		return userLogMapper.countByParm(parm);
	}
	
	public Page<UserLog> getBeanListByParm(UserLog userLog, int pageNo, Integer pageSize, String orderby) {
		Page<UserLog> page = new Page<UserLog>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(userLog, page, orderby);
		
		int count = userLogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<UserLog> list = new ArrayList<UserLog>();
		if(count!=0){
			list = userLogMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(UserLog userLog,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(userLog, page, orderby);
		
		int count = userLogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userLogMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = userLogMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = userLogMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(UserLog userLog, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(userLog!=null){
			
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(UserLog userLog) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(userLog!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
