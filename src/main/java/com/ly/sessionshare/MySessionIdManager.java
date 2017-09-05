package com.ly.sessionshare;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.session.AbstractSessionIdManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySessionIdManager extends AbstractSessionIdManager {

	private static final Logger logger = LoggerFactory.getLogger(MySessionIdManager.class);
	
	HashSet<String> sessionIds = new HashSet<String>();
	
	@Override
	public boolean idInUse(String id) {
		logger.info("id={} id in use ",id);
		synchronized (this) {
			return sessionIds.contains(id);
		}
	}
	
	@Override
	public String newSessionId(HttpServletRequest request, long created) {
		String sessionId = null;
		sessionId = super.newSessionId(request, created);
		logger.info("new sessionId = {}",sessionId);
		return sessionId;
	}

	@Override
	public void addSession(HttpSession session) {
		if(session == null){
			return ;
		}
		logger.info("add session id = {}",session.getId());
		synchronized (this) {
			sessionIds.add(session.getId());
		}
	}

	@Override
	public void removeSession(HttpSession session) {
		logger.info("add session id = {}",session==null?null:session.getId());
		if(session == null){
			return;
		}
		synchronized (this) {
			sessionIds.remove(session);
		}
	}

	@Override
	public void invalidateAll(String id) {
		
	}

	@Override
	public String getClusterId(String nodeId) {
		return nodeId;
	}

	@Override
	public String getNodeId(String clusterId, HttpServletRequest request) {
		return clusterId;
	}
	
}
