<%@ page import="java.sql.Connection" %>
<%@ page import="com.ebuka.dbWork.DBManager" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	
			Connection conn = DBManager.getDBConnection();
			if(conn == null)
			{
		out.print("DB Connection Failed!");
			}
			else
			{
		out.print("DB Connection Succeeded!");
			}
	%>
</body>
</html>