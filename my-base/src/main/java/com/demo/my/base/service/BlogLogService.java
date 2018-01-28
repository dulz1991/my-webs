package com.demo.my.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.BlogLog;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogLogMapper;

@Component  
public class BlogLogService extends AbstractBaseService {
	
	@Autowired
    BlogLogMapper blogLogMapper;
    
    public int insert(BlogLog blogLog) {
		return blogLogMapper.insert(blogLog);
	}
    
    public int update(BlogLog blogLog) {
		return blogLogMapper.update(blogLog);
	}
	
	public int delete(Long id) {
		return blogLogMapper.delete(id);
	}
	
	public void save(BlogLog blogLog) {
		if(blogLog.getId()!=null){
			blogLogMapper.update(blogLog);
		} else {
			blogLogMapper.insert(blogLog);
		}
	}

	public BlogLog getById(Long id) {
		return blogLogMapper.getById(id);
	}
	
	public Map<String, Object> getDetail(Long id) {
		return blogLogMapper.getDetail(id);
	}
	
	public int countByParm(Map<String, Object> parmMap) {
		return blogLogMapper.countByParm(parmMap);
	}
	
	public List<Map<String, Object>> getMapListByParm(Map<String, Object> parm) {
		return  blogLogMapper.getMapListByParm(parm);
	}
	
	public List<BlogLog> getBeanListByParm(Map<String, Object> parm) {
		return blogLogMapper.getBeanListByParm(parm);
	}
	
}
