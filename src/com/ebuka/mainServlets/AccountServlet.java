package com.ebuka.mainServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebuka.dataObjects.AccountDAO;
import com.ebuka.dataObjects.IAccountDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet(description = "Expose accounts", urlPatterns = { "/api/account" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	IAccountDAO accountDAO;
       
    public AccountServlet() {
        super();      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        accountDAO = new AccountDAO();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
