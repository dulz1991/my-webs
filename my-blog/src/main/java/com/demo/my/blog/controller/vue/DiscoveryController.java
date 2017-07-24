package com.demo.my.blog.controller.vue;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Discovery;
import com.demo.my.base.service.CollectionService;
import com.demo.my.base.service.CommentService;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.Page;
import com.demo.my.blog.controller.common.BaseController;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController extends BaseController {
	
	@Autowired
	private DiscoveryService discoveryService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectionService collectionService;

	
	@ResponseBody
	@RequestMapping(value = "/getList", method=RequestMethod.GET)
	public Map<String, Object> discoveryList(@RequestParam(name="pageNo", defaultValue="1") int pageNo, Long discoveryId, String type) {
		int pageSize = 10;
		Discovery discovery = new Discovery();
		discovery.setStatus(1);
		
		if(StringUtils.isBlank(type)){
			Page<Map<String, Object>> page = discoveryService.getMapListByParm(discovery, pageNo, pageSize, "d.ID desc");
			resMap.put("list", page.getList());
		} else{
			List<Map<String, Object>> list = discoveryService.getMapListForDrag(discoveryId, pageSize, type);
			if(list.isEmpty()){
				return responseError(ErrorConstant.ERROR_GENERAL, "没有更多数据");
			}
			Collections.sort(list, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					Long o1Id = Long.valueOf(o1.get("id").toString());
					Long o2Id = Long.valueOf(o2.get("id").toString());
					return -(o1Id).compareTo(o2Id);
				}
			});
			resMap.put("list", list);
		}
		
		resMap.put("pageSize", pageSize);
		return resMap;
	}
	
	
}
