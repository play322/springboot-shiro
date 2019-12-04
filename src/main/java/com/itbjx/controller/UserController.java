package com.itbjx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello springboot-shiro";
	}

	@RequestMapping("/testThymaleaf")
	public String testThymaleaf(Model model) {
		model.addAttribute("name","西安柚子公司");
		return "test";
	}

	/*去登陆*/
	@RequestMapping("/toLogin")
	public String tologin() {
		return "login";
	}
	
	//跳转未授权页面
	@RequestMapping("/unAuth")
	public String unAuth() {
		return "unAuth";
	}
	
	/*登陆*/
	@RequestMapping("/login")
	public String login(String name,String password,Model model) {
		/*
		shiro 编写登录的认证逻辑
		* */
		//1，获取subject
		Subject subject = SecurityUtils.getSubject();
		// 2，封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(name,password);
		//3，执行登录
		try {
		subject.login(token);
		//登陆成功
			return "redirect:/testThymaleaf";
		}catch (UnknownAccountException e){
			//登录失败：用户名不存在
			model.addAttribute("msg","用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException e){
			//登录失败：密码错误
			model.addAttribute("msg","密码错误");
			return "login";
		}
	}


	@RequestMapping("/add")
	public String add() {
		return "/user/add";
	}

	@RequestMapping("/update")
	public String update() {
		return "/user/update";
	}

}
