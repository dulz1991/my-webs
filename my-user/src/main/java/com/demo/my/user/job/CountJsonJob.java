package com.demo.my.user.job;

import javax.annotation.Resource;

import com.demo.my.base.servicebean.SysCfgServiceBean;

public class CountJsonJob {
	
	@Resource(name = "sysCfgServiceBean")
	private SysCfgServiceBean sysCfgService;
	
	public void run() {
		sysCfgService.callProcCount();
	}

}
