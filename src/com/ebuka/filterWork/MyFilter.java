package com.ebuka.filterWork;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.log4j.Logger;

//Filter implementation and we are validating user session here
@WebFilter("/MyFilter")
public class MyFilter implements Filter {
		
	public void init(FilterConfig fConfig) throws ServletException {
		///logger.info("Authentication filter initialized");
		System.out.println("Authentication filter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		System.out.println("Requested Resource::" + uri);
		
		HttpSession session = req.getSession(false);
		
		
		//here i need to verify session and capture all my endpoints
		if(session == null && !(uri.endsWith("jsp") || uri.endsWith("Login") || uri.endsWith("Register") || uri.endsWith("Welcome")))
		{
			System.out.println("Unauthorized access request or session is null");
			res.sendRedirect("login.jsp");
		}
		else if(session != null && req.getRequestedSessionId() != null && !req.isRequestedSessionIdValid())
		{
			//session has expired
			System.out.println("Unauthorized access! Session has expired.");
			res.sendRedirect("login.jsp");
		}
		else {
			//correct
			System.out.println("Authorized access request");
			if(session != null)
			{
				System.out.println("AccountApp Session::" + session.toString());
				
				
			} else {
				System.out.println("AccountApp Session is null");
				//res.sendRedirect("login.jsp");
			}
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {
		//sha... do nothing for now except i want to close any resources
	}

}
