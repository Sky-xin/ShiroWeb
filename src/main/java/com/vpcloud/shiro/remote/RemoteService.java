package com.vpcloud.shiro.remote;

import java.io.Serializable;

import org.apache.shiro.session.Session;
/**
 * 
 * @author Sky
 *
 */
public interface RemoteService {
	public Session getSession(String organization,Serializable sessionId);
	
	Serializable createSession(Session session);
	
	public void updateSession(String organization,Session session);
	
	public void deleteSession(String organization , Session session);
	
	public PermissionContext getPermissions(String organization,String username);
}
