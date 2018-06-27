package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.SysCfg;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface SysCfgMapper extends BaseMapper {

	int insert(SysCfg sysCfg);
	
	int update(SysCfg sysCfg);
	
	SysCfg getById(@Param("id") Long id);
	
	List<SysCfg> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	void callProcCount(@Param("parm") Map<String, Object> map);
	
	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("parm") Map<String, Object> paramMap);
 
	SysCfg getByKey(@Param("key") String key);
}
