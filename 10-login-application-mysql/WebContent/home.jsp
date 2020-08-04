<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

<h1>Welcome to the page</h1>
<a href="login.jsp" >Login</a>
<br/><br/>
<a href="signup.jsp" >Sign Up</a>
<br/><br/>
<a href="EmployeeServlet?action=all">All Employees</a>

<table>
<tr>
<th>Employee ID</th><th>Employee Name</th><th>Job Type</th><th>Salary</th>
</tr>
<c:forEach items="${listOfEmployees}" var="e">
<tr>
<td>${e.empId}</td>
<td>${e.name}</td>
<td>${e.job}</td>
<td>${e.salary}</td>
</tr>
</c:forEach>
</table>

</body>
</html>