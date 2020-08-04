<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date, com.blazdemsar.domain.Employee" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Date</title>
</head>
<body>

<%
response.setContentType("text/html");
response.getWriter().print("==============================");
out.println("<br>Date: " + new Date());
out.println("<br>" + new Employee(1, "Blaz", "DBA", 87697));
out.println("<br>" + new Employee(2, "Domen", "Programmer", 34675));
%>
</body>
</html>