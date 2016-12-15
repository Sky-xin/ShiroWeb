package com.vpcloud.shiro.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

	@RequestMapping("/login")
	public String loginForm(HttpServletRequest request, Model model) {
		String name = (String) request.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(name)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(name)) {
			error = "用户名/密码错误";
		} else if (name != null) {
			error = "其他错误：" + name;
		}
		model.addAttribute("error", error);
		return "login";
	}

	@RequestMapping("/")
	public String getTest() {
		return "/user/list";
	}

	@RequiresGuest
	@RequestMapping("/hello")
	public String testAdmin() {
		System.out.println("权限测试...");
		return "test";
	}

}
