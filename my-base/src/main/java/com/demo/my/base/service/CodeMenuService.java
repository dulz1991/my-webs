package com.demo.my.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.enums.EnumCodeMenuStatus;
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
			codeMenu.setStatus(EnumCodeMenuStatus.EDIT.getKey()+"");
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
	
	public Map<Object, Object> getListForSelect() {
		List<CodeMenu> list = codeMenuMapper.getBeanListByParm(new HashMap<String, Object>());
		Map<Object, Object> map = new HashMap<Object, Object>();
		for(CodeMenu codeMenu : list){
			map.put(codeMenu.getId(), codeMenu.getName());
		}
		return map;
	}
	
	public List<CodeMenu> getBeanListByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return codeMenuMapper.getBeanListByParm(parm);
	}
}
