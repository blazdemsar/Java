<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
</head>
<body>

<h1>Welcome! Please Sign Up below.</h1>

<form action="LoginServlet" method="post">
	<table>
	<tr>
		<td>Username:</td><td><input type="text" name="username" /></td>
	</tr>
	<tr>
		<td>Password:</td><td><input type="text" name="password" /></td>
	</tr>
	<tr>
		<td>E-mail:</td><td><input type="text" name="email" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Submit" /></td>
	</tr>
	</table>

	<input type="hidden" name="action" value="register" />

</form>

</body>
</html>