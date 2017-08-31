package com.ly;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.session.AbstractSessionIdManager;

public class MySessionIdManager extends AbstractSessionIdManager{

	@Override
	public boolean idInUse(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addSession(HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSession(HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invalidateAll(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getClusterId(String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNodeId(String clusterId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
