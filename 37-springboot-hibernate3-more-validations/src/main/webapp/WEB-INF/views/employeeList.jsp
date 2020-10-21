<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Form</title>
</head>
<body>
<div align="center"><table>
<p>Welcome ${pageContext.request.userPrincipal.name} !!!</p>
<tr>
<th>Emp Id</th><th>Name</th><th> Job</th><th>Salary</th><th>Action</th>
</tr>

<c:forEach items="${employeesOnPage}" var="employee">
<tr>
<td>${employee.empId}</td>
<td>${employee.name}</td>
<td>${employee.job}</td>
<td>${employee.salary}</td>
<td>
<a href="deleteEmployee?empId=${employee.empId}">Delete&nbsp;&nbsp;</a>
<a href="updateEmployee?empId=${employee.empId}">Update&nbsp;&nbsp;</a>
</td>
</tr>
</c:forEach>
</table>

<br>
<c:set var="noOfPages" value="${totalPages}" />

<%
for(int i=0; i< (int)pageContext.getAttribute("noOfPages"); i++){
	out.println("<a href=\"pagedEmployee?pageNo=" + i + "&pageSize=5&sortedBy=salary\">" + i +"</a>&nbsp;&nbsp;");
}


%>
</div>

</body>
</html>