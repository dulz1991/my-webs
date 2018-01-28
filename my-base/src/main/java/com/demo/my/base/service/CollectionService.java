package com.demo.my.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.Collection;
import com.demo.my.base.mybatis.mapper.ds1mapper.CollectionMapper;

@Component  
public class CollectionService extends AbstractBaseService {
	
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
		if(parmMap==null){
			parmMap = new HashMap<String, Object>();
		}
		return collectionMapper.countByParm(parmMap);
	}
	
	public int countByParm(Collection c) {
		if(c==null){
			c = new Collection();
		}
		return collectionMapper.countByParm(c);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  collectionMapper.getMapListByParm(parm);
	}
	
	public List<Collection> getBeanListByParm(Map<String, Object> parm) {
		return collectionMapper.getBeanListByParm(parm);
	}
	
	public boolean deleteByDiscoveryId(Collection collection) {
		int  count = collectionMapper.deleteByDiscoveryId(collection);
		if(count==0){
			return false;
		}
		return true;
	}

}
