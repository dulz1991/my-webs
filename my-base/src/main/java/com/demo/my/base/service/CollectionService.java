package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.Page;
import com.demo.my.base.model.Collection;
import com.demo.my.base.mybatis.mapper.ds1mapper.CollectionMapper;
import com.demo.my.base.service.common.AdapterService;


@Component  
public class CollectionService extends AdapterService {
	
	@Autowired
    CollectionMapper collectionMapper;
    
    public int insert(Collection collection) {
		return collectionMapper.insert(collection);
	}
    
    public int update(Collection collection) {
		return collectionMapper.update(collection);
	}
	
	public int delete(Long id) {
		return collectionMapper.delete(id);
	}
	
	public void save(Collection collection) {
		if(collection.getId()!=null){
			collectionMapper.update(collection);
		} else {
			collectionMapper.insert(collection);
		}
	}

	public Collection getById(Long id) {
		return collectionMapper.getById(id);
	}
	
	public int countByParm(Collection collection) {
		Map<String, Object> parm = queryParm(collection);
		return collectionMapper.countByParm(parm);
	}
	
	public Page<Collection> getBeanListByParm(Collection collection, int pageNo, Integer pageSize, String orderby) {
		Page<Collection> page = new Page<Collection>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(collection, page, orderby);
		
		int count = collectionMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Collection> list = new ArrayList<Collection>();
		if(count!=0){
			list = collectionMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Collection collection,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(collection, page, orderby);
		
		int count = collectionMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = collectionMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = collectionMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = collectionMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(Collection collection, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(collection!=null){
			
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(Collection collection) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(collection!=null){
			
		}
		parm.put("orderby", "id desc" );
		return parm;
	}
	
	public List<Map<String, Object>> getMyCollectionForApp(Long userId, Long colId, String type,String orderby){
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("id", colId);
		parmMap.put("userId", userId);
		if(StringUtils.isNotBlank(orderby)){
			parmMap.put("orderby", orderby);	
		}
		if(StringUtils.isNotBlank(type)){
			parmMap.put("type", type);	
		}
		parmMap.put("limit", 10);
		
		return collectionMapper.getMyCollectionForApp(parmMap);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		return collectionMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  collectionMapper.getMapListByParm(parm);
	}
	
	public List<Collection> getBeanListByParm(Map<String, Object> parm) {
		return collectionMapper.getBeanListByParm(parm);
	}
	
	public Page<Collection> getPageBeanByParm(Map<String, Object> parm) {
		Page<Collection> page = new Page<Collection>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Collection> list = new ArrayList<Collection>();
		if(count!=0){
			list = this.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getPageMapByParm(Map<String, Object> parm) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>((Integer)parm.get("pageNo"), (Integer)parm.get("pageSize"));
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = this.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = this.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}

}
