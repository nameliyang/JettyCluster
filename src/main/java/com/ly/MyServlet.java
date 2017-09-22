package com.ly;

import java.io.IOException;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		String cookieSessionID = null;
		
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if ("JSESSIONID".equals(cookie.getName())) {
					cookieSessionID = cookie.getValue();
				}
			}
		}
		resp.getWriter().write("ip: " +getIp()+ " ,reqCookie:" + cookieSessionID + ",session=" + req.getSession().getId());
		System.out.println("ip: " +getIp()+ " ,reqCookie:" + cookieSessionID + ",session=" + req.getSession().getId());
		
	}

	public static String getIp(){
		try {
		    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		    while (interfaces.hasMoreElements()) {
		        NetworkInterface iface = interfaces.nextElement();
		        if (iface.isLoopback() || !iface.isUp() || iface.isVirtual() || iface.isPointToPoint())
		            continue;

		        Enumeration<InetAddress> addresses = iface.getInetAddresses();
		        while(addresses.hasMoreElements()) {
		            InetAddress addr = addresses.nextElement();

		            final String ip = addr.getHostAddress();
		            if(Inet4Address.class == addr.getClass()) return ip;
		        }
		    }
		} catch (SocketException e) {
		    throw new RuntimeException(e);
		}
		return null;
	}
	
	
}

class User implements Serializable{
	
	private String name;
	
	public User(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "user:"+name;
	}
}