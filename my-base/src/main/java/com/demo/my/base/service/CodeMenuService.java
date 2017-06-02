package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.my.base.util.Page;

import com.demo.my.base.model.CodeMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeMenuMapper;

import com.demo.my.base.service.common.AdapterService;


@Component  
public class CodeMenuService extends AdapterService {
	
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
	
	public int countByParm(CodeMenu codeMenu) {
		Map<String, Object> parm = queryParm(codeMenu);
		return codeMenuMapper.countByParm(parm);
	}
	
	public Page<CodeMenu> getBeanListByParm(CodeMenu codeMenu, int pageNo, Integer pageSize, String orderby) {
		Page<CodeMenu> page = new Page<CodeMenu>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(codeMenu, page, orderby);
		
		int count = codeMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<CodeMenu> list = new ArrayList<CodeMenu>();
		if(count!=0){
			list = codeMenuMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(CodeMenu codeMenu,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(codeMenu, page, orderby);
		
		int count = codeMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = codeMenuMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = codeMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = codeMenuMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(CodeMenu codeMenu, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(codeMenu!=null){
			if(StringUtils.isNotBlank(codeMenu.getName())){
				parm.put("name", codeMenu.getName());
			}
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(CodeMenu codeMenu) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(codeMenu!=null){
			if(StringUtils.isNotBlank(codeMenu.getName())){
				parm.put("name", codeMenu.getName());
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
