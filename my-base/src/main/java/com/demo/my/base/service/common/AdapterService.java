package com.demo.my.base.service.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.demo.my.base.common.BaseCommon;
import com.demo.my.base.mybatis.config.PagePlugin;
import com.demo.my.base.mybatis.mapper.base.BaseMapper;
import com.demo.my.base.util.Page;
import com.demo.my.base.util.SpringContextUtil;

public class AdapterService extends BaseCommon {
	
	private static String BASE_MAPPER_PATH = "com.demo.my.base.mybatis.mapper.";

	/*public <T> int insert(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> int update(T t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	/**
	 * 分页查询方法
	 * @param mapperNameAndXmlId
	 * @param parm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> getPage(String mapperNameAndXmlId, Map<String, Object> parm) {
		Integer pageNo = parm.get("pageNo")==null?1:Integer.valueOf(parm.get("pageNo")+"");
		Integer pageSize = parm.get("pageSize")==null?10:Integer.valueOf(parm.get("pageSize")+"");
		Page<T> page = new Page<T>(pageNo, pageSize);
		
		PagePlugin.startPage(page);
		List<T> list = this.excute(mapperNameAndXmlId, parm);
		page = PagePlugin.getPage();
		page.setList(list);
		
		return page;
	}
	
	/**
	 * sample: super.getByParm("TProductMapper.xmlId", parmMap)
	 * @param mapperNameAndXmlId
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T excute(String mapperNameAndXmlId, Map<String, Object> paramMap) {
		if(StringUtils.isBlank(mapperNameAndXmlId)){
			return null;
		}
		String[] arr = mapperNameAndXmlId.split("\\.");
		if(arr==null || arr.length!=3){
			return null;
		}
		if(paramMap==null){
			paramMap = new HashMap<String, Object>();
		}
		return  (T) invokeMapper(arr[0]+"."+arr[1], arr[2], paramMap);
	}
	
	@SuppressWarnings("unchecked")
	private Object invokeMapper(String mapperName, String sqlId, Object... args){
		try {
			Class<?> mapperClass = Class.forName(BASE_MAPPER_PATH+mapperName); 
			if (BaseMapper.class.isAssignableFrom(mapperClass)) {
				for (Method mapperMethod : mapperClass.getMethods()) {
					if (mapperMethod.getName().equals(sqlId)) {
						if (args==null) {
							args = (Object[]) new Object();
						}
						String mapperKey = mapperName.substring(mapperName.lastIndexOf(".")+1, mapperName.length());
						mapperKey = BaseCommon.toLowerCaseFirstOne(mapperKey);
						Object mapperInstance = SpringContextUtil.getBean(mapperKey);
						return mapperMethod.invoke(mapperInstance, args);
					}
				}		
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
}
