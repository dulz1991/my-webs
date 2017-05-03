package com.demo.my.base.servicebean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.my.base.mybatis.mapper.ds3mapper.DemoMapper;
import com.demo.my.base.mybatis.mapper.ds3mapper.DemoMenuMapper;
import com.demo.my.base.service.AbstractService;

@Service("demoServiceBean")  
public class DemoServiceBean extends AbstractService {
	
	@Autowired
	private DemoMapper demoMapper;
	@Autowired
	private DemoMenuMapper demoMenuMapper;

}
