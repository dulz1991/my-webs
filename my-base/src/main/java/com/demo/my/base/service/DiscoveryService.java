package com.demo.my.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.DateUtil;
import com.demo.my.base.model.Discovery;
import com.demo.my.base.mybatis.mapper.ds1mapper.DiscoveryMapper;

@Component  
public class DiscoveryService extends AbstractBaseService {
	
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
	
	public List<Map<String, Object>> getMapListForDrag(Long discoveryId, int pageSize, String type){
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("id", discoveryId);
		parmMap.put("pageSize", pageSize);
		parmMap.put("type", type);
		
		List<Map<String, Object>> list = discoveryMapper.getMapListForDrag(parmMap);
		String imagePath = "";
		String content = "";
		for(Map<String, Object> map : list){
			
			imagePath = map.get("imagePath")==null?"":map.get("imagePath").toString();
			if(StringUtils.isBlank(imagePath)){
				map.put("hasImg", false);
			} else {
				map.put("hasImg", true);
			}
			
			Date createTime = (Date) map.get("createTime");
			map.put("createTimeStr", DateUtil.calcDatetime(createTime));
			
			content = map.get("content")==null?"":map.get("content").toString();
			if(content.length()>140){
				map.put("content", content.substring(0, 140)+"...");
			}
			
		}
		
		return list;
	}
	
	public int getMyCommentDiscoveryCount(Map<String, Object> parm) {
		if(parm == null){
			parm = new HashMap<String, Object>();
		}
		return discoveryMapper.getMyCommentDiscoveryCount(parm);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		if(parmMap==null){
			parmMap = new HashMap<String, Object>();
		}
		return discoveryMapper.countByParm(parmMap);
	}
	public int countByParm(Discovery d) {
		if(d==null){
			d = new Discovery();
		}
		return discoveryMapper.countByParm(d);
	}
}
