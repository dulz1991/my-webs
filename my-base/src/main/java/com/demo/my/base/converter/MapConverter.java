package com.demo.my.base.converter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.demo.my.base.util.DateUtil;

public class MapConverter {

	public List<Map<String, Object>> map2Map(List<Map<String, Object>> list, Map<String, Object> additionMap) {
		for(Map<String, Object> map : list){
			for(String str : map.keySet()){
				if(str.equals("createTime") || str.equals("updateTime")){
					if(map.get(str)!=null){
						Date date = (Date) map.get(str);
						map.put(str, DateUtil.dateToString(date, DateUtil.DATETIME_FORMATE_2));
					}
				}
			}
		}
		if(additionMap!=null && !additionMap.isEmpty()){
			list.add(additionMap);	
		}
		return list;
	}
	
}
