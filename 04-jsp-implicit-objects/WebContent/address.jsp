<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter your address here:</title>
</head>
<body>
	<%

	session.setAttribute("dob", request.getParameter("dob"));
	session.setAttribute("gender", request.getParameter("gender"));
	session.setAttribute("email", request.getParameter("email"));
	session.setAttribute("skills", request.getParameter("skills"));
	session.setAttribute("insured", request.getParameter("insured"));
	%>

	<form action="message.jsp">
		<table>
			<tr>
				<td>Address Line 1:</td>
				<td><input type="text" name="addresLine1" /></td>
			</tr>

			<tr>
				<td>Address Line 2:</td>
				<td><input type="text" name="addresLine2" /></td>
			</tr>

			<tr>
				<td>City:</td>
				<td><input type="text" name="city" /></td>
			</tr>

			<tr>
				<td>State:</td>
				<td><input type="text" name="state" /></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center" ><input type="submit" name="submit" value="Submit" /></td>
			</tr>

		</table>
	</form>
</body>
</html>