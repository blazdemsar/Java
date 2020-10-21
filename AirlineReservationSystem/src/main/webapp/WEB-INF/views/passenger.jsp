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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Passenger</title>
</head>
<body>
		<div class="container p-3 my-3 bg-white " align="center">
		<h1>Create A New Account</h1>
		<br />
		<ul class="nav nav-tabs nav-justified">
			<li class="nav-item"><a class="nav-link " href="home">Home</a></li>
			<li class="nav-item"><a class="nav-link " href="flight">Flight</a></li>
			<sec:authorize access="hasAuthority('Administrator') || hasAuthority('Manager')">
				<li class="nav-item"><a class="nav-link " href="airline">Airline</a></li>
				<li class="nav-item"><a class="nav-link " href="airport">Airport</a></li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('User') || hasAuthority('Administrator') || hasAuthority('Manager')">
				<li class="nav-item"><a class="nav-link active" href="passenger">Passenger</a></li>
				<li class="nav-item"><a class="nav-link " href="reservation">Reservation</a></li>
			</sec:authorize>
			<li class="nav-item"><a class="nav-link " href="login">Login</a></li>
			<li class="nav-item"><a class="nav-link " href="user">Customer</a></li>
		</ul>
		</div>
		<div class="container">
			<h1>Passenger Information</h1>
			<frm:form action="savePassenger" method="POST" modelAttribute="passenger">
				<div class="form-group">
					<label>Flight ID:</label>
					<frm:input path="flightId" class="form-control"
						placeholder="Flight ID" id="flightId" />
				</div>
				<div class="form-group">
					<label>Flight Number:</label>
					<frm:input path="flightNumber" class="form-control"
						placeholder="Flight Number" id="flightNumber" />
				</div>
				<div class="form-group">
					<label>Airline:</label>
					<frm:select class="form-control" path="airline">
						<frm:option selected="selected" value="">Select An Airline</frm:option>
						<c:forEach items="${airlineSet}" var="airL">
							<frm:option value="${airL}">${airL.airlineName}</frm:option>
						</c:forEach>
					</frm:select>
				</div>
				<div class="form-group">
					<label>Departure Airport:</label>
					<frm:select class="form-control" path="departureAirport">
						<frm:option selected="selected" value="">Select A Departure Airport</frm:option>
						<c:forEach items="${departureAirportSet}" var="deptAirport">
							<frm:option value="${deptAirport}">${deptAirport.airportCode} - ${deptAirport.airportName}</frm:option>
						</c:forEach>
					</frm:select>
				</div>
				<div class="form-group">
					<label>Arrival Airport:</label>
					<frm:select class="form-control" path="arrivalAirport">
						<frm:option selected="selected" value="">Select An Arrival Airport</frm:option>
						<c:forEach items="${arrivalAirportSet}" var="arrivAirport">
							<frm:option value="${arrivAirport}">${arrivAirport.airportCode} - ${arrivAirport.airportName}</frm:option>
						</c:forEach>
					</frm:select>
				</div>
				<div class="form-group">
					<label>Departure Date:</label>
					<frm:input type="datetime-local" path="departureDate"
						class="form-control" id="departureDate" />
				</div>
				<div class="form-group">
					<label>Arrival Date:</label>
					<frm:input type="datetime-local" path="arrivalDate"
						class="form-control" id="arrivalDate" />
				</div>
				<div class="form-group">
					<label>Nr. Of Seats:</label>
					<frm:input type="number" path="noSeats" placeholder="Nr. Of Seats"
						class="form-control" id="noSeats" />
				</div>
				<input type="submit" value="Create" class="btn btn-info" />
			</frm:form>
			<br />
			<h3>Existing Airlines</h3>
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th align="center">Airline ID</th>
						<th align="center">Airline Name</th>
						<th align="center">Airline Code</th>
						<th align="center" colspan="2">Action</th>
					</tr>
				</thead>
				<c:forEach items="${airlines}" var="a">
					<tr>
						<td align="center">${a.airlineId}</td>
						<td align="center">${a.airlineName}</td>
						<td align="center">${a.airlineCode}</td>
						<td align="center"><a href="${pageContext.request.contextPath}/deleteAirline?airlineId=${a.airlineId}">Delete</a>
									&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/updateAirline?airlineId=${a.airlineId}">Update</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="jumbotron text-center" style="margin-bottom: 0">
			<h3>Blazing Airlines Inc.</h3>
			<p>13522 Canada Goose Ct</p>
		</div>
</body>
</html>