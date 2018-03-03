package com.demo.my.base.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.SysCfg;
import com.demo.my.base.model.User;
import com.demo.my.base.mybatis.mapper.ds1mapper.SysCfgMapper;

@Component  
public class SysCfgService extends AbstractBaseService {
	
	@Autowired
    SysCfgMapper sysCfgMapper;
    
    public int insert(SysCfg sysCfg) {
		return sysCfgMapper.insert(sysCfg);
	}
    
    public int update(SysCfg sysCfg) {
		return sysCfgMapper.update(sysCfg);
	}
	
	public int delete(Long id) {
		return sysCfgMapper.delete(id);
	}
	
	public void save(SysCfg sysCfg) {
		if(sysCfg.getId()!=null){
			sysCfgMapper.update(sysCfg);
		} else {
			sysCfgMapper.insert(sysCfg);
		}
	}

	public SysCfg getById(Long id) {
		return sysCfgMapper.getById(id);
	}
	
	public SysCfg getByKey(String key) {
		return sysCfgMapper.getByKey(key);
	}
	
}
