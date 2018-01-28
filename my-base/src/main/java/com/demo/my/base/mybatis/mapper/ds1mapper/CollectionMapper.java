package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.Collection;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface CollectionMapper extends BaseMapper {

	int insert(Collection Collection);
	
	int delete(@Param("id") Long id);
	
	int update(Collection Collection);
	
	Collection getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	int countByParm(@Param("parm") Collection c);
	
	List<Collection> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMyCollectionForApp(@Param("parm") Map<String, Object> parmMap);

	int getMapListByParm_count(@Param("parm") Map<String, Object> parm);

	int deleteByDiscoveryId(Collection collection);

}
