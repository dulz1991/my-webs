package com.demo.my.base.service;

import java.util.List;
import java.util.Map;

import com.demo.my.base.service.common.AdapterService;

public abstract class AbstractBaseService extends AdapterService {

	/**
	 * sample: super.getByParm("OrderMapper.xmlId", parmMap)
	 * @param mapperNameAndXmlId
	 * @param paramMap
	 * @return
	 */
	@Override
	public <T> List<T> preExcute(String mapperNameAndXmlId, Map<String, Object> paramMap) {
		mapperNameAndXmlId = "ds1mapper." + mapperNameAndXmlId;
		return super.excute(mapperNameAndXmlId, paramMap);
	}
	
}
