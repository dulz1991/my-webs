package com.demo.my.base.mybatis.mapper.ds1mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.model.DemoMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;

public interface DemoMenuMapper extends BaseMapper {

	int insert(DemoMenu DemoMenu);
	
	int delete(@Param("id") Long id);
	
	int update(DemoMenu DemoMenu);
	
	DemoMenu getById(@Param("id") Long id);
	
	int countByParm(@Param("parm") Map<String, Object> paramMap);
	
	List<DemoMenu> getBeanListByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm( @Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getForTree();

}
