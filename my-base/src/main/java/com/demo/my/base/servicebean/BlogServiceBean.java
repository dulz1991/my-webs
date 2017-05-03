package com.demo.my.base.servicebean;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.my.base.bean.Blog;
import com.demo.my.base.bean.BlogLog;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogLogMapper;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogMapper;
import com.demo.my.base.mybatis.mapper.ds1mapper.BlogMenuMapper;
import com.demo.my.base.service.AbstractService;

@Service("blogServiceBean")  
public class BlogServiceBean extends AbstractService {
	
	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private BlogMenuMapper blogMenuMapper;
	@Autowired
	private BlogLogMapper blogLogMapper;
	
	/*以下blog部分*/
	public boolean editBlog(Blog blog) {
		int count = 0;
		BlogLog blogLog = new BlogLog();
		if (blog.getId() != null) {
			count = blogMapper.update(blog);
			blogLog.setRemark("更新博客");
		} else {
			blog.setClick(0L);
			count = blogMapper.insert(blog);
			blogLog.setRemark("创建博客");
		}
		blogLog.setBlogId(blog.getId());
		blogLog.setCreateTime(new Date());
		blogLog.setUserId(blog.getUserId());
		blogLog.setUpdateContent(blog.getContent());
		blogLogMapper.insert(blogLog);
		if (count == 0) {
			return false;
		} else {
			return true;	
		}
	}

}
