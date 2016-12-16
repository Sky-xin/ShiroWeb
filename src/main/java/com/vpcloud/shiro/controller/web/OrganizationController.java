package com.vpcloud.shiro.controller.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vpcloud.shiro.service.OrganizationService;
import com.vpcloud.shiro.service.RoleService;
import com.vpcloud.shiro.service.UserService;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
    private OrganizationService organizationService;
	
	@RequiresPermissions("user:view")
	@RequestMapping
	public String index(Model model){
		setCommonData(model);
		return "/organization/index";
	}
	
	private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
}
