package com.demo.my.base.mybatis.mapper.ds3mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demo.my.base.bean.DemoMenu;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.PageUtil;

public interface DemoMenuMapper extends BaseMapper {

	int insert(DemoMenu demoMenu);
	
	int update(DemoMenu demoMenu);
	
	DemoMenu getById(@Param("id") Long id);
	
	List<DemoMenu> getBeanListByParm(@Param("page")PageUtil pageUtil, @Param("parm")Map<String, Object> parmMap);

	int delete(@Param("id") Long id);

	int countByParm(@Param("parm") Map<String, Object> paramMap);

	List<Map<String, Object>> getMapListByParm(@Param("page") PageUtil page, @Param("parm") Map<String, Object> paramMap);
}
