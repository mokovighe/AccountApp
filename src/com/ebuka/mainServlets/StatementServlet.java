package com.ebuka.mainServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.ebuka.model.StatementModel;
import com.ebuka.model.UserModel;
import com.ebuka.utils.GenericHelpers;
import com.ebuka.dataObjects.IStatementDAO;
import com.ebuka.dataObjects.IUserDAO;
import com.ebuka.dataObjects.StatementDAO;
import com.ebuka.dataObjects.UserDAO;
import com.google.gson.Gson;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

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
        s = new StatementDAO();
    }

    private String resolveToken(HttpServletRequest request){
	    String bearerToken = request.getHeader(GenericHelpers.AUTHORIZATION_HEADER);
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	    	
	        return bearerToken.substring(7, bearerToken.length());
	    }
	    return null;
	}
    private String validateMyToken(String token)
	{
    	String user = null;
		final String SECRET1 = Base64.encodeBase64String(GenericHelpers.secretKey.getBytes());
		try {
			user = Jwts.parser().setSigningKey(SECRET1).parseClaimsJws(token).getBody().getSubject();
			System.out.println("validateMyToken: " + user);
		}
		catch(ExpiredJwtException e)
		{}
	    
	    return user;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        
        String jwt = resolveToken(request);
        if(jwt == null)
        {
        	GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_UNAUTHORIZED, false, "It may be that you didn't supply authentication token. Please login for a valid token."));
        }
        else {
        	//
        	System.out.println("Bearer::" + jwt);
			
			String validUser = this.validateMyToken(jwt);
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
							String currentDate = GenericHelpers.getCurrentDateString();
			            	String threeMonthsBackFromToday = GenericHelpers.addMonthToCurrentDate(3);
			            	List<StatementModel> statement = s.getAllStatementsByDate(threeMonthsBackFromToday, currentDate);
			        		String statementJsonString = gson.toJson(statement);
			        		               
			        		out.print(statementJsonString);
						}
						else {
							
							String[] param1=null, param2=null, param3 = null, param4 = null, param5 = null;
							String paramAccountId="", paramFromDate="", paramToDate="", paramFromAmount="", paramToAmount = "";
							String sqlQuery = "select * from statement where";
							param1 = mapParameters.get("account_id");
							param2 = mapParameters.get("from_date");
							param3 = mapParameters.get("to_date");
							param4 = mapParameters.get("from_amount");
							param5 = mapParameters.get("to_amount");
							if(param1 != null)
							{
								paramAccountId = param1[0];
								System.out.println("account_id Supplied::" + paramAccountId);
							}
							if(param2 != null)
							{
								paramFromDate = param2[0];
								System.out.println("from_date Supplied::" + paramFromDate);
							}
							if(param3 != null)
							{
								paramToDate = param3[0];
								System.out.println("to_date Supplied::" + paramToDate);
							}
							if(param4 != null)
							{
								paramFromAmount = param4[0];
								System.out.println("from_amount Supplied::" + paramFromAmount);
							}
							if(param5 != null)
							{
								paramToAmount = param5[0];
								System.out.println("from_amount Supplied::" + paramToAmount);
							}
							
							
							if(!paramAccountId.isEmpty())
							{
								sqlQuery = sqlQuery + " (account_id=" + paramAccountId + ")";
							}
							if(!paramAccountId.isEmpty() && !paramFromDate.isEmpty() && !paramToDate.isEmpty())
							{
								sqlQuery = sqlQuery + " and (datefield >='" + paramFromDate + "' and datefield <='" + paramToDate + "'";
							}
							else if(paramAccountId.isEmpty() && !paramFromDate.isEmpty() && !paramToDate.isEmpty())
							{
								sqlQuery = sqlQuery + " (datefield >='" + paramFromDate + "' and datefield <='" + paramToDate + "'";
							}
														
							if((!paramFromAmount.isEmpty() && !paramToAmount.isEmpty()) && (!paramAccountId.isEmpty() || (!paramFromDate.isEmpty() && !paramToDate.isEmpty())))
							{
								sqlQuery = sqlQuery + " and (amount >=" + paramFromAmount + " and amount <=" + paramToAmount + ")";
							}
							else if((!paramFromAmount.isEmpty() && !paramToAmount.isEmpty()) && (paramAccountId.isEmpty() || (paramFromDate.isEmpty() && paramToDate.isEmpty())))
							{
								sqlQuery = sqlQuery + " (amount >=" + paramFromAmount + " and amount <=" + paramToAmount + ")";
							}
														
							//make sure that all parameters are right
							if(!paramAccountId.isEmpty() || !paramFromDate.isEmpty() || !paramToDate.isEmpty() && !paramFromAmount.isEmpty() && !paramToAmount.isEmpty())
							{
								//
								List<StatementModel> statement = s.getAllStatementsByQuery(sqlQuery);
				        		String statementJsonString = gson.toJson(statement);
				        		               
				        		out.print(statementJsonString);
							}
							else {
								GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_BAD_REQUEST, false, "Parameter request not right."));
							}
							
						
						}
					}
					else if(u.getRole() != null && u.getRole().equalsIgnoreCase("developer") && u.getUsername().equalsIgnoreCase("testuser"))
					{						
						if(!mapParameters.isEmpty())
			            {
							GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_UNAUTHORIZED, false, "This user is not allowed to specify query string."));
							
			            }
						else {
							String currentDate = GenericHelpers.getCurrentDateString();
			            	String threeMonthsBackFromToday = GenericHelpers.addMonthToCurrentDate(3);
			            	List<StatementModel> statement = s.getAllStatementsByDate(threeMonthsBackFromToday, currentDate);
			        		String statementJsonString = gson.toJson(statement);
			        		               
			        		out.print(statementJsonString);
						}
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
        		
	}

}
