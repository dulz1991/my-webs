package com.demo.my.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeMenuMapper;

@Component  
public class CodeMenuService extends AbstractBaseService {
	
	@Autowired
    CodeMenuMapper codeMenuMapper;
    
    public int insert(CodeMenu codeMenu) {
		return codeMenuMapper.insert(codeMenu);
	}
    
    public int update(CodeMenu codeMenu) {
		return codeMenuMapper.update(codeMenu);
	}
	
	public int delete(Long id) {
		return codeMenuMapper.delete(id);
	}
	
	public void save(CodeMenu codeMenu) {
		if(codeMenu.getId()!=null){
			codeMenuMapper.update(codeMenu);
		} else {
			codeMenuMapper.insert(codeMenu);
		}
	}

	public CodeMenu getById(Long id) {
		return codeMenuMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return codeMenuMapper.countByParm(parm);
	}
	
	public List<Map<String, Object>> getListForZtree() {
		return codeMenuMapper.getListForZtree();
	}
}
