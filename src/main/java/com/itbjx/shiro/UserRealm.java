package com.itbjx.shiro;

import com.itbjx.domain.User;
import com.itbjx.service.UserSerice;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	 private UserSerice userSerice;
	/*
	*   执行授权逻辑
	* */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("执行授权逻辑");
		//给资源进行授权
		SimpleAuthorizationInfo  info = new SimpleAuthorizationInfo ();
		//添加资源的授权字符串
		//info.addStringPermission("user:add");
	
		//从数据库查询当前用户及其授权字符串
//		Subject subject = SecurityUtils.getSubject();
//		User user = (User)subject.getPrincipal();
//		User user2 = userSerice.findById(user.getId());
//		System.out.println("user2--->"+user2.getPerms());
//		info.addStringPermission(user2.getPerms());
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		
		
		User dbUser = userSerice.findById(user.getId());
		
		
		info.addStringPermission(dbUser.getPerms());
		
		return info;
	}

	
	/*
	* 执行认证逻辑
	* */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		User user = userSerice.findByName(token.getUsername());
	
		//假设数据库的用户名和密码
//		String name = "aaa";
//		String password = "111";

		//1,判断用户名
//		if(!token.getUsername().equals(name)){
		if(user==null) {
			//用户名不存在
			return null;   //shiro底层会抛出UnKnowAccountException
		}
		//2，判断密码
		//return new SimpleAuthenticationInfo("",password,"");
		return new SimpleAuthenticationInfo(user,user.getPassword(),"");
	}
}
