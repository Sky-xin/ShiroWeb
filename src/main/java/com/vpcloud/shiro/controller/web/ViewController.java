package com.vpcloud.shiro.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	
	@RequestMapping("/login")
	public ModelAndView goIndex(){
		System.out.println(">>>>>>>>>>>>>>>>>>>");
		ModelAndView mv = new ModelAndView();
        mv.addObject("name", "sky");
        mv.setViewName("index");
        System.out.println("2");
        return mv;
	}
	@RequestMapping("/")
	@ResponseBody
	public String getTest(){
		System.out.println("?>>>>>>>>>>>>>>>");
		return "hehe";
	}

}
