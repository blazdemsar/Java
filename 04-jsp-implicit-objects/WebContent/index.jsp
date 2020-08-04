<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter your name, designation and salary here:</title>
</head>
<body>

	<form action="personal-details.jsp" method="post">
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
				<td colspan="2" align="center"><input type="submit" name="submit"
					value="Submit" /></td>
			</tr>



		</table>
	</form>

</body>
</html>