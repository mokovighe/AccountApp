package com.ebuka.mainServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebuka.dataObjects.AccountDAO;
import com.ebuka.dataObjects.IAccountDAO;
import com.ebuka.dataObjects.IUserDAO;
import com.ebuka.dataObjects.UserDAO;
import com.ebuka.model.UserModel;
import com.ebuka.utils.GenericHelpers;
import com.google.gson.Gson;

@WebServlet(description = "Expose accounts", urlPatterns = { "/api/account" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	IAccountDAO accountDAO;
       
    public AccountServlet() {
        super();
        accountDAO = new AccountDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        
        String jwt = GenericHelpers.resolveToken(request);
        if(jwt == null)
        {
        	GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_UNAUTHORIZED, false, "It may be that you didn't supply authentication token. Please login for a valid token."));
        }
        else {
        	//
        	System.out.println("Bearer::" + jwt);
        	String validUser = GenericHelpers.validateMyToken(jwt);
			if(validUser != null)
			{
				System.out.println("User Token Valid :: " + validUser);
				//check if testuser or adminuser and if they are developer role
				Map<String, String[]> mapParameters = null;
				IUserDAO ud = new UserDAO();	
				UserModel u = ud.getUserByUsername(validUser);
				if(u != null)
				{
					mapParameters = GenericHelpers.getQueryParameters(request);
					if(u.getRole() != null && u.getRole().equalsIgnoreCase("developer") && u.getUsername().equalsIgnoreCase("testadmin"))
					{
						if(mapParameters.isEmpty()) //no parameter specified by this user
						{			        		               
			        		out.print(gson.toJson(accountDAO.getAllAccounts()));
						}
						else {
							String[] param1=null;
							String paramId="";
						
							param1 = mapParameters.get("id");
							if(param1 != null)
							{
								paramId = param1[0];
								System.out.println("account id Supplied::" + paramId);
							}
							if(!paramId.isEmpty())
							{
								out.print(gson.toJson(accountDAO.getAccountById(Integer.parseInt(paramId))));
							}
							
						}
					}
					else if(u.getRole() != null && u.getRole().equalsIgnoreCase("developer") && u.getUsername().equalsIgnoreCase("testuser"))
					{						
						GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_UNAUTHORIZED, false, "You are not authorized to view this resource."));
					}
				}
				else {
					GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_UNAUTHORIZED, false, "Unidentify user account."));
					
				}
			}
			else {
				//
				GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_UNAUTHORIZED, false, "It may be that you supplied invalid authentication token or that token has expired. Please login for a valid token."));
				
			}
        }
        
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
