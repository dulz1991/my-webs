package com.demo.my.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.demo.my.base.enums.EnumUserStatus;
import com.demo.my.base.model.SysRole;
import com.demo.my.base.model.SysUserRole;
import com.demo.my.base.model.User;
import com.demo.my.base.util.MD5Util;
import com.demo.my.base.util.Page;

@Controller
@RequestMapping("/backend/user")
public class UserController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("user/user_list");
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
				int s = Integer.valueOf(map.get("status").toString()) ;
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
	
	@ResponseBody
	@RequestMapping(value="/disable")
	public Map<String, Object> disable(Long id) {
		User user = new User();
		user.setId(id);
		user.setStatus(EnumUserStatus.FORBIDDEN.getKey());
		userService.update(user);
		return responseOK();
	}
	
	@ResponseBody
	@RequestMapping(value="/enable")
	public Map<String, Object> enable(Long id) {
		User user = new User();
		user.setId(id);
		user.setStatus(EnumUserStatus.NORMAL.getKey());
		userService.update(user);
		return responseOK();
	}
	
	@ResponseBody
	@RequestMapping(value="/resetPwd")
	public Map<String, Object> resetPwd(Long id) {
		User user = new User();
		user.setId(id);
		MD5Util md5 = new MD5Util(MD5Util.default_salt, "MD5");
		user.setPassword(md5.encode("123456"));
		userService.update(user);
		return responseOK();
	}
		
	/*修改权限*/
	@RequestMapping(value="/resetUserRolePage")
	public ModelAndView resetUserRolePage(Long userId) {
		ModelAndView model = new ModelAndView("user/sysUser_edit_role");
		
		User user = userService.getById(userId);
		model.addObject("userId", user.getId());
		model.addObject("title", "修改用户【"+user.getUsername()+"】权限");
		
		//已有权限
		List<SysUserRole> sysUserRoles = sysUserRoleService.getSysUserRoleByUserId(user.getId());
		//全部权限
		List<SysRole> sysRoles = sysRoleService.getBeanListByParm(null);
		//权限列表
		List<Map<String, Object>> sysRoleMaps = new ArrayList<Map<String,Object>>();
		if(sysUserRoles!=null){
			for(SysRole sysRole : sysRoles){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleCode", sysRole.getRoleCode());
				map.put("roleName", sysRole.getRoleName());
				map.put("hasRoleCode", false);
				for(SysUserRole sysUserRole : sysUserRoles){
					if(sysRole.getRoleCode().equals(sysUserRole.getSysRoleCode())){
						map.put("hasRoleCode", true);
						break;
					}
				}
				sysRoleMaps.add(map);
			}
		}
		model.addObject("roleList", sysRoleMaps);
		
		return model;
	}
	/**
	 * 修改权限
	 * @param userId
	 * @param roleCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/resetUserRole")
	public Map<String, Object> resetUserRole(Long userId, String roleCode) {
		if(userId==null){
			return responseGeneralError("保存失败：参数异常");
		}
		if(StringUtils.isBlank(roleCode)){
			return responseGeneralError("请选择权限");
		}
		sysUserRoleService.deleteByUserId(userId);
		userService.addUserRole(userId, roleCode);
		return responseOK();
	}
}
