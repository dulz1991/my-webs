package com.demo.my.base.mybatis.mapper.ds2mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.PicBlog;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface PicBlogMapper extends BaseMapper {

	void insert(PicBlog pic);
	
	void update(PicBlog pic);
	
	PicBlog getById(@Param("id") Long id);
	
	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
	
	List<PicBlog> getBeanListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
}
