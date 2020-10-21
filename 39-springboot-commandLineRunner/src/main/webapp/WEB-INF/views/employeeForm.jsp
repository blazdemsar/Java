<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/security/tags"   prefix="sec" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Form</title>
</head>
<body>
<div align="center">
<p><img src="img/spring.png" height="50"  width="50" alt="Spring Logo"/>
<sec:authorize access="isAuthenticated()">
Welcome <Strong> ${pageContext.request.userPrincipal.name}</Strong>&nbsp; <a href="login?logout" >Logout</a>
</sec:authorize>

<frm:form   action="saveEmployee" method="POST" modelAttribute="employee">
<table border="1">
<tr>
	<td>Emp ID:</td>
	<td><frm:input path="empId"/></td>
	<td><frm:errors path="empId" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Name:</td>
	<td><frm:input path="name"/></td>
	<td><frm:errors path="name" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Job:</td>
	<td><frm:input path="job"/></td>
	<td><frm:errors path="job" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Salary:</td>
	<td><frm:input path="salary"/></td>
	<td><frm:errors path="salary" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Date of Birth:</td>
	<td><frm:input type="date" path="dob" /></td>
	<td><frm:errors path="dob" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Gender:</td>
	<td>
	<frm:radiobutton name="gender" path="gender" value="male" label ="male" checked="true"/>
	<frm:radiobutton name="gender" path="gender" value="female" label ="female" />
	</td>
	<td><frm:errors path="gender" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Skills:</td>
	<td>
	<%-- <frm:checkbox name="skills" path="skills"  value="Java" label="Java" checked="true"/> --%>
	<frm:checkbox name="skills" path="skills"  value="Java" label="Java" />
	<frm:checkbox name="skills" path="skills"  value="Spring" label="Spring" />
	<frm:checkbox name="skills" path="skills"  value="JPA" label="JPA" />
	<frm:checkbox name="skills" path="skills"  value="Hibernate" label="Hibernate" />
	<frm:checkbox name="skills" path="skills"  value="MySQL" label="MySQL" />
	<frm:checkbox name="skills" path="skills"  value="Micro Services" label="Micro Services" />
	</td>
	<td><frm:errors path="skills" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Spoken Languages:</td>
	<td>
	<frm:select  name="spokenLanguages" path="spokenLanguages" items="${spokenLanguages}" />	
	</td>
	<td><frm:errors path="spokenLanguages" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Hobbies:</td>
	<td>
	<frm:select  name="hobbies" path="hobbies"  >	
		<frm:option value="" lable="Please select a value" ></frm:option>
	    <frm:options items="${hobbies}" />
	</frm:select>
	</td>
	<td><frm:errors path="hobbies" cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Employee Type:</td>
	<td>
	<frm:select  name="employeeType" path="employeeType"  >	
		<frm:option value="" lable="Please select a value" ></frm:option>
	    <frm:options items="${employeeType.values}" />
	</frm:select>
	</td>
	<td><frm:errors path="employeeType"  cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Insured ?:</td>
	<td>
	<frm:checkbox name="insured" path="insured"  value="false" />	
	</td>
	<td><frm:errors path="insured"  cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Email:</td>
	<td><frm:input path="email"/></td>
	<td><frm:errors path="email"  cssStyle="color:red"/></td>
</tr>

<tr>
	<td>Mobile:</td>
	<td><frm:input path="mobile"/></td>
	<td><frm:errors path="mobile"  cssStyle="color:red"/></td>
</tr>

<tr>
<td>Nationality</td>
<td>
<frm:radiobutton name="nationality"  path="nationality" value="USA" label="USA" />
<frm:radiobutton name="nationality"  path="nationality" value="UK" label="UK" />
<frm:radiobutton name="nationality"  path="nationality" value="China" label="China" />
<frm:radiobutton name="nationality"  path="nationality" value="India" label="India" />
<frm:radiobutton name="nationality"  path="nationality" value="Australia" label="Australia" />
</td>
<td><frm:errors path="nationality" cssStyle="color:red"/></td>
</tr>

