package com.demo.my.backend.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.my.backend.service.file.FileUploadService;
import com.demo.my.base.common.BaseCommon;
import com.demo.my.base.model.User;
import com.demo.my.base.service.BlogService;
import com.demo.my.base.service.CodeMenuService;
import com.demo.my.base.service.CodeService;
import com.demo.my.base.service.DemoMenuService;
import com.demo.my.base.service.DemoService;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.SysMenuRoleService;
import com.demo.my.base.service.SysMenuService;
import com.demo.my.base.service.SysRoleService;
import com.demo.my.base.service.SysUserRoleService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.service.file.ImageFileService;

import java.net.URLDecoder;

public class BaseBackendController extends BaseCommon {
	
	@Autowired
	protected SysRoleService sysRoleService;
	@Autowired
	protected SysMenuService sysMenuService;
	@Autowired
	protected SysMenuRoleService sysMenuRoleService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected CodeService codeService;
	@Autowired
	protected BlogService blogService;
	@Autowired
	protected DemoService demoService;
	@Autowired
	protected DiscoveryService discoveryService;
	@Autowired
	protected ImageFileService imageFileService;
	@Autowired
    protected SysUserRoleService sysUserRoleService;
	@Autowired
	protected FileUploadService fileUploadService;
	@Autowired
	protected DemoMenuService demoMenuService;
	@Autowired
	protected CodeMenuService codeMenuService;
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    
    
	@ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        
        User user = this.getCurrentUser(); 
		if (user != null) {
			request.setAttribute("isAdmin", isAdmin());
			request.setAttribute("isLogin", true);
			request.setAttribute("user", user);
		} else {
			request.setAttribute("role", "");
			request.setAttribute("isLogin", false);
		}
        
        request.setAttribute("imgApi", "/api_img");
    }
	
	public Map<String, Object> getParmMap() {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		
		//遍历 request
		Enumeration paramNames = request.getParameterNames();  
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();  
			String[] paramValues = request.getParameterValues(paramName);  
			if (paramValues.length == 1) {  
				String paramValue = paramValues[0];  
				if (paramValue.length() != 0) {  
					try {
						parmMap.put(paramName, URLDecoder.decode(paramValue, "utf-8"));	
					} catch (Exception e) {
						parmMap.put(paramName, "");
					}
				}  
			}  
		}
		
		return parmMap;
	}
	
	/**
     * 是否管理员
     * @return
     */
    protected boolean isAdmin(){
    	Subject subject = SecurityUtils.getSubject();
    	return subject.hasRole("ROLE001");
    }
	
}
