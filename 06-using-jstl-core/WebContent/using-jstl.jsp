<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, java.util.ArrayList, com.blazdemsar.beans.Employee" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JavaServerPages Standard Tag Library</title>
</head>
<body>

	<c:set var="country" value="USA"></c:set>
	<p>Country: ${country}</p>
	Using "c:out" tag:
	<c:out value="${country}"></c:out>
	<c:remove var="country"></c:remove>

	<p><strong>After removing the variable - Country: ${country}</strong></p>

	<jsp:useBean id="x" class="com.blazdemsar.beans.Employee">
		<br>EmpId: ${x.empId}
		<br>Name: ${x.name}
		<br>Job: ${x.job}
		<br>Salary: ${x.salary}
	</jsp:useBean>

	<br>
	<p><strong>Using c:if</strong></p>
	<c:if test="${x.name=='Blaz'}">

		<br>Hello ${x.name}, you are a trainee.

	</c:if>

	<br>
	<strong>Using c:choose</strong>
	<c:choose>
		<c:when test="${x.name=='Blaz'}">
			<br>Hello ${x.name}, you are a trainee.
	</c:when>
		<c:when test="${x.name!='Blaz'}">
			<br>Hello ${x.name}, you are not a trainee.
	</c:when>
		<c:otherwise>
			<br>inside of c:otherwise
	</c:otherwise>
	</c:choose>

	<br>
	<strong>Using c:forEach</strong>
	<c:forEach var="i" begin="0" end="12" step="2">
		<p>${i}</p>
	</c:forEach>

	<%
		String[] names = new String[4];
	names[0] = "Blaz";
	names[1] = "Domen";
	names[2] = "Natalija";
	names[3] = "Manu";
	%>
	
	<br>
	<p><strong>Printing an array of names using c:forEach tag:</strong></p>
	<c:forEach items="<%=names%>" var="name">
		<br><c:out value="${name}"></c:out>
	</c:forEach>
	
	<p><strong>Printing list of employees using c:forEach tag</strong></p>
	<%!
	List<Employee> employees = new ArrayList<>();
	%>
	
	<%
	employees.add(new Employee(1, "Blaz", "Programmer", 8000));
	employees.add(new Employee(2, "Domen", "DBA", 7500));
	employees.add(new Employee(3, "Natalija", "MechE", 8500));
	employees.add(new Employee(4, "Manu", "Project Manager", 9000));
	%>
	
	<c:set var="employees" value="<%=employees%>" />
	<br>No. of employees using size(): ${employees.size()}
	<table border="1">
		<tr>
			<th colspan="4">No. of employees</th>
		</tr>
		<tr>
			<td colspan="4" align="center">${employees.size()}</td>
		</tr>
		<tr>
			<th>EmpID</th><th>Name</th><th>Job</th><th>Salary</th>
		</tr>
		<c:forEach items="${employees}" var="e">
			<tr><td>${e.empId}</td><td>${e.name}</td><td>${e.job}</td><td>${e.salary}</td>
		</c:forEach>
	</table>
	
	<p><strong>Using c:forTokens</strong></p>
	<%
	String url="com.blazdemsar.xyz.abc";
	%>
	
	<c:forTokens items="<%=url%>" var="substring" delims=".">
	<br>${substring}
	</c:forTokens>
	
	<p><strong>Using c:catch</strong></p>
	<c:catch var="exception">
		<br><% int y = 100/0; %>
	</c:catch>
	<br>Exception: ${exception}
	<br>Message: ${exception.message}


</body>
</html>