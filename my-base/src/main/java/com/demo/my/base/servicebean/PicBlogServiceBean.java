package com.demo.my.base.servicebean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.my.base.bean.PicBlog;
import com.demo.my.base.bean.PicBlogLog;
import com.demo.my.base.mybatis.mapper.ds2mapper.PicBlogLogMapper;
import com.demo.my.base.mybatis.mapper.ds2mapper.PicBlogMapper;
import com.demo.my.base.service.AbstractService;

@Service("picBlogServiceBean")  
public class PicBlogServiceBean extends AbstractService {
	
	@Autowired
	private PicBlogMapper picBlogMapper;
	@Autowired
	private PicBlogLogMapper picBlogLogMapper;
	
	/*以下是pic blog部分*/
	/*@Transactional*/
	public void insert(PicBlog pic) {
		picBlogMapper.insert(pic);
		PicBlogLog log = new PicBlogLog();
		log.setPicBlogId(pic.getId());
		log.setRemark("创建游记："+pic.getTitle());
		log.setUserId(pic.getUserId());
		picBlogLogMapper.insert(log);
	}

	public void update(PicBlog pic) {
		picBlogMapper.update(pic);	
		PicBlogLog log = new PicBlogLog();
		log.setPicBlogId(pic.getId());
		log.setRemark("更新游记："+pic.getTitle());
		log.setUserId(getCurrentUserId());
		picBlogLogMapper.insert(log);
	}

	/*以下是pic blog log部分*/
	
}
