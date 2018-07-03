package com.demo.my.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			if(demoMenu.getParentId()==null){
				demoMenu.setParentId(0L);
			}
			demoMenuMapper.insert(demoMenu);
		}
	}

	public DemoMenu getById(Long id) {
		return demoMenuMapper.getById(id);
	}
	
	public List<DemoMenu> getList(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return demoMenuMapper.getBeanListByParm(parm);
	}
	
	public List<Map<String, Object>> getForTree(){
		return demoMenuMapper.getForTree();
	}
	
}
