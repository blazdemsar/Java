<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.blazdemsar.domain.Employee" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="empdetails" class="com.blazdemsar.domain.Employee" scope="request" />
<jsp:setProperty property="*" name="empdetails" />
<br>The Details of employee is as follows:
<br>EMP ID: <jsp:getProperty property="empId" name="empdetails" />
<br>Name: <jsp:getProperty property="name" name="empdetails" />
<br>Job: <jsp:getProperty property="job" name="empdetails" />
<br>Salary: <jsp:getProperty property="salary" name="empdetails" />

</body>
</html>