package com.vpcloud.shiro.controller.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vpcloud.shiro.pojo.Resource;
import com.vpcloud.shiro.service.OrganizationService;
import com.vpcloud.shiro.service.ResourceService;
import com.vpcloud.shiro.service.RoleService;
import com.vpcloud.shiro.service.UserService;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
    private OrganizationService organizationService;
	
	@ModelAttribute("types")
	public Resource.ResourceType[] resourceTypes(){
		return Resource.ResourceType.values();
	}
	
	@RequiresPermissions("user:view")
	@RequestMapping()
	public String list(Model model){
		setCommonData(model);
		model.addAttribute("resourceList", resourceService.findAll());
		return "/resource/list";
	}
	@RequiresPermissions("resource:create")
	@RequestMapping(value = "/{parentId}/appendChild")
	public String appendChildForm(@PathVariable("parentId") Long parentId,Resource resource){
		Resource parent = resourceService.findOne(parentId);
		resource.setParentId(parentId);
		resource.setParentIds(parent.makeSelfAsParentIds());
		resourceService.createResource(resource);
		return "redirect:/resource";
	}
	@RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update")
	public String update(@PathVariable("id") Long id,Resource resource) {
		Resource oldResource = resourceService.findOne(id);
		resource.setParentId(oldResource.getParentId());
		resource.setParentIds(oldResource.getParentIds());
		resourceService.updateResource(resource);
        return "redirect:/resource";
    }
	@RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/getUpdate")
	@ResponseBody
    public Resource showUpdateForm(@PathVariable("id") Long id) {
        return resourceService.findOne(id);
    }
	@RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        resourceService.deleteResource(id);
        return "redirect:/resource";
    }
	private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
}
