package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.Discovery;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface DiscoveryMapper extends BaseMapper {

	int insert(Discovery Discovery);
	
	int delete(@Param("id") Long id);
	
	int update(Discovery Discovery);
	
	Discovery getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	int countByParm(@Param("parm") Discovery d);
	
	List<Discovery> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	Map<String, Object> getMapById(@Param("id") Long id);

	List<Map<String, Object>> getMapListForDrag(@Param("parm") Map<String, Object> parmMap);

	List<Map<String, Object>> getMyCommentDiscovery(@Param("parm") Map<String, Object> parm);
	int getMyCommentDiscoveryCount(@Param("parm") Map<String, Object> parm);

}
