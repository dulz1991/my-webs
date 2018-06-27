package com.demo.my.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.SysMenu;
import com.demo.my.base.mybatis.mapper.ds1mapper.SysMenuMapper;
import com.demo.my.base.service.common.AdapterService;
import com.demo.my.base.util.Page;

@Component  
public class SysMenuService extends AbstractBaseService {
	
	@Autowired
    SysMenuMapper sysMenuMapper;
    
    public boolean insert(SysMenu sysMenu) {
    	sysMenu.setCreateTime(new Date());
    	sysMenu.setUpdateTime(new Date());
    	int result = sysMenuMapper.insert(sysMenu);
		return result > 0;
	}
    
    public boolean update(SysMenu sysMenu) {
    	sysMenu.setUpdateTime(new Date());
    	int result = sysMenuMapper.update(sysMenu);
		return result > 0;
	}
	
	public boolean delete(Long id) {
		int result = sysMenuMapper.delete(id);
		return result > 0;
	}
	
	public SysMenu getById(Long id) {
		return sysMenuMapper.getById(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return sysMenuMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return  sysMenuMapper.getMapListByParm(parmMap);
	}
	
	public List<SysMenu> getBeanListByParm(Map<String, Object> parmMap) {
		if(parmMap == null){
			parmMap = new HashMap<String, Object>();
		}
		return sysMenuMapper.getBeanListByParm(parmMap);
	}
	
	public Page<SysMenu> getList(Map<String, Object> parmMap) {
		return this.getPage("SysMenuMapper.getBeanListByParm", parmMap);
	}

	public List<Map<String, Object>> getByUserId(Long userId) {
		List<SysMenu> list1 = sysMenuMapper.getListByUserId(userId,1);
		List<SysMenu> list2 = sysMenuMapper.getListByUserId(userId,2);
		List<Map<String, Object>> menus = new ArrayList<Map<String,Object>>();
		
		if(list1!=null && list1.size()>0){
			for(SysMenu menu : list1){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", menu.getId());
				map.put("icon", menu.getIcon());
				map.put("name", menu.getMenuName());
				map.put("parentId", menu.getParentId());
				map.put("url", menu.getUrl());

				/*遍历二级*/
				if(list2!=null && list2.size()>0){
					List<Map<String, Object>> subMenus = new ArrayList<Map<String,Object>>();	
					for(SysMenu subMenu : list2){
						if(subMenu.getParentId().longValue()==menu.getId().longValue()){
							Map<String, Object> subMap = new HashMap<String, Object>();
							subMap.put("id", subMenu.getId());
							subMap.put("icon", subMenu.getIcon());
							subMap.put("name", subMenu.getMenuName());
							subMap.put("url", subMenu.getUrl());
							subMap.put("parentId", subMenu.getParentId());
							subMenus.add(subMap);
						}
					}
					if(subMenus.size()>0){
						map.put("menus", subMenus);	
					}
				}
				
				menus.add(map);
			}
		}
		return menus;
	}

	public List<SysMenu> getLevelOneMenuList() {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("menuLevel", 1);
		return sysMenuMapper.getBeanListByParm(parmMap);
	}

	public SysMenu getLast() {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("orderby", "PRIORITY DESC");
		parmMap.put("start", "0");
		parmMap.put("limit", "1");
		List<SysMenu> list = sysMenuMapper.getBeanListByParm(parmMap);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<Map<String, Object>> getMenuTree(Long roleId) {
		List<Map<String, Object>> menuList = sysMenuMapper.getMenuTree();
		List<String> selectedMenuIds = getSelectedMenuId(roleId);
		for(Map<String, Object> map : menuList){
			for(String str :selectedMenuIds){
				if(str.equals(toString(map.get("id")))){
					map.put("checked", true);
					break;
				}
			}
		}
		return menuList;
	}

	public List<SysMenu> getSelectedMenuForRole(Long roleId) {
		return sysMenuMapper.getSelectedMenuForRole(roleId);
	}

	public List<String> getSelectedMenuId(Long roleId) {
		List<SysMenu> list = getSelectedMenuForRole(roleId);
		List<String> ids = new ArrayList<String>();
		if(list!=null){
			for(SysMenu menu : list){
				ids.add(menu.getId()+"");
			}
		}
		return ids;
	}
	
	
}
