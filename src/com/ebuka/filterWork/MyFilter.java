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


//Filter implementation and we are validating user session here
@WebFilter("/MyFilter")
public class MyFilter implements Filter {
	
			
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Authentication filter initialized");
	}
			
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
				
		String uri = req.getRequestURI();
		System.out.println("Requested Resource::" + uri);
		
		HttpSession session = req.getSession(false);
		
		if(uri.endsWith("statement") || uri.endsWith("token"))
		{
			System.out.println("Statement or token url is called");
			
			chain.doFilter(request, response);
		}
		else if(uri.endsWith("jsp") || uri.endsWith("Login") || uri.endsWith("Welcome") || uri.endsWith("UserView") || uri.endsWith("Logout"))
		{
			//
			
			if(session == null && !(uri.endsWith("jsp")))
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
		else {
			System.out.println("Redirect to login");
			res.sendRedirect("login.jsp");
			//correct
			/**System.out.println("Authorized access request");
			if(session != null)
			{
				System.out.println("AccountApp Session::" + session.toString());
				
				
			} else {
				System.out.println("AccountApp Session is null");
				//res.sendRedirect("login.jsp");
			}
			chain.doFilter(request, response);**/
		}
		
		//here i need to verify session and capture all my endpoints
		/**else if(session == null && !(uri.endsWith("jsp") || uri.endsWith("Login") || uri.endsWith("Welcome")))
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
		}***/
	}
	
	public void destroy() {
		//sha... do nothing for now except i want to close any resources
	}

}
