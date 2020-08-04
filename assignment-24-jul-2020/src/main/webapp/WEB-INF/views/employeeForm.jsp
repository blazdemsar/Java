<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="frm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Form</title>
</head>
<body>
	<div align="center">
		<frm:form action="saveEmployee" method="post" modelAttribute="employee">
			<table>
				<tr>
					<td>EMP ID:</td><td><frm:input type="number" name="empId" path="empId" value="${employee.empId}"/></td>
				</tr>
				<tr>
					<td>Name:</td><td><frm:input type="text" name="name" path="name" value="${employee.name}"/></td>
				</tr>
				<tr>
					<td>Job:</td><td><frm:input type="text" name="job" path="job" value="${employee.job}"/></td>
				</tr>
				<tr>
					<td>Salary:</td><td><frm:input type="number" name="salary" path="salary" value="${employee.salary}"/></td>
				</tr>
				<tr>
					<td>Address Line 1:</td><td><frm:input type="text" name="addressLine1" path="address.addressLine1" value="${employee.address.addressLine1}"/></td>
				</tr>
				<tr>
					<td>Address Line 2:</td><td><frm:input type="text" name="addressLine2" path="address.addressLine2" value="${employee.address.addressLine2}"/></td>
				</tr>
				<tr>
					<td>City:</td><td><frm:input type="text" name="city" path="address.city" value="${employee.address.city}"/></td>
				</tr>
				<tr>
					<td>State:</td><td><frm:input type="text" name="state" path="address.state" value="${employee.address.state}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" name="submit" value="Submit"/></td>
				</tr>
			</table>
		</frm:form>
		<br/>
		<table>
			<tr>
				<th>Emp ID</th><th>Name</th><th>Job</th><th>Salary</th><th>Address Line 1</th><th>Address Line 2</th><th>City</th><th>State</th><th colspan="2" align="center" >Action</th>
			</tr>
			<c:forEach items="${employees}" var="e">
				<tr>
					<td>${e.empId}</td><td>${e.name}</td><td>${e.job}</td><td>${e.salary}</td><td>${e.address.addressLine1}</td><td>${e.address.addressLine2}</td>
					<td>${e.address.city}</td><td>${e.address.state}</td><td><a href="updateEmp?empId=${e.empId}">Update</a></td><td><a href="deleteEmployee?empId=${e.empId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>

		<%-- <frm:form action="findEmployee" method="post" modelAttribute="employee">
			<table>
				<tr>
					<td>Search by Emp ID:</td><td><frm:input type="number" path="empId"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" name="submit" value="Search"/></td>
				</tr>
			</table>
		</frm:form>
		<br/>
		<table>
				<tr>
					<td>${employee.empId}</td><td>${employee.name}</td><td>${employee.job}</td><td>${employee.salary}</td><td>${employee.address.addressLine1}</td><td>${employee.address.addressLine2}</td><td>${employee.address.city}</td><td>${employee.address.state}</td><td><a href="/updateEmployee&empId=${employee.empId}">Update</a></td><td><a href="/deleteEmployee&empId=${employee.empId}">Delete</a></td>
				</tr>

		</table> --%>
		
	
	</div>
</body>
</html>