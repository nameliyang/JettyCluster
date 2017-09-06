package com.ly.jdbc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.JDBCSessionIdManager;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcJetty9Cluster {
	private static final Logger logger = LoggerFactory.getLogger(JdbcJetty9Cluster.class);
	
	public static void main(String[] args) throws Exception {
		Slf4jLog slf4jLog = new org.eclipse.jetty.util.log.Slf4jLog();
		org.eclipse.jetty.util.log.Log.setLog(slf4jLog);
		
		String userDir = System.getProperty("user.dir");
		String webApp = userDir+"/src/main/webapp";
		logger.info("webapp ={}",webApp);
		Server server = getServer(2334);
			
		 
		
		 WebAppContext context = new WebAppContext(webApp, "/");
		
		
		 // context.setDefaultsDescriptor("C:\\Users\\ADMINI~1\\AppData\\Local\\Temp\\eclipseJettyPlugin.webDefaults.SessionB.xml");
		  context.setConfigurationClasses(new String[] { 
				"org.eclipse.jetty.webapp.WebInfConfiguration",
				"org.eclipse.jetty.webapp.WebXmlConfiguration", 
				"org.eclipse.jetty.webapp.MetaInfConfiguration",
				"org.eclipse.jetty.webapp.FragmentConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration",
				"org.eclipse.jetty.webapp.JettyWebXmlConfiguration"
				});
		
		server.setHandler(context);
		server.start();
		logger.info("ehllo");
		server.join();
		
	}

	public static Server getServer(int port) {
		Server server = new Server(2334);
		return server;
	}
}
