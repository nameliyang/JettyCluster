package com.ly;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.session.AbstractSessionIdManager;

public class MySessionIdManager extends AbstractSessionIdManager{

	@Override
	public void addSession(HttpSession arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean idInUse(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void invalidateAll(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSession(HttpSession arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renewSessionId(String arg0, String arg1, HttpServletRequest arg2) {
		
	}

}
