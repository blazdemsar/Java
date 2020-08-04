<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Form</title>
</head>
<body>
	<div align="center">
		<frm:form action="saveEmployee" method="post" modelAttribute="employee">
			<table>
				<tr>
					<td>Emp ID:</td>
					<td><frm:input path="empId"/></td>
					<td><frm:errors path="empId"/></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><frm:input path="name"/></td>
					<td><frm:errors path="name"/></td>
				</tr>
				<tr>
					<td>Job:</td>
					<td><frm:input path="job"/></td>
					<td><frm:errors path="job"/></td>
				</tr>
				<tr>
					<td>Salary:</td>
					<td><frm:input path="salary"/></td>
					<td><frm:errors path="salary"/></td>
				</tr>
				<tr>
					<td>Date of Birth:</td>
					<td><frm:input type="date" path="dob" required="true"/></td>
					<td><frm:errors path="dob"/></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><frm:radiobutton name="gender" value="male" path="gender" label="male" checked=true/></td>
					<td><frm:radiobutton name="gender" value="female" path="gender" label="female"/></td>
					<td><frm:errors path="gender"/></td>
				</tr>
				<tr>
					<td>Skills:</td>
					<td><frm:checkbox name="skills" path="skills" value="Java" label="Java"/></td>
					<td><frm:checkbox name="skills" path="skills" value="Spring Framework" label="Spring Framework"/></td>
					<td><frm:checkbox name="skills" path="skills" value="Node.js" label="Node.js"/></td>
					<td><frm:checkbox name="skills" path="skills" value="React.js" label="React.js"/></td>
					<td><frm:checkbox name="skills" path="skills" value="Hibernate" label="Hibernate"/></td>
					<td><frm:checkbox name="skills" path="skills" value="PL/SQL" label="PL/SQL"/></td>
					<td><frm:checkbox name="skills" path="skills" value="MicroServices" label="MicroServices"/></td>
					<td><frm:checkbox name="skills" path="skills" value="Python" label="Python"/></td>
					<td><frm:errors path="skills"/></td>
				</tr>
				<tr>
					<td>Languages:</td>
					<td><frm:select name="spokenLanguages" path="spokenLanguages" items="${spokenLanguages}"/></td>
					<td><frm:errors path="spokenLanguages"/></td>
				</tr>
				<tr>
					<td>Hobbies:</td>
					<td><frm:select name="hobbies" path="hobbies">
						<frm:option value="" label="Please Select a Value"></frm:option>
						<frm:options items="${spokenLanguages }"/>
						</frm:select></td>
					<td><frm:errors path="hobbies"/></td>
				</tr>
				<tr>
					<td>Employee Type:</td>
					<td><frm:select name="employeetype" path="employeetype">
						<frm:option value="" label="Please Select a Value"></frm:option>
						<frm:options items="${spokenLanguages }"/>
						</frm:select></td>
					<td><frm:errors path="employeetype"/></td>
				</tr>
				<tr>
					<td>Insured?:</td>
					<td><frm:checkbox name="insured" path="insured" value="false"/></td>
					<td><frm:errors path="insured"/></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><frm:input path="email"/></td>
					<td><frm:errors path="email"/></td>
				</tr>
				<tr>
					<td>Mobile:</td>
					<td><frm:input path="mobile"/></td>
					<td><frm:errors path="mobile"/></td>
				</tr>
				<tr>
					<td>Nationality:</td>
					<td><frm:radiobutton name="nationality" path="nationality" value="USA" label="USA"/></td>
					<td><frm:radiobutton name="nationality" path="nationality" value="Slovenia" label="Slovenia"/></td>
					<td><frm:radiobutton name="nationality" path="nationality" value="Spain" label="Spain"/></td>
					<td><frm:radiobutton name="nationality" path="nationality" value="Italy" label="Italy"/></td>
					<td><frm:radiobutton name="nationality" path="nationality" value="Serbia" label="Serbia"/></td>
					<td><frm:radiobutton name="nationality" path="nationality" value="UK" label="UK"/></td>
					<td><frm:radiobutton name="nationality" path="nationality" value="France" label="France"/></td>
					<td><frm:errors path="nationality"/></td>
				</tr>
				<tr>
					<td>Address Line 1:</td>
					<td><frm:input path="address.addressline1" /></td>
					<td><frm:errors path="address.addressline1" /></td>
				</tr>
				<tr>
					<td>Address Line 2:</td>
					<td><frm:input path="address.addressline2" /></td>
					<td><frm:errors path="address.addressline2" /></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><frm:input path="address.city" /></td>
					<td><frm:errors path="address.city" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><frm:input path="address.state" /></td>
					<td><frm:errors path="address.state" /></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="submit" value="Submit"/></td>
				</tr>
			</table>
		</frm:form>
	</div>
</body>
</html>