package com.demo.my.base.servicebean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.my.base.bean.SysCfg;
import com.demo.my.base.mybatis.mapper.ds1mapper.SysCfgMapper;
import com.demo.my.base.service.AbstractService;
import com.demo.my.base.util.PageUtil;

@Service("sysCfgServiceBean")  
public class SysCfgServiceBean extends AbstractService {
	
	@Autowired
	private SysCfgMapper sysCfgMapper;
	
	public SysCfg getOne() {
		List<SysCfg> list = sysCfgMapper.getBeanListByParm(new PageUtil(), new HashMap<String, Object>());
		if(list==null || list.isEmpty()){
			SysCfg sysCfg = new SysCfg();
			sysCfg.setCountJson("");
			sysCfg.setRemark("");
			sysCfgMapper.insert(sysCfg);
			return sysCfg;
		} else {
			return list.get(0);
		}
	}
	
	public void callProcCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		/*map.put("result", 0);*/
		sysCfgMapper.callProcCount(map);
		System.out.print(map.get("result"));
	}

}
