package com.vpcloud.shiro.controller.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

public class SysUserFilter extends PathMatchingFilter {


    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

//        String username = (String)SecurityUtils.getSubject().getPrincipal();
//        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}