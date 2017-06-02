package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.Page;
import com.demo.my.base.model.CodeSubMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeSubMenuMapper;
import com.demo.my.base.service.common.AdapterService;

@Component  
public class CodeSubMenuService extends AdapterService {
	
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
			codeSubMenuMapper.insert(codeSubMenu);
		}
	}

	public CodeSubMenu getById(Long id) {
		return codeSubMenuMapper.getById(id);
	}
	
	public int countByParm(CodeSubMenu codeSubMenu) {
		Map<String, Object> parm = queryParm(codeSubMenu);
		return codeSubMenuMapper.countByParm(parm);
	}
	
	public Page<CodeSubMenu> getBeanListByParm(CodeSubMenu codeSubMenu, int pageNo, Integer pageSize, String orderby) {
		Page<CodeSubMenu> page = new Page<CodeSubMenu>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(codeSubMenu, page, orderby);
		
		int count = codeSubMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<CodeSubMenu> list = new ArrayList<CodeSubMenu>();
		if(count!=0){
			list = codeSubMenuMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(CodeSubMenu codeSubMenu,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(codeSubMenu, page, orderby);
		
		int count = codeSubMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = codeSubMenuMapper.getMapListByParm(parm);
			for(Map<String, Object> map : list){
				String menuStatus = map.get("menuStatus").toString();
				if(menuStatus.equals("0")){
					map.put("statusStr", "未开始");
				} else if(menuStatus.equals("1")){
					map.put("statusStr", "编辑中");
				} else if(menuStatus.equals("2")){
					map.put("statusStr", "已完成");
				}
			}
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = codeSubMenuMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = codeSubMenuMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(CodeSubMenu codeSubMenu, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(codeSubMenu!=null){
			if(codeSubMenu.getFatherId()!=null && codeSubMenu.getFatherId()>=0){
				parm.put("fatherId", codeSubMenu.getFatherId());
			}
			if(codeSubMenu.getStatus()!=null && codeSubMenu.getStatus()>=0){
				parm.put("status", codeSubMenu.getStatus());
			}
			if(StringUtils.isNotBlank(codeSubMenu.getName())){
				parm.put("name", codeSubMenu.getName());
			}
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(CodeSubMenu codeSubMenu) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(codeSubMenu!=null){
			if(codeSubMenu.getFatherId()!=null && codeSubMenu.getFatherId()>=0){
				parm.put("fatherId", codeSubMenu.getFatherId());
			}
			if(codeSubMenu.getStatus()!=null && codeSubMenu.getStatus()>=0){
				parm.put("status", codeSubMenu.getStatus());
			}
			if(StringUtils.isNotBlank(codeSubMenu.getName())){
				parm.put("name", codeSubMenu.getName());
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
