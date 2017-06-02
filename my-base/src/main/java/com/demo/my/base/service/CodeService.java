package com.demo.my.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.Page;
import com.demo.my.base.model.Code;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeMapper;
import com.demo.my.base.service.common.AdapterService;


@Component  
public class CodeService extends AdapterService {
	
	@Autowired
    CodeMapper codeMapper;
    
    public int insert(Code code) {
    	code.setCreateTime(new Date());
    	code.setUpdateTime(new Date());
    	code.setStatus(0);
		return codeMapper.insert(code);
	}
    
    public int update(Code code) {
    	code.setUpdateTime(new Date());
		return codeMapper.update(code);
	}
	
	public int delete(Long id) {
		return codeMapper.delete(id);
	}
	
	public void save(Code code) {
		if(code.getId()!=null){
			this.update(code);
		} else {
			this.insert(code);
		}
	}

	public Code getById(Long id) {
		return codeMapper.getById(id);
	}
	
	public int countByParm(Code code) {
		Map<String, Object> parm = queryParm(code);
		return codeMapper.countByParm(parm);
	}
	
	public Page<Code> getBeanListByParm(Code code, int pageNo, Integer pageSize, String orderby) {
		Page<Code> page = new Page<Code>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(code, page, orderby);
		
		int count = codeMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Code> list = new ArrayList<Code>();
		if(count!=0){
			list = codeMapper.getBeanListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Code code,int pageNo, Integer pageSize, String orderby) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		Map<String, Object> parm = queryParm(code, page, orderby);
		
		int count = codeMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = codeMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	public Page<Map<String, Object>> getMapListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo, pageSize);
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		int count = codeMapper.countByParm(parm);
		page.setTotalRecords(count);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(count!=0){
			list = codeMapper.getMapListByParm(parm);
		}
		page.setList(list);
		
		return page;
	}
	
	private Map<String, Object> queryParm(Code code, Page page, String orderby) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("start", page.getStartRow());
		parm.put("limit", page.getPageSize());
		
		if(code!=null){
			if(code.getCodeId()!=null && code.getCodeId()>0){
				parm.put("codeId", code.getCodeId());
			}
			if(code.getFatherId()!=null && code.getFatherId()>0){
				parm.put("fatherId", code.getFatherId());
			}
			if(code.getCodeLevel()!=null && code.getCodeLevel()>0){
				parm.put("codeLevel", code.getCodeLevel());
			}
			if(StringUtils.isNotBlank(code.getItem())){
				parm.put("item", code.getItem());
			}
		}
		
		if(StringUtils.isNotBlank(orderby)){
			parm.put("orderby", orderby);
		} else {
			parm.put("orderby", "id desc" );
		}
		
		return parm;
	}
	
	private Map<String, Object> queryParm(Code code) {
		Map<String, Object> parm = new HashMap<String, Object>();
		if(code!=null){
			if(code.getCodeId()!=null && code.getCodeId()>0){
				parm.put("codeId", code.getCodeId());
			}
			if(code.getFatherId()!=null && code.getFatherId()>0){
				parm.put("fatherId", code.getFatherId());
			}
			if(StringUtils.isNotBlank(code.getItem())){
				parm.put("item", code.getItem());
			}
			if(code.getCodeLevel()!=null && code.getCodeLevel()>0){
				parm.put("codeLevel", code.getCodeLevel());
			}
		}
		parm.put("orderby", "id desc" );
		return parm;
	}

}
