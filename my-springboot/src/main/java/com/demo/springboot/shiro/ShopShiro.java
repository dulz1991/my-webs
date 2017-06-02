package com.demo.springboot.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.demo.my.base.model.User;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.SpringContextUtil;


public class ShopShiro extends AuthorizingRealm{

	 /**  
     * 鏉冮檺璁よ瘉  
     */    
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		UserService userService = (UserService) SpringContextUtil.getBean("userService");
		//鑾峰彇鐧诲綍鏃惰緭鍏ョ殑鐢ㄦ埛鍚?    
        String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();    
        //鍒版暟鎹簱鏌ユ槸鍚︽湁姝ゅ璞?    
        User user=userService.getByUsername(loginName);    
        if(user!=null){  
        	//RoleService roleService = (RoleService) SpringContextUtil.getBean("roleService");
            //鏉冮檺淇℃伅瀵硅薄info,鐢ㄦ潵瀛樻斁鏌ュ嚭鐨勭敤鎴风殑鎵?鏈夌殑瑙掕壊锛坮ole锛夊強鏉冮檺锛坧ermission锛?    
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();    
            //鐢ㄦ埛鐨勮鑹查泦鍚?    
           /* Role role = roleService.getById(user.getUserRole());
            info.addRole(role.getRoleName());	
            //鐢ㄦ埛鐨勮鑹插搴旂殑鎵?鏈夋潈闄愶紝濡傛灉鍙娇鐢ㄨ鑹插畾涔夎闂潈闄愶紝涓嬮潰鐨勫彲浠ヤ笉瑕?
            List<String> permissions = roleService.getPermNameList(role.getPermissions());    
            if(permissions!=null && !permissions.isEmpty()){
            	info.addStringPermissions(permissions);
            }*/
            return info;    
        }    
        return null;    
	}

	 /**  
     * 鐧诲綍璁よ瘉;  
     */   
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//UsernamePasswordToken瀵硅薄鐢ㄦ潵瀛樻斁鎻愪氦鐨勭櫥褰曚俊鎭?    
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;    
        //鏌ュ嚭鏄惁鏈夋鐢ㄦ埛    
        /*UserService userService = (UserService) SpringContextUtil.getBean("userService");
        User user=userService.findByName(token.getUsername());*/    
        if(token!=null){    
            //鑻ュ瓨鍦紝灏嗘鐢ㄦ埛瀛樻斁鍒扮櫥褰曡璇乮nfo涓?    
            return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());    
        }    
        return null;    
	}

}
