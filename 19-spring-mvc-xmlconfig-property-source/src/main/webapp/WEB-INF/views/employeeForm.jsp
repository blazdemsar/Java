<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee From</title>
</head>
<body>
	<div align="center">
		<frm:form action="saveEmployee" method="post"
			modelAttribute="employee">
			<table>
				<tr>
					<td>Emp ID:</td>
					<td><frm:input type="number" name="empId" path="empId" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><frm:input type="text" name="name" path="name" /></td>
				</tr>
				<tr>
					<td>Job:</td>
					<td><frm:input type="text" name="job" path="job" /></td>
				</tr>
				<tr>
					<td>Salary:</td>
					<td><frm:input type="number" name="salary" path="salary" /></td>
				</tr>
				<tr>
					<td>Address Line 1:</td>
					<td><frm:input type="text" name="addressLine1"
							path="address.addressLine1" /></td>
				</tr>
				<tr>
					<td>Address Line 2:</td>
					<td><frm:input type="text" name="addressLine2"
							path="address.addressLine2" /></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><frm:input type="text" name="city" path="address.city" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><frm:input type="text" name="state" path="address.state" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						name="submit" value="Submit" /></td>
				</tr>
			</table>
		</frm:form>
		<br />${employee} <br />

		<table>
			<tr>
				<th>Emp ID</th><th>Name</th><th>Job</th><th>Salary</th><th>Address Line 1</th><th>Address Line 2</th><th>City</th><th>State</th>
			</tr>
			<c:forEach items="${employees}" var="e">
				<tr>
					<td>${e.empId}</td><td>${e.name}</td><td>${e.job}</td><td>${e.salary}</td><td>${e.address.addressLine1}</td><td>${e.address.addressLine2}</td><td>${e.address.city}</td><td>${e.address.state}</td><td><a href="updateEmploye&empId=${e.empId}">Update</a></td><td><a href="deleteEmploye&empId=${e.empId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>