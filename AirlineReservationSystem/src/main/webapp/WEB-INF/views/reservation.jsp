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
<title>Your Reservations</title>
</head>
<body>
	<sec:authorize access="hasAuthority('User')">
		<div class="container border rounded" align="center" style="margin: auto; margin-top: 20px; margin-bottom: 20px; background-image: url('./images/TravelandHealthForm.jpg');">
			<div class="row"
				style="margin: auto; margin-top: 40px; margin-bottom: 50px">
				<div class="col-6" align="center">
					<div class="container">
						<h1>Account - Reservations</h1>
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
							<button type="button" class="btn btn-info ">Management</button>
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
						<button type="button" class="btn btn-info active" >Account</button>
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
		
		<div class="container border rounded" style="margin-bottom: 30px; padding-top: 15px">
			<h1 style="padding-left: 25px">Your Reservations</h1>
			<c:forEach items="${yourReservations}" var="yr">
				<div class="container rounded" style="margin: auto; margin-bottom: 30px; padding: 20px 25px;">
					<h5>Passenger Information:</h5>
					<strong>First Name</strong>: ${yr.passenger.lastName}<br/>
					<strong>Middle Name</strong>: ${yr.passenger.middleName}<br/>
					<strong>Last Name</strong>: ${yr.passenger.firstName}<br/>
					<strong>Date of Birth</strong>: ${yr.passenger.dob}<br/>
					<strong>Form of ID</strong>: ${yr.passenger.passport}<br/><br/>
					
					<c:choose>
						<c:when test="${yr.checkedIn == false}">
							<a href="/updateReservation?ticketId=${yr.ticketId}" class="btn btn-info">Check In</a><br/><br/>
						</c:when>
						<c:otherwise>
							<strong>Checked In</strong>: YES<br/><br/>
						</c:otherwise>
					</c:choose>
					
					<strong>From</strong>:<br/>${yr.flight.departureAirport.airportCode} - ${yr.flight.departureAirport.airportName}<br/> Departure : ${yr.flight.departureDate.getMonthValue()}/${yr.flight.departureDate.getDayOfMonth()}/${yr.flight.departureDate.getYear()} ${yr.flight.departureDate.getHour()}:${yr.flight.departureDate.getMinute()}<br />
					<strong>To</strong>:<br/>${yr.flight.arrivalAirport.airportCode} - ${yr.flight.arrivalAirport.airportName}<br/> Arrival : ${yr.flight.arrivalDate.getMonthValue()}/${yr.flight.arrivalDate.getDayOfMonth()}/${yr.flight.arrivalDate.getYear()} ${yr.flight.arrivalDate.getHour()}:${yr.flight.arrivalDate.getMinute()}<br /><br/>
					<strong>Service Provider</strong>:<br/>${yr.flight.airline.airlineName}<br /><br/>
					<p><strong>Number of Bags</strong>:<br/>${yr.nrOfBags}</p>
					<h5><strong>Total Price:</strong> USD$${yr.ticketFare}</h5>
					<a href="/cancelReservation?ticketId=${yr.ticketId}" class="btn btn-info">Cancel</a>
				</div>
				<hr>
			</c:forEach>
		</div>
		<sec:authorize access="hasAuthority('Administrator') || hasAuthority('Manager')">
		<div class="container border rounded" style="margin-bottom: 30px; padding-top: 15px">
			<h1 style="padding-left: 25px">All Reservations</h1>
			<c:forEach items="${allReservations}" var="ar">
				<div class="container rounded" style="margin: auto; margin-bottom: 30px; padding: 20px 25px;">
					<h5>Passenger Information:</h5>
					<strong>Info</strong>: ${ar.passenger.lastName}, ${ar.passenger.middleName}, ${ar.passenger.firstName}, ${ar.passenger.dob}, ${ar.passenger.passport}<br/>
					<strong>From</strong>: ${ar.flight.departureAirport.airportCode} - ${ar.flight.departureAirport.airportName} / Departure : ${ar.flight.departureDate.getMonthValue()}/${ar.flight.departureDate.getDayOfMonth()}/${ar.flight.departureDate.getYear()} ${ar.flight.departureDate.getHour()}:${ar.flight.departureDate.getMinute()}<br />
					<strong>To</strong>: ${ar.flight.arrivalAirport.airportCode} - ${ar.flight.arrivalAirport.airportName} / Arrival : ${ar.flight.arrivalDate.getMonthValue()}/${ar.flight.arrivalDate.getDayOfMonth()}/${ar.flight.arrivalDate.getYear()} ${ar.flight.arrivalDate.getHour()}:${ar.flight.arrivalDate.getMinute()}<br />
					<strong>Service Provider</strong>: ${ar.flight.airline.airlineName}<br />
					<strong>Number of Bags</strong>: ${ar.nrOfBags}<br/>
					<strong>Total Price</strong>: USD$${ar.ticketFare}<br/>
					<a href="/cancelReservation?ticketId=${ar.ticketId}" class="btn btn-info">Cancel</a>
					<c:choose>
						<c:when test="${ar.checkedIn == false}">
							<a href="/updateReservation?ticketId=${ar.ticketId}" class="btn btn-info">Check In</a><br/><br/>
						</c:when>
						<c:otherwise>
							<strong>Checked In</strong>: YES<br/><br/>
						</c:otherwise>
					</c:choose>
				</div>
				<hr>
			</c:forEach>
		</div>
		</sec:authorize>
		
		<div class="jumbotron text-center" style="margin-bottom: 0">
			<h3>Blazing Airlines Inc.</h3>
			<p>13522 Canada Goose Ct</p>
		</div>
	</sec:authorize>
	
</body>
</html>