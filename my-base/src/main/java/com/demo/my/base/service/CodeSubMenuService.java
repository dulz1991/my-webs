package com.demo.my.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.enums.EnumCodeSubMenuStatus;
import com.demo.my.base.model.CodeSubMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeSubMenuMapper;

@Component  
public class CodeSubMenuService extends AbstractBaseService {
	
	@Autowired
    CodeSubMenuMapper codeSubMenuMapper;
    
    public int insert(CodeSubMenu codeSubMenu) {
		return codeSubMenuMapper.insert(codeSubMenu);
	}
    
    public int update(CodeSubMenu codeSubMenu) {
		return codeSubMenuMapper.update(codeSubMenu);
	}
	
	public int delete(Long id) {
		return codeSubMenuMapper.delete(id);
	}
	
	public void save(CodeSubMenu codeSubMenu) {
		if(codeSubMenu.getId()!=null){
			codeSubMenuMapper.update(codeSubMenu);
		} else {
			codeSubMenu.setStatus(EnumCodeSubMenuStatus.NOT_START.getKey());
			codeSubMenuMapper.insert(codeSubMenu);
		}
	}

	public CodeSubMenu getById(Long id) {
		return codeSubMenuMapper.getById(id);
	}
	
	public List<CodeSubMenu> getBeanListByParm(Map<String, Object> parm) {
		return codeSubMenuMapper.getBeanListByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return codeSubMenuMapper.countByParm(parm);
	}
	
}
