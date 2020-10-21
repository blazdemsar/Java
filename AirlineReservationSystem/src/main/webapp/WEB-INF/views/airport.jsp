<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/jquery-ui.min.css" rel="stylesheet">
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<title>Airport Management</title>
</head>
<body>
	<sec:authorize
		access="hasAuthority('Administrator') || hasAuthority('Manager')">
		<div class="container border rounded" align="center"
			style="margin: auto; margin-top: 20px; margin-bottom: 20px; background-image: url('./images/TravelandHealthForm.jpg');">
			<div class="row"
				style="margin: auto; margin-top: 40px; margin-bottom: 50px">
				<div class="col-6" align="center">
					<div class="container">
						<h1>Manager Portal - Airports</h1>
					</div>
				</div>
				<div class="col-6" align="center" style="margin-top: 20px">
					<div class="container">
						<c:choose>
							<c:when test="${currentUser != null}">
								<h3>Hello ${currentUser.username}, welcome back!</h3>
							</c:when>
							<c:otherwise>
								<h3>Hello stranger, login or create an account!</h3>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="row" style="margin: auto; margin-top: 20px; margin-bottom: 50px">
			<div class="container rounded"
				style="margin: auto; margin-top: 10px; margin-bottom: 10px;"
				align="center">
				<div class="col-12">
					<div class="btn-group">
						<a class="btn btn-info " href="home">Home</a>
					</div>
					<div class="btn-group">
						<a class="btn btn-info " href="flight">Flight</a>
					</div>
					<sec:authorize access="hasAuthority('Administrator') || hasAuthority('Manager')">
						<div class="btn-group">
							<button type="button" class="btn btn-info active">Management</button>
							<button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split" data-toggle="dropdown"></button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="airline">Airline</a> <a
									class="dropdown-item" href="airport">Airport</a> <a
									class="dropdown-item" href="role">Roles</a>
									<a class="dropdown-item " href="user">New Admin Account</a>
							</div>
						</div>
					</sec:authorize>
					<div class="btn-group">
						<button type="button" class="btn btn-info" >Account</button>
						<button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split" data-toggle="dropdown"></button>
						<div class="dropdown-menu">
							<c:choose>
								<c:when test="${currentUser == null}">
									<a class="dropdown-item " href="login">Sign In</a>
									<a class="dropdown-item " href="user">Create Account</a>
								</c:when>
							</c:choose>
							<sec:authorize access="hasAuthority('User') || hasAuthority('Administrator') || hasAuthority('Manager')">
								<a class="dropdown-item " href="reservation">Reservations</a>
								<a class="dropdown-item " href="login?logout">Logout</a>
							</sec:authorize>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="container">
			<frm:form action="saveAirport" method="POST" modelAttribute="airport">
				<div class="form-group">
					<label>Airport ID:</label>
					<frm:input path="airportId" class="form-control" placeholder="Airport ID" id="airportId" />
				</div>
				<div class="form-group">
					<label>Airport Name:</label>
					<frm:input path="airportName" class="form-control" placeholder="Airport Name" id="airportName" />
				</div>
				<div class="form-group">
					<label>Airport Code:</label>
					<frm:input path="airportCode" class="form-control" placeholder="Airport Code" id="airportCode" />
				</div>
				<input type="submit" value="Create Airport" class="btn btn-info" />
			</frm:form>
			<br />
			<h3>Existing Airports</h3>
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th align="center">Airport ID</th>
						<th align="center">Airport Name</th>
						<th align="center">Airport Code</th>
						<th align="center" colspan="2">Action</th>
					</tr>
				</thead>
				<c:forEach items="${airports}" var="a">
					<tr>
						<td align="center">${a.airportId}</td>
						<td align="center">${a.airportName}</td>
						<td align="center">${a.airportCode}</td>
						<td align="center"><a
							href="${pageContext.request.contextPath}/deleteAirport?airportId=${a.airportId}">Delete</a>
							&nbsp;&nbsp;<a
							href="${pageContext.request.contextPath}/updateAirport?airportId=${a.airportId}">Update</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
		<div class="jumbotron text-center" style="margin-bottom: 0">
			<h3>Blazing Airlines Inc.</h3>
			<p>13522 Canada Goose Ct</p>
		</div>
	</sec:authorize>
</body>
</html>