package com.vpcloud.shiro.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vpcloud.shiro.pojo.Role;
import com.vpcloud.shiro.pojo.User;
import com.vpcloud.shiro.service.OrganizationService;
import com.vpcloud.shiro.service.ResourceService;
import com.vpcloud.shiro.service.RoleService;
import com.vpcloud.shiro.service.UserService;

/**
 * 
 * @author Sky
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private ResourceService resourceService;

	@Autowired
	private RoleService roleService;

	@RequiresPermissions("role:view")
	@RequestMapping
	public String getRole(Model model) {
		List<Role> rolelist = roleService.findAll();
		for (int i = 0; i < rolelist.size(); i++) {
			List<Long> resourcesid = rolelist.get(i).getResourceIds();
			// System.out.println(resourcesid.size());
			ArrayList<String> resourcename = new ArrayList<String>();
			for (int j = 0; j < resourcesid.size(); j++) {
				resourcename.add(j, resourceService.findOne(resourcesid.get(j)).getName());
			}
			rolelist.get(i).setResourcename(resourcename);
		}
		model.addAttribute("AllRoleList", rolelist);
		model.addAttribute("Rescourcelist", resourceService.findAll());
		return "/role/list";
	}

	@RequiresPermissions("role:create")
	@RequestMapping(value = "/create")
	public String create(Role role, RedirectAttributes redirectAttributes) {
		System.out.println(role.toString());
		roleService.createRole(role);
		redirectAttributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/role";
	}

	@RequiresPermissions("role:delete")
	@RequestMapping(value = "/{id}/delete")
	public String DeleteRole(@PathVariable("id") Long id, Model model) {
		roleService.deleteRole(id);
		return "redirect:/role";
	}

	@RequiresPermissions("role:update")
	@RequestMapping(value = "/update")
	public String UpdateRole(Role role, RedirectAttributes redirectAttributes) {
		System.out.println(role.toString());
		roleService.updateRole(role);
		return "redirect:/role";
	}

}
