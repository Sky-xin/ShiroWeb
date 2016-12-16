package com.vpcloud.shiro.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest request,Model model){
		String name = request.getParameterMap().toString();
		System.out.println(name);
		String error = null;
		if(UnknownAccountException.class.getName().equals(name)){
			 error = "用户名/密码错误";
		}else if (IncorrectCredentialsException.class.getName().equals(name)){
			error = "用户名/密码错误";
		}else if(name != null) {
            error = "其他错误：" + name;
        }
		model.addAttribute("error", error);
		return "login";
	}
}
