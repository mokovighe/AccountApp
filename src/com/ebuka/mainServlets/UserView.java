package com.ebuka.mainServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebuka.dataObjects.IUserDAO;
import com.ebuka.dataObjects.UserDAO;
import com.ebuka.model.UserModel;

@WebServlet(description = "Show all users", urlPatterns = { "/UserView" })
public class UserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserDAO userDAO;
       
    public UserView() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<UserModel> modelList = userDAO.getAllUsers();
		request.setAttribute("lists", modelList);
        RequestDispatcher rd = request.getRequestDispatcher("userView.jsp");
        rd.forward(request, response);
        
	}

}
