package com.demo.my.backend.controller;

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
import com.demo.my.base.service.UserService;
import com.demo.my.base.enums.EnumUserRole;
import com.demo.my.base.enums.EnumUserStatus;
import com.demo.my.base.model.User;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/user")
public class UserController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list")
	public ModelAndView index(String role) {
		ModelAndView model = new ModelAndView("user/user_list");
		model.addObject("role", role);
		if(role.equals(EnumUserRole.ADMIN.getKey()+"")){
			model.addObject("activeIndex", 1);
		} else if(role.equals(EnumUserRole.REGISTER.getKey()+"")){
			model.addObject("activeIndex", 0);
		}
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("user/user_edit");
		if(id!=null){
			User entity = userService.getById(id);
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList(User user,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> parmMap = this.getParmMap();
		Page<Map<String, Object>> page = userService.getPage("UserMapper.getMapListByParm", parmMap);

		if(page.getList()!=null){
			for(Map<String, Object> map : page.getList()){
				int s = (int) map.get("status");
				map.put("userStatusStr", EnumUserStatus.getValueByKey(s));
				map.put("hidepb", false);
				map.put("hidehf", false);
				if(s==EnumUserStatus.NORMAL.getKey()){
					map.put("hidehf", true);
				} else if(s==EnumUserStatus.FORBIDDEN.getKey()){
					map.put("hidepb", true);
				}
			}
		}
		
		Map<String, Object> resMap = responseOK("");
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(User user) {
		userService.save(user);
		return responseOK("保存成功");
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = userService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
