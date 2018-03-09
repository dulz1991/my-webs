package com.demo.my.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.my.base.model.Code;
import com.demo.my.base.mybatis.mapper.ds1mapper.CodeMapper;

@Component  
public class CodeService extends AbstractBaseService {
	
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
		Code code = codeMapper.getById(id);
		String contentString = code.getContent().replace("http://127.0.0.1:8095", "/api_img");
		code.setContent(contentString);
		return code;
	}
	
	public int countByParm(Map<String, Object> parm) {
		if(parm==null){
			parm = new HashMap<String, Object>();
		}
		return codeMapper.countByParm(parm);
	}
	
	public List<Map<String, Object>> getCodeListForZtree(Long fatherId){
		return codeMapper.getCodeListForZtree(fatherId);
	}
	
}
