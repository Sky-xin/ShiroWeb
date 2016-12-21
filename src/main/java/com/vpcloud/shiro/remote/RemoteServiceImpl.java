package com.vpcloud.shiro.remote;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.vpcloud.shiro.service.UserService;

public class RemoteServiceImpl implements RemoteService {
	@Autowired
	private UserService userService;
	@Autowired
	private SessionDAO sessionDAO;
	
	@Override
	public Session getSession(String organization, Serializable sessionId) {
		return sessionDAO.readSession(sessionId);
	}

	@Override
	public Serializable createSession(Session session) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return sessionDAO.create(session);
	}

	@Override
	public void updateSession(String organization, Session session) {
		sessionDAO.update(session);
	}

	@Override
	public void deleteSession(String organization, Session session) {
		sessionDAO.delete(session);
	}

	@Override
	public PermissionContext getPermissions(String organization, String username) {
		PermissionContext permissionContext = new PermissionContext();
		permissionContext.setRoles(userService.findRoles(username));
		permissionContext.setPermissions(userService.findPermissions(username));
		return permissionContext;
	}

}
