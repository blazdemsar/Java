<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>   
 <%@ taglib uri="http://www.springframework.org/tags/form"   prefix="frm" %> 
 <%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
</head>
<body>

<div align="center">
<sec:authorize access="isAuthenticated()">

Welcome<Strong> ${pageContext.request.userPrincipal.name}</Strong>&nbsp; <a href="login?logout" >Logout</a>
</sec:authorize>
<frm:form action="saveUser" method="POST"  modelAttribute="user">
<table>
<tr>
<td>User Id: </td><td><frm:input path="userId"/></td><td><frm:errors path="userId" /></td>
</tr>

<tr>
<td>Name: </td><td><frm:input path="name"/></td><td><frm:errors path="name" /></td>
</tr>

<tr>
<td>Password: </td><td><frm:input type="password" path="password"/></td><td><frm:errors path="password" /></td>
</tr>


<tr>
<td>Email: </td><td><frm:input path="email"/></td><td><frm:errors path="email" /></td>
</tr>
</table>

<tr>
<td>Roles: </td>
<td>
<c:forEach items="${roleSet}" var="role">
${role.name}<frm:checkbox path="roles"  value="${role.roleId}"/>
</c:forEach>
</td>

<td><frm:errors path="roles" /></td>
</tr>

<tr>
<td colspan="3" align="center"><input type="submit"  value="submit" /></td>
</tr>

</frm:form>

<c:if test="${not empty users}" >
<hr/>
<table>
<tr>
<th>User Id</th><th>Name</th><th>Password</th><th>Email</th><th>Roles</th>
</tr>
<c:forEach items="${users}" var="user" >
<tr>
<td>${user.userId}</td>
<td>${user.name}</td>
<td>${user.password}</td>
<td>${user.email}</td>
<td>
<c:forEach items="${user.roles}" var="role" >
<strong>${role.name}&nbsp;</strong>
</c:forEach>
</td>
</tr>
</c:forEach>

</table>

</c:if>


</div>
</body>
</html>