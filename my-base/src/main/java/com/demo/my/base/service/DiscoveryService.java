package com.demo.my.base.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.Page;
import com.demo.my.base.model.Discovery;
import com.demo.my.base.mybatis.mapper.ds1mapper.DiscoveryMapper;
import com.demo.my.base.service.common.AdapterService;


@Component  
public class DiscoveryService extends AdapterService {
	
	@Autowired
    DiscoveryMapper discoveryMapper;
    
    public int insert(Discovery discovery) {
		return discoveryMapper.insert(discovery);
	}
    
    public int update(Discovery discovery) {
		return discoveryMapper.update(discovery);
	}
	
	public int delete(Long id) {
		return discoveryMapper.delete(id);
	}
	
	public void save(Discovery discovery) {
		if(discovery.getId()!=null){
			discoveryMapper.update(discovery);
		} else {
			discoveryMapper.insert(discovery);
		}
	}

	public Discovery getById(Long id) {
		return discoveryMapper.getById(id);
	}
	
	public Map<String, Object> getMapById(Long id) {
		return discoveryMapper.getMapById(id);
	}
	
	public int countByParm(Discovery discovery) {
		Map<String, Object> parm = queryParm(discovery);
		return discoveryMapper.countByParm(parm);
	}
	
	public Page<Discovery> getBeanListByParm(Discovery discovery, int pageNo, Integer pageSize, String orderby) {
		Page<Discovery> page = new Page<Discovery>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(discovery, page, orderby);
		
		int count = discoveryMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Discovery> list = new ArrayList<Discovery>();
		if(count!=0){
			list = discoveryMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Discovery discovery,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(discovery, page, orderby);
		
		int count = discoveryMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = discoveryMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = discoveryMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = discoveryMapper.getMapListByParm(parm);
			String imagePath = "";
			for(Map<String, Object> map : list){
				imagePath = map.get("imgPath")==null?"":map.get("imgPath").toString();
				if(StringUtils.isBlank(imagePath)){
					map.put("hasImg", false);
				} else {
					map.put("hasImg", true);
				}
			}
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(Discovery discovery, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(discovery!=null){
			if(discovery.getStatus()!=null && discovery.getStatus()>0){
				parm.put("status", discovery.getStatus());
			}
			if(discovery.getUserId()!=null && discovery.getUserId()>0){
				parm.put("userId", discovery.getUserId());
			}
			if(StringUtils.isNotBlank(discovery.getTitle())){
				parm.put("title", discovery.getTitle());
			}
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(Discovery discovery) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(discovery!=null){
			if(discovery.getStatus()!=null && discovery.getStatus()>0){
				parm.put("status", discovery.getStatus());
			}
			if(discovery.getUserId()!=null && discovery.getUserId()>0){
				parm.put("userId", discovery.getUserId());
			}
			if(StringUtils.isNotBlank(discovery.getTitle())){
				parm.put("title", discovery.getTitle());
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
