package com.itbjx.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

/**
 * 创建ShiroFilterFactoryBean
 */
		@Bean
		public ShiroFilterFactoryBean shiroFilterFactoryBean (@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
			ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
			//设置安全管理器
			shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

			//添加Shiro内置过滤器
			/**
			 * Shiro内置过滤器，可以实现权限相关的拦截器
			 *    常用的过滤器：
			 *       anon: 无需认证（登录）可以访问
			 *       authc: 必须认证才可以访问
			 *       user: 如果使用rememberMe的功能可以直接访问
			 *       perms： 该资源必须得到资源权限才可以访问
			 *       role: 该资源必须得到角色权限才可以访问
			 */
			Map<String,String> filterMap = new LinkedHashMap<>();
			//设置要拦截的URL
		/*	filterMap.put("/add","authc");
			filterMap.put("/update","authc");*/
		   filterMap.put("/login","anon"); //不拦截登录
		   filterMap.put("/testThymaleaf","anon"); //不拦截首页
		   
		   
		   //授权过滤器
		   //注意：当前授权拦截后，shiro会自动跳转到未授权页面
		   filterMap.put("/add", "perms[user:add]");
		   filterMap.put("/update", "perms[user:update]");
		   
		   filterMap.put("/*","authc");  //拦截所有
		   
		  
			//修改默认的跳转页面
			shiroFilterFactoryBean.setLoginUrl("/toLogin");
			//设置未授权提示页面
			shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
			
			shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
			return shiroFilterFactoryBean;
		}
/**
 * 创建DefaultWebSecurityManager
 */
		@Bean(name = "securityManager")
   public DefaultWebSecurityManager defaultWebSecurityManager (@Qualifier("userRealm") UserRealm userRealm){
		 DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		 //关联realm
		 securityManager.setRealm(userRealm);
		 return securityManager;
	 }

		/*
		*   创建Realm
		* */
		@Bean(name = "userRealm")
		public UserRealm getRealm(){
			return new UserRealm();
		}
		
	/**
	 * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
	 */
	@Bean
	public ShiroDialect  getShiroDialect () {
		return new ShiroDialect ();
	}
}
