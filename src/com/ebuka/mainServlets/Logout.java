package com.ebuka.mainServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebuka.dataObjects.IUserDAO;
import com.ebuka.dataObjects.UserDAO;
import com.ebuka.model.UserModel;

@WebServlet(description = "User should logout", urlPatterns = { "/Logout" })
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserDAO userDAO = new UserDAO();
       
    public Logout() {
        super();       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		if(cookie.getName().equals("SESSIONID")){
    			System.out.println("SESSIONID="+cookie.getValue());
    		
    			break;
    		}
    	}
    	}
    	//invalidate the session if exists
    	HttpSession session = request.getSession(false);
    	System.out.println("User="+session.getAttribute("currentSessionUser"));
    	
    	if(session != null && session.getAttribute("currentSessionUser") != null){
    		UserModel currentUser = (UserModel) (session.getAttribute("currentSessionUser"));
    		if(currentUser != null)
    			userDAO.updateLoginCount(currentUser.getUsername(), 0);
    		
    		System.out.println("Invalidate session");
    		session.invalidate();
    	}
    	response.sendRedirect("login.jsp");
    }

}
