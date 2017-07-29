package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.Comment;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface CommentMapper extends BaseMapper {

	int insert(Comment Comment);
	
	int delete(@Param("id") Long id);
	
	int update(Comment Comment);
	
	Comment getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<Comment> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListForDrag(@Param("parm") Map<String, Object> parm);

	int getMapListByParm_count(@Param("parm") Map<String, Object> parm);

	Map<String, Object> getCommentDetailById(@Param("id") Long id);

}
