package com.ebuka.mainServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ebuka.dataObjects.IUserDAO;
import com.ebuka.dataObjects.UserDAO;
import com.ebuka.model.UserModel;

@WebServlet(description = "Login post API", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    public Login() {
        super();       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("passw");
		
		String submitButtonType = request.getParameter("submit");
		
		String message = null;
		
		if(submitButtonType.equalsIgnoreCase("login"))
		{
			if(username == null || username == "")
			{
				message = "username cannot be null or empty.";
			}
			else if(password == null || password == "")
			{
				message = "password cannot be null or empty.";
			}
			
			if(message != null)
			{				
				loginError(request,response,message);
			}
			else {
				//
				IUserDAO ud = new UserDAO();	
				UserModel u = ud.getUser(username, password);
				
				if(u != null && u.getUsername() != null && u.getLoginCount() == 1)
				{
					message = "User account already logged in!";
					loginError(request,response,message);
				}
				else if(u != null && u.getUsername() != null && u.getRole() != null && u.getRole().equalsIgnoreCase("superadmin"))
				{					    			
					System.out.println("Logged in user found with data = " + u);
					HttpSession session =  request.getSession();					
					session.setAttribute("currentSessionUser",u); 
					response.sendRedirect("welcome.jsp");
					
				}
				else
				{
					message = "Invalid login credentials, may be you should register!";
					loginError(request,response,message);
				}
			}
		}
		else
		{
			message = "Invalid Request, may be you should contact admin!";
			loginError(request,response,message);
		}
	}

    void loginError(HttpServletRequest request, HttpServletResponse response, String message)
    {
    	
		try {
			/**RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+message+"</font>");
			rd.include(request, response);**/
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
