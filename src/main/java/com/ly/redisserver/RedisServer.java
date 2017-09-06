package com.ly.redisserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ovea.jetty.session.redis.RedisSessionIdManager;
import com.ovea.jetty.session.redis.RedisSessionManager;
import com.ovea.jetty.session.serializer.JsonSerializer;

public class RedisServer {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RedisServer.class);

	public static void main(String[] args) throws Exception {
		
		Slf4jLog slf4jLog = new org.eclipse.jetty.util.log.Slf4jLog();
		org.eclipse.jetty.util.log.Log.setLog(slf4jLog);

		String userDir = System.getProperty("user.dir");
		String webApp = userDir + "/src/main/webapp";
		logger.info("webapp ={}", webApp);
		Server server = getServer(2334);
		
		redis.clients.jedis.JedisPool jedisPool = new redis.clients.jedis.JedisPool("127.0.0.1", 6379);
		RedisSessionIdManager sessionIdManager = new RedisSessionIdManager(server, jedisPool);
		sessionIdManager.setScavengerInterval(20000);
	//	sessionIdManager.setWorkerName("node1");
		server.setSessionIdManager(sessionIdManager);
		
		RedisSessionManager sessionManager = new RedisSessionManager(jedisPool, new JsonSerializer());
		sessionManager.setSaveInterval(20);
		sessionManager.setSessionDomain("localhost");
		sessionManager.setSessionPath("/");
		sessionManager.setMaxCookieAge(80);
		sessionManager.setRefreshCookieAge(20);
		SessionHandler handler = new SessionHandler(sessionManager);
		
		
		WebAppContext context = new WebAppContext(webApp, "/");
		context.setSessionHandler(handler);
		context.setConfigurationClasses(new String[] {
				"org.eclipse.jetty.webapp.WebInfConfiguration",
				"org.eclipse.jetty.webapp.WebXmlConfiguration",
				"org.eclipse.jetty.webapp.MetaInfConfiguration",
				"org.eclipse.jetty.webapp.FragmentConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration",
				"org.eclipse.jetty.webapp.JettyWebXmlConfiguration" });

		server.setHandler(context);
		server.start();
		logger.info("ehllo");
		server.join();
	}

	public static Server getServer(int port) {
		Server server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		connector.setMaxIdleTime(300000);
		connector.setStatsOn(false);
		connector.setLowResourcesConnections(5000);
		connector.setLowResourcesMaxIdleTime(5000);
		server.addConnector(connector);
		return server;
	}
}
