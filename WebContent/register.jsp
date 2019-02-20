<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Account Registration</h3>
<form action="Login" method="post">
<span>${message}</span><br>
<strong>User Name</strong>:<input type="text" name="username"><br>
<strong>Password</strong>:<input type="password" name="passw"><br>
<strong>Surname</strong>:<input type="text" name="surname"><br>
<strong>Other Names</strong>:<input type="text" name="othernames"><br>
<input type="submit" name="submit" value="Register">
</form>
<br>
If you already have account, please <a href="login.jsp">login</a>.
</body>
</html>