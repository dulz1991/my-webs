package com.demo.my.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.Demo;
import com.demo.my.base.mybatis.mapper.ds1mapper.DemoMapper;

@Component  
public class DemoService extends AbstractBaseService {
	
	@Autowired
    DemoMapper demoMapper;
    
    public int insert(Demo demo) {
    	demo.setUpdateTime(new Date());
    	demo.setCreateTime(new Date());
    	demo.setDownloadTimes(0);
		return demoMapper.insert(demo);
	}
    
    public int update(Demo demo) {
    	demo.setUpdateTime(new Date());
		return demoMapper.update(demo);
	}
	
	public int delete(Long id) {
		return demoMapper.delete(id);
	}
	
	public void save(Demo demo) {
		if(demo.getId()!=null){
			demoMapper.update(demo);
		} else {
			demoMapper.insert(demo);
		}
	}

	public Demo getById(Long id) {
		return demoMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return demoMapper.countByParm(parm);
	}
	
}
