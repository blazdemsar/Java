<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Servlet & JSP</title>
</head>
<body>

<br><strong>Web Application</strong> : web applications use a web client and web server(Tomcat),
<br>Web Client (browser) ============> Web Server (Tomcat)
<br>browser => sends request to => server
<br>Server => sends response to => browser

<form action="EmployeeServlet" method="post">

<!-- <br>EMP ID: <input type="text" name="empId" />
<br>Name: <input type="text" name="Name" />
<br>Job: <input type="text" name="Job" />
<br>Salary: <input type="number" name="Salary" />
<br>Date of Birth: <input type="date" name="dob" />
<br><br><input type="submit" name="submit" value="submit" /> -->

<table>
<tr>
<td>EMP ID:</td> <td><input type="text" name="empId" /></td>
</tr>

<tr>
<td>Name:</td> <td><input type="text" name="name" /></td>
</tr>

<tr>
<td>Job:</td> <td><input type="text" name="job" /></td>
</tr>

<tr>
<td>Salary:</td> <td><input type="number" name="salary" /></td>
</tr>

<tr>
<td>Date of Birth:</td> <td><input type="date" name="dob" /></td>
</tr>

<tr>
<td>Email:</td> <td><input type="email" name="email" /></td>
</tr>

<tr>
<td colspan="2" align="center"><input type="submit" name="submit" value="submit" /></td>
</tr>

</table>
</form>

</body>
</html>