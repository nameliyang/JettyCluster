package com.ly;

import java.io.IOException;

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
			for(Cookie cookie:cookies){
				if("JSESSIONID".equals(cookie.getName())){
					cookieSessionID = cookie.getValue();
				}
			}
		}
		
		HttpSession session = req.getSession(false);
		resp.getWriter().write("helloA "+"reqCookie:"+cookieSessionID+",session="+session);
		req.getSession(false);
		req.getSession();
		req.getSession();
	}
	
}
