<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Type your message here:</title>
</head>
<body>

<%
session.setAttribute("addressLine1", request.getParameter("addressLine1"));
session.setAttribute("addressLine2", request.getParameter("addressLine2"));
session.setAttribute("city", request.getParameter("city"));
session.setAttribute("state", request.getParameter("state"));
%>

<form action="final.jsp">
<textarea name="message" style="width:300px; height:60px;"></textarea>
<br><br>
<input type="submit" value="Submit" />
</form>

</body>
</html>