<tr>
<td colspan="3" align="center"><strong>Billing Address</strong></td>
</tr>
<tr>
<td>Address Line1:</td>
<td><frm:input path="billing_Address.addressLine1" /></td>
<td><frm:errors path="billing_Address.addressLine1" cssStyle="color:red"/></td>
</tr>

<tr>
<td>Address Line2:</td>
<td><frm:input path="billing_Address.addressLine2" /></td>
<td><frm:errors path="billing_Address.addressLine2" cssStyle="color:red"/></td>
</tr>

<tr>
<td>City:</td>
<td><frm:input path="billing_Address.city" /></td>
<td><frm:errors path="billing_Address.city"  cssStyle="color:red"/></td>
</tr>

<tr>
<td>State:</td>
<td><frm:input path="billing_Address.state" /></td>
<td><frm:errors path="billing_Address.state" cssStyle="color:red"/></td>
</tr>

<tr>
<td colspan="3" align="center"><Strong>Shipping Address</Strong></td>
</tr>
<tr>
<td>Address Line1:</td>
<td><frm:input path="shipping_Address.addressLine1" /></td>
<td><frm:errors path="shipping_Address.addressLine1" cssStyle="color:red"/></td>
</tr>

<tr>
<td>Address Line2:</td>
<td><frm:input path="shipping_Address.addressLine2" /></td>
<td><frm:errors path="shipping_Address.addressLine2" cssStyle="color:red"/></td>
</tr>

<tr>
<td>City:</td>
<td><frm:input path="shipping_Address.city" /></td>
<td><frm:errors path="shipping_Address.city"  cssStyle="color:red"/></td>
</tr>

<tr>
<td>State:</td>
<td><frm:input path="shipping_Address.state" /></td>
<td><frm:errors path="shipping_Address.state" cssStyle="color:red"/></td>
</tr>

<tr>
<td colspan="3" align="center"><strong>Other Details - Passport and Project</strong></td>
<tr>
<tr>
<td>Passport</td>
<td><frm:input path="passport.passportNumber"  name="passportNumber" /></td>
<td><frm:errors path="passport.passportNumber" cssStyle="color:red"/></td>
</tr>

<tr>
<td>Project</td>
<td><frm:input path="project.name"  name="projectName"/></td>
<td><frm:errors path="project.name" cssStyle="color:red"/></td>
</tr>

<tr>
<td colspan="3" align="center" ><input type="submit" value="submit" /></td>
</tr>

<%-- <tr>
<td colspan="3"  ><frm:errors path="*"  cssStyle="color:red"/></td>
</tr>
 --%>
</table>

</frm:form>
<p><p>
<h3>List of Employees</h3>
<table border="1">
<tr>
<th>Emp Id</th><th>Name</th><th>Job</th><th>Salary</th>
<th>Date of Birth</th><th>Gender</th><th>Spoken Languages</th><th>Skills</th>
<th>Hobbies</th><th>Employee Type</th><th>Email</th><th>Mobile</th>
<th>Nationality</th><th>Address Line1</th><th>Address Line2</th><th>City</th><th>State</th>
<th colspan="2">Action</th>
</tr>

<c:forEach items="${employees}" var="e">
<tr>
<td>${e.empId }</td><td>${e.name}</td><td>${e.job}</td><td>${e.salary}</td>
<td>${e.dob}</td><td>${e.gender}</td><td>${e.spokenLanguages}</td><td>${e.skills}</td>
<td>${e.hobbies}</td><td>${e.employeeType}</td><td>${e.email}</td><td>${e.mobile}</td>
<td>${e.nationality}</td>
<td>${e.billing_Address.addressLine1}</td>
<td>${e.billing_Address.addressLine2}</td>
<td>${e.billing_Address.city}</td>
<td>${e.billing_Address.state}</td>
<td><a href="delete?empId=${e.empId}" >Delete</a></td>
<td><a href="update?empId=${e.empId}" >Update</a></td>
</tr>
</c:forEach>
</table>


</div>


</body>
</html>