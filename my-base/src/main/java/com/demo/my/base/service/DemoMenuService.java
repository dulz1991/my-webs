package com.demo.my.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.DemoMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.DemoMenuMapper;

@Component  
public class DemoMenuService extends AbstractBaseService {
	
	@Autowired
    DemoMenuMapper demoMenuMapper;
    
    public int insert(DemoMenu demoMenu) {
		return demoMenuMapper.insert(demoMenu);
	}
    
    public int update(DemoMenu demoMenu) {
		return demoMenuMapper.update(demoMenu);
	}
	
	public int delete(Long id) {
		return demoMenuMapper.delete(id);
	}
	
	public void save(DemoMenu demoMenu) {
		if(demoMenu.getId()!=null){
			demoMenuMapper.update(demoMenu);
		} else {
			demoMenuMapper.insert(demoMenu);
		}
	}

	public DemoMenu getById(Long id) {
		return demoMenuMapper.getById(id);
	}
	
}
