<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.synergisticit.domain.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success!</title>
</head>
<body>
<h1>You have successfully logged into your account.</h1>

<%
User user = (User)session.getAttribute("userFromDb");

if (user != null) {
	out.println("Welcome " + user.getUsername() + "!");
	out.println("<p><a href=\"LoginServlet?action=logout\" >Log out</a></p>");
} 
/*
else {
	//response.sendRedirect("login.jsp");
}
*/
%>
</body>
</html>