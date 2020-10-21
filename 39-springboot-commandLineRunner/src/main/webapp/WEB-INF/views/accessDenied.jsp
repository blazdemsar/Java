<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Access Denied</title>
</head>
<body>

<p>${message} </p>
<p>Hi ${pageContext.request.userPrincipal.name }, you don't have access to this page.
<br> Click <a href="/" >Home</a> to return to home page.

</body>
</html>