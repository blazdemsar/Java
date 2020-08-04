<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Final Page: Fetching the values from session...</title>
</head>
<body>

<%
session.setAttribute("message", request.getParameter("message"));
%>

<table>
<tr>
<th>Attribute name</th><th>Attribute Value</th>
</tr>

<%
for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {
	
	String attributeName = e.nextElement();
	out.println("<tr><td>" + attributeName + "</td><td>" + session.getAttribute(attributeName) + "</td></tr>");
}
%>

</table>

<form action="logout.jsp">
<input type="submit" name="submit" value="Log out"/>
</form>

</body>
</html>