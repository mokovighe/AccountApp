package com.ebuka.mainServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ebuka.model.StatementModel;
import com.ebuka.dataObjects.IStatementDAO;
import com.ebuka.dataObjects.StatementDAO;
import com.google.gson.Gson;

@WebServlet(description = "API to return account's statement to user", urlPatterns = { "/api/statement" })
public class StatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	IStatementDAO s;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
		s = new StatementDAO();
		List<StatementModel> statement = s.getAllStatements();
		String statementJsonString = gson.toJson(statement);
		 
        PrintWriter out = response.getWriter();
        try {
            out.println(statementJsonString);
        } finally {
            out.close();
        }
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		///response.getWriter().append("Served at: ").append(statement);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
