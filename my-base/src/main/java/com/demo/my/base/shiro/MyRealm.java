package com.demo.my.base.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.model.User;

public class MyRealm extends AuthorizingRealm {
	
	/**
	 * ��Ȩ
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		 String username = (String) principals.getPrimaryPrincipal();
	     if (StringUtils.isNotBlank(username)) {
	    	 Set<String> roleNames = new HashSet<String>();
	    	 Set<String> permissions = new HashSet<String>();
	    	 Subject subject = SecurityUtils.getSubject();
	    	 User user = (User) subject.getSession().getAttribute(KeyConstant.USER_INFO);
	    	 if(user.getRoleCodeList()!=null && !user.getRoleCodeList().isEmpty()){
	    		 roleNames.addAll(user.getRoleCodeList());	 
	    	 }
	    	 SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
	    	 authenticationInfo.setRoles(roleNames);
	    	 authenticationInfo.setStringPermissions(permissions);
	    	 return authenticationInfo;
	     }
	     return null;
	}

	/*
	 * ��֤
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if (StringUtils.isNoneBlank(token.getPassword().toString())) {
			User user = new User();
			user.setUsername(token.getUsername().toString());
			user.setPassword(new String(token.getPassword()));
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		}
		return null;
	}
	
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}
