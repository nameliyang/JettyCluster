package com.ly.test;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.session.AbstractSession;
import org.eclipse.jetty.server.session.AbstractSessionManager;

public class MySessionManager  extends AbstractSessionManager{

	@Override
	protected void addSession(AbstractSession session) {
		
	}

	@Override
	public AbstractSession getSession(String idInCluster) {
		return null;
	}

	@Override
	protected void invalidateSessions() throws Exception {
		
	}

	@Override
	protected AbstractSession newSession(HttpServletRequest request) {
		return null;
	}

	@Override
	protected boolean removeSession(String idInCluster) {
		return false;
	}

	
	class MySession extends AbstractSession{

		public MySession(
				HttpServletRequest request) {
			super(MySessionManager.this, request);
		}
		
	}
}
