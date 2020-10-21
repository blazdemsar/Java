<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>   
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions"   prefix="fn" %> 
 
 <%@ taglib uri="http://www.springframework.org/tags/form"   prefix="frm" %> 
 <%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec" %> 
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" > -->

<link  rel="stylesheet" href="css/bootstrap.min.css" >

<!-- <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" > -->

<%-- <link type="text/css" rel="stylesheet" href="<c:url value='css/bootstrap.min.css'/>" > --%>


<meta charset="ISO-8859-1">
<title>User Management</title>

<style>
.error{
	color:red;
	font-style:italic;
	font-weight:bold
}
</style>

<script>
function f1(){	
	if(document.userForm.userId.value==""){
		alert("userId is a numeric field, please enter a numeric value for User Id.");
		document.userForm.userId.focus();
	}
	
}

</script>
</head>
<body>

<div align="center">
<p><img src="img/spring.png" height="30" width="30" border="0" alt="Spring Logo"/>
<h3>User Management</h3>
<sec:authorize access="isAuthenticated()">

Welcome<Strong> ${pageContext.request.userPrincipal.name}</Strong>&nbsp; <a href="login?logout" >Logout</a>
</sec:authorize>

<p/>

<frm:form action="saveUser" method="POST"  modelAttribute="user"  name="userForm">
<table  class="table table-striped">
<tr>
<td>User Id: </td><td><frm:input name="userId"  path="userId" /></td><td><frm:errors path="userId" cssClass="error"/></td>
</tr>

<!-- cssStyle="color:red" -- this is defned at html control level -->
<tr>
<td>Name: </td><td><frm:input path="name"/></td><td><frm:errors path="name" cssClass="error" /></td>
</tr>

<tr>
<td>Password: </td><td><frm:input type="password" path="password"/></td><td><frm:errors path="password" cssClass="error"/></td>
</tr>


<tr>
<td>Email: </td><td><frm:input path="email"/></td><td><frm:errors path="email"  cssClass="error" /></td>
</tr>


<tr>
<td>Roles: </td>
<td>
<c:forEach items="${roleSet}" var="role">
<c:choose>
	<c:when test="${fn:contains(selectedRoles, role)}">
		<strong>${role.name}</strong><frm:checkbox path="roles"  value="${role.roleId}" checked="true"/>&nbsp;&nbsp;
	</c:when>
	<c:otherwise>
		${role.name}<frm:checkbox path="roles"  value="${role.roleId}"/>&nbsp;&nbsp;
	</c:otherwise>
</c:choose>
</c:forEach>
</td>

<td><frm:errors path="roles"  cssClass="error" /></td>
</tr>

<tr>
<td colspan="3" align="center"><input type="submit"  value="submit"  class="btn btn-primary btn-lg"  size="300"/></td>  <!-- onclick="f1();" -->
</tr>

</table>

</frm:form>

<c:if test="${not empty users}" >
<hr/>
<table class="table table-bordered table-light">
<thead class="thead-dark">
<tr>
<th>User Id</th><th>Name</th><th>Password</th><th>Email</th><th>Roles</th><th colspan="2">Action</th>
</tr>
</thead>
<c:forEach items="${users}" var="user" >
<tbody>
<tr>
<td>${user.userId}</td>
<td>${user.name}</td>
<td>${user.password}</td>
<td>${user.email}</td>
<td>
<c:forEach items="${user.roles}" var="role" >
   ${role.name}&nbsp;
</c:forEach>
</td>

<td>
<a href="deleteUser?userId=${user.userId}">Delete</a> &nbsp;&nbsp;
<a href="updateUser?userId=${user.userId}">Update</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>

</c:if>


</div>
</body>
</html>

<!-- https://getbootstrap.com -->