<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Personal Details</title>
</head>
<body>
	<%
		String name = request.getParameter("name");
	String job = request.getParameter("job");
	String salary = request.getParameter("salary");

	// out implicit object is of type PrintWriter
	out.println("<br>Name: " + name);
	out.println("<br>Job: " + job);
	out.println("<br>Salary: " + salary);

	session.setAttribute("Name", name);
	session.setAttribute("Job", job);
	session.setAttribute("Salary", salary);
	%>

	<form action="ParameterValueServlet" method="post">
		<table>
			<tr>
				<td>Date of Birth: </td>
				<td><input type="date" name="dob"></td>
			</tr>
			<tr>
				<td>Gender: </td>
				<td><input type="radio" name="gender" value="male" checked />Male&nbsp&nbsp</td>
				<td><input type="radio" name="gender" value="female" />Female</td>			
			</tr>
			<tr>
				<td>Email: </td>
				<td><input type="email" name="email" /></td>
			</tr>
			<tr>
				<td>Skills: </td>
				<td>
					<select name="skills">
						<option value="Java 11">Java 11</option>
						<option value="Spring 5.x" >Spring 5.x</option>
						<option value="Hibernate 5.x">Hibernate 5.x</option>
						<option value="MySQL 8.x">MySQL 8.x</option>
						<option value="Angular">Angular</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Insured? </td>
				<td>
					<input type="checkbox" name="insured" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="submit" value="Submit" /></td>
			</tr>
			
		</table>
	</form>

</body>
</html>