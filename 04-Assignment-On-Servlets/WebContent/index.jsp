<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter your information here:</title>
</head>
<body>
	<form action="index.jsp" method="post">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" /></td>
			</tr>

			<tr>
				<td>Job:</td>
				<td><input type="text" name="job" /></td>
			</tr>

			<tr>
				<td>Salary:</td>
				<td><input type="text" name="salary" /></td>
			</tr>
			<tr>
				<td>Date of Birth:</td>
				<td><input type="date" name="dob" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

	<%
	session.setAttribute("name", request.getParameter("name"));
	session.setAttribute("job", request.getParameter("job"));
	session.setAttribute("salary", request.getParameter("salary"));
	session.setAttribute("dob", request.getParameter("dob"));
	%>

	<table>
		<tr>
			<th>Attribute Name</th>
			<th>Attribute Value</th>
		</tr>
		<%
			for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {

			String attrName = e.nextElement();
			out.println("<tr><td>" + attrName + "</td><td>" + session.getAttribute(attrName) + "</td></tr>");

		}

		session.invalidate();
		%>
	</table>

</body>
</html>