package com.ly.sessionshare;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServer {

	private static final Logger logger = LoggerFactory
			.getLogger(MyServer.class);

	public static void main(String[] args) throws Exception {
		Slf4jLog slf4jLog = new org.eclipse.jetty.util.log.Slf4jLog();
		org.eclipse.jetty.util.log.Log.setLog(slf4jLog);

		String userDir = System.getProperty("user.dir");
		String webApp = userDir + "/src/main/webapp";
		logger.info("webapp ={}", webApp);
		Server server = getServer(2334);

		MySessionIdManager sessionIdmanager = new MySessionIdManager();
		server.setSessionIdManager(sessionIdmanager);

		WebAppContext context = new WebAppContext(webApp, "/");

		MySessionManager manager = new MySessionManager();
		manager.setSessionIdManager(server.getSessionIdManager());
		context.getSessionHandler().setSessionManager(manager);
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