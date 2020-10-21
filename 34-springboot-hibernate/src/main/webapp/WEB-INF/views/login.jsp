<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" >

<meta charset="ISO-8859-1">
<title>Login Form - Synergisticit Pvt Ltd</title>
</head>
<body>
<div align="center">
<h3>Login Form - Synergisticit Private Limited</h3>
<p><img src="img/spring.png" height="30" width="30" alt="Spring Logo"/>
<form action="login" method="POST">
<table>

<tr>
<td> User Name: </td><td><input type="text" name="username" /></td>
</tr>

<tr>
<td> Password: </td><td><input type="password" name="password" /></td>
</tr>

<tr>
<td colspan="2" align="center"><input type="submit" name="submit"  value="submit"  class="btn btn-primary"/></td>
</tr>

</table>

</form>
</div>


</body>
</html>