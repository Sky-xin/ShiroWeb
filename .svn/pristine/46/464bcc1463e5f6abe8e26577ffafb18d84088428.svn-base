package com.vpcloud.shiro.controller.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vpcloud.shiro.pojo.User;
import com.vpcloud.shiro.service.OrganizationService;
import com.vpcloud.shiro.service.RoleService;
import com.vpcloud.shiro.service.UserService;

/**
 * 
 * @author Sky
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
    private OrganizationService organizationService;
	
	@RequiresPermissions("user:view")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){
		String url = "http://i.dimg.cc/8f/3c/9f/39/8e/48/0b/b4/ff/0d/a8/8a/62/22/f3/6a.jpg";
		setCommonData(model);
		model.addAttribute("userList", userService.findAll());
		model.addAttribute("url", url);
		return "/user/list";
	}
	
	@RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(User user, RedirectAttributes redirectAttributes) {
		System.out.println(user.toString());
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/user";
    }
	
	
	@RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update")
    public String update(User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/user";
    }


    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        System.out.println(id+">>>>>>>>>>");
    	userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/user";
    }


   

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
    	userService.changePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user";
    }
	
	private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
}


















