package com.ly.jdbc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.session.JDBCSessionIdManager;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcJetty8Cluster {
	private static final Logger logger = LoggerFactory.getLogger(JdbcJetty8Cluster.class);
	
	public static void main(String[] args) throws Exception {
		Slf4jLog slf4jLog = new org.eclipse.jetty.util.log.Slf4jLog();
		org.eclipse.jetty.util.log.Log.setLog(slf4jLog);
		
		String userDir = System.getProperty("user.dir");
		String webApp = userDir+"/src/main/webapp";
		logger.info("webapp ={}",webApp);
		Server server = getServer(2334);
			
		 JDBCSessionIdManager idMgr = new JDBCSessionIdManager(server);
		 idMgr.setWorkerName("fred");
		 idMgr.setDriverInfo("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/sessions?user=root&password=liyang");
		 idMgr.setScavengeInterval(60);
		 server.setSessionIdManager(idMgr);
		 
		
		 WebAppContext context = new WebAppContext(webApp, "/");
		
		
		 JDBCSessionManager jdbcMgr = new JDBCSessionManager();
		 jdbcMgr.setSessionIdManager(server.getSessionIdManager());
		 context.getSessionHandler().setSessionManager(jdbcMgr);
		
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
