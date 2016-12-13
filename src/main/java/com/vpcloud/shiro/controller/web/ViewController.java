package com.vpcloud.shiro.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class ViewController {
	
	@RequestMapping("login")
	public String index(){
		System.out.println(">>>>>>>>>>>>>>>>>>>");
		return "index";
	}
	@RequestMapping("get")
	@ResponseBody
	public String getTest(){
		System.out.println("?>>>>>>>>>>>>>>>");
		return "hehe";
	}

}
