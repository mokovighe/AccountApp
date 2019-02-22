package com.ebuka.mainServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;

import com.ebuka.dataObjects.IUserDAO;
import com.ebuka.dataObjects.UserDAO;
import com.ebuka.model.UserModel;
import com.ebuka.utils.GenericHelpers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@WebServlet(description = "Get authorized token", urlPatterns = { "/api/token" })
public class Token extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Token() {
        super();
        
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		PrintWriter out = response.getWriter();
		
		
		res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
		
		String authHeader = req.getHeader("Authorization");
		if (authHeader != null) {
			System.out.println("Authorization header is not null");
			
			StringTokenizer stoken = new StringTokenizer(authHeader);
			if (stoken.hasMoreTokens()) {
				String basic = stoken.nextToken();
				if (basic.equalsIgnoreCase("Basic")) {
					System.out.println("Basic Authorization header");
					try {
	                    String credentials = new String(Base64.decodeBase64(stoken.nextToken()), "UTF-8");
	                    System.out.println("Credentials: " + credentials);
	                    int p = credentials.indexOf(":");
	                    if (p != -1) {
	                        String login = credentials.substring(0, p).trim();
	                        String password = credentials.substring(p + 1).trim();
	                        
	                        //check user login credentials

	                        IUserDAO ud = new UserDAO();	
	        				UserModel u = ud.getUser(login, password);
	        				if(u != null && u.getRole() != null && u.getRole().equalsIgnoreCase("developer"))
	        				{
	        					// Issue a token for the user
	        					try {
	        						String token = issueJWTToken(login);
	        						GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_OK, true, "Bearer " +token));		        					
	        					}
	        					catch(Exception e) {
	        						GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_FORBIDDEN, false, "Exception::" + e));			                    	
	        					}
	        		         
	        				}
	        				else {
	        					GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_FORBIDDEN, false, "Invalid credentials or role not allowed"));
		                    	
	        				}
	        				              
	                    } else {
	                    	System.out.println("Invalid authentication token");
	                    	GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_BAD_REQUEST, false, "Invalid authentication token"));
	                    	
	                    }
	                } catch (UnsupportedEncodingException e) {
	                	System.out.printf("Couldn't retrieve authentication", e);
	                	GenericHelpers.showMessage(out, GenericHelpers.jsonResponse(HttpServletResponse.SC_UNAUTHORIZED, false, "Couldn't retrieve authentication. " + e));
	                
	                }
				}
			}
		}
		else {
			System.out.println("Authorization header is null");
		}
	}
	
	private String issueJWTToken(String login)
	{
		final String SECRET = Base64.encodeBase64String(GenericHelpers.secretKey.getBytes());
	    String token = Jwts.builder()
	                     .setSubject(login)
	                     .setExpiration(GenericHelpers.addMinuteToCurrentDate(5))
	                     .signWith(SignatureAlgorithm.HS512, SECRET)
	                     .compact();
	    return token;
	}	

}
