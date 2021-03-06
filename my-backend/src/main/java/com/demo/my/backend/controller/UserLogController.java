package com.demo.my.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.service.UserLogService;
import com.demo.my.base.enums.EnumUserLogType;
import com.demo.my.base.model.UserLog;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/userLog")
public class UserLogController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserLogController.class);
	
	@Autowired
	private UserLogService userLogService;
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("userLog/userLog_list");
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("userLog/userLog_edit");
		if(id!=null){
			UserLog entity = userLogService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(UserLog userLog,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		Page<Map<String, Object>> page = userLogService.getPage("UserLogMapper.getMapListByParm", parmMap);
		if(page!=null && page.getList()!=null){
			for(Map<String, Object> map : page.getList()){
				map.put("logTypeStr", EnumUserLogType.getValueByKey((Integer)map.get("type")));
			}
		}

		Map<String, Object> resMap = responseOK();
		resMap.put("page", page);
		resMap.put("list", page.getList());
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(UserLog userLog) {
		userLogService.save(userLog);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = userLogService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
