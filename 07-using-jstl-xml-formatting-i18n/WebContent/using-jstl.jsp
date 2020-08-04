<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSTL-xml-i18n-formatting</title>
</head>
<body>
	<strong>Using jstl's xml tag library...</strong>
	<c:import url="employees.xml" var="xmlFile"/>
	<x:parse doc="${xmlFile}" var="xml" />
	<br>EmpId:
	<x:out select="$xml/employees/employee[2]/empId" />
	<br>Name:
	<x:out select="$xml/employees/employee[2]/name" />
	<br>Job:
	<x:out select="$xml/employees/employee[2]/job" />
	<br>Salary:
	<x:out select="$xml/employees/employee[2]/salary" />
	
	<br><br>4th Employee
	<br>EmpId:
	<x:out select="$xml/employees/employee[4]/empId" />
	<br>Name:
	<x:out select="$xml/employees/employee[4]/name" />
	<br>Job:
	<x:out select="$xml/employees/employee[4]/job" />
	<br>Salary:
	<x:out select="$xml/employees/employee[4]/salary" />
	
	<p>Now printing all the employees in employees.xml whose salary is between 1500 and 2000
	<table border="1">
		<tr>
			<th>EmpId</th><th>Name</th><th>Job</th><th>Salary</th>
		</tr>
		<x:forEach select="$xml/employees/employee" >
			<x:if select="salary >= 1500 and salary <= 2000">
				<tr>
					<td><x:out select="empId" /></td>
					<td><x:out select="name" /></td>
					<td><x:out select="job" /></td>
					<td><x:out select="salary" /></td>
				</tr>
			</x:if>
		</x:forEach>
	</table>
	
</body>
</html>