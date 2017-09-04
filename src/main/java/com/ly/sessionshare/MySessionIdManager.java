package com.ly.sessionshare;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.session.AbstractSessionIdManager;

public class MySessionIdManager extends AbstractSessionIdManager {

	@Override
	public boolean idInUse(String id) {
		return false;
	}

	@Override
	public void addSession(HttpSession session) {
		if(session == null){
			return ;
		}
	}

	@Override
	public void removeSession(HttpSession session) {
		
	}

	@Override
	public void invalidateAll(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getClusterId(String nodeId) {
		return null;
	}

	@Override
	public String getNodeId(String clusterId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
