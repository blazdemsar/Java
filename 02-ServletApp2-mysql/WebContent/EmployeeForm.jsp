<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Form</title>
<script>
function doSearch() {
	//alert("Dinesh");
	var empId = document.getElementbyId("empId").value;
	var name = document.getElementbyId("name").value;
	var job = document.getElementbyId("job").value;
	var salary = document.getElementbyId("salary").value;
	alert("empId : " + empId + ", name : " + name + ", job : " + job + ", salary : " + salary);
	
	window.location.href="EmployeeServlet?action=search&empId="+empId+"&name="+name+"&job="+job+"&salary="+salary;
}
</script>
</head>
<body>
<div align='center'>
<h3>Employee Management</h3>
<a href="EmployeeServlet?action=all">All Employees</a>

<c:if test="${employee != null}">
<form action="EmployeeServlet?action=update" method="post">
</c:if>

<c:if test="${employee == null}">
<form action="EmployeeServlet?action=save" method="post">
</c:if>

<table>
<tr>
<td>EMP ID:</td> <td><input type="text" id="empId" value="${employee.empId}"/></td>
</tr>

<tr>
<td>Name:</td> <td><input type="text" id="name" value="${employee.name}"/></td>
</tr>

<tr>
<td>Job:</td> <td><input type="text" id="job" value="${employee.job}"/></td>
</tr>

<tr>
<td>Salary:</td> <td><input type="number" id="salary" value="${employee.salary}"/></td>
</tr>

<!-- <tr>
<td>Action:</td> <td><input type="text" name="action" /></td>
</tr> -->

<tr>
<td align="center"><input type="submit" name="submit" value="Submit" /></td>
<td align="center"><input type="button" value="All Employees" onClick="window.location.href='EmployeeServlet?action=all'"/></td>
<td align="center"><input type="button" name="search" value="Search" onClick="doSearch()"/></td>
</tr>

</table>
</form>
</div>
<hr>
<div align="center">
<table>
<tr>
<th>Employee ID</th><th>Employee Name</th><th>Job Type</th><th>Salary</th><th colspan="2">Action</th>
</tr>
<c:forEach items="${listOfEmployees}" var="e">
<tr>
<td>${e.empId}</td>
<td>${e.name}</td>
<td>${e.job}</td>
<td>${e.salary}</td>
<td><a href="EmployeeServlet?action=delete&empId=${e.empId}">Delete</a></td>
<td><a href="EmployeeServlet?action=updateForm&empId=${e.empId}">Update</a></td>
</tr>
</c:forEach>
</table>
</div>

</body>
</html>