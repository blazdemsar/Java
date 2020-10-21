<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<style>
.error {
	color: red;
	font-style: italic;
	font-weight: bold
}
</style>
<script>
	function f1() {
		if (document.userForm.userId.value == "") {
			alert("userId is a numeric field, please enter a numeric value for User Id.");
			document.userForm.userId.focus();
		}
	}
</script>
</head>
<body>

	<div align="center">
		<h3>User Management</h3>
		<sec:authorize access="isAuthenticated()">
		Welcome <strong>${pageContext.request.userPrincipal.name}</strong>&nbsp; <a
				href="login?logout">Logout</a>
		</sec:authorize>
		<br />
		<frm:form action="saveUser" method="POST" modelAttribute="user"
			name="userForm">
			<table>
				<tr>
					<td>User Id:</td>
					<td><frm:input name="userId" path="userId" /></td>
					<td><frm:errors path="userId" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><frm:input name="name" path="name" /></td>
					<td><frm:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><frm:input name="password" path="password" /></td>
					<td><frm:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><frm:input name="email" path="email" /></td>
					<td><frm:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Roles:</td>
					<td><c:forEach items="${roleSet}" var="role">
							<c:choose>
								<c:when test="${fn:contains(selectedRoles, role)}">
									<strong>${role.name}</strong>
									<frm:checkbox path="roles" value="${role.roleId}" checked="true" />&nbsp;&nbsp;
								</c:when>
								<c:otherwise>
									${role.name}<frm:checkbox path="roles" value="${role.roleId}" />&nbsp;&nbsp;
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</td>
					<td><frm:errors path="roles" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="submit" value="submit" /></td>
				</tr>
			</table>
		</frm:form>
		<c:if test="${not empty users}">
			<hr />
			<table>
				<tr>
					<th>User Id</th><th>Name</th><th>Password</th><th>Email</th><th>Roles</th><th colspan="2">Action</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.userId}</td>
						<td>${user.name}</td>
						<td>${user.password}</td>
						<td>${user.email}</td>
						<td><c:forEach items="${user.roles}" var="role">${role.name}&nbsp;</c:forEach></td>
						<td><a href="deleteUser?userId=${user.userId}">Delete</a>
							&nbsp;&nbsp; <a href="updateUser?userId=${user.userId}">Update</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>