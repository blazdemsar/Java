<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thank you!</title>
</head>
<body>

<table>
<tr>
<th>Attribute name</th><th>Attribute Value</th>
</tr>

<%
for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {
	
	String attributeName = e.nextElement();
	out.println("<tr><td>" + attributeName + "</td><td>" + session.getAttribute(attributeName) + "</td></tr>");
}

session.invalidate();
out.println("You have been logged out.");
%>

</table>

<form action="index.jsp">
<input type="submit" name="submit" value="Return Home"/>
</form>


</body>
</html>