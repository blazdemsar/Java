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
<meta charset="ISO-8859-1">
<title>Search Flights</title>
<link href="css/jquery-ui.min.css" rel="stylesheet">
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<!-- <script src="js/popper.min.js"></script> -->
<script>
	$(document).ready(function() {

		$(document).on('click', '.view-passenger', function() {

			$("#passengerForm").trigger("reset");

			var flightId = $(this).attr("data-flightId");
			//alert(flightId);
			var flightPrice = $(this).attr("data-flightPrice");
			
			$("#flight_flightId").val(flightId);
			$("#flight_price").val(flightPrice);

			$("#passengerModal").modal("toggle");
		})

		$(document).on('click', '.view-reservation', function() {

			var firstName = $("#passenger_firstName").val();
			var middleName = $("#passenger_middleName").val();
			var lastName = $("#passenger_lastName").val();
			var gender = $("#passenger_gender").val();
			var email = $("#passenger_email").val();
			var phoneNumber = $("#passenger_phoneNumber").val();
			var dob = $("#passenger_dob").val();
			var passport = $("#passenger_passport").val();

			var passenger = {
				"firstName" : firstName,
				"middleName" : middleName,
				"lastName" : lastName,
				"gender" : gender,
				"email" : email,
				"phoneNumber" : phoneNumber,
				"dob" : dob,
				"passport" : passport
			}

			$.ajax({
				type : "POST",
				contentType : "application/json", //type of data being sent
				url : "savePassenger",
				dataType : "json", // type of data expected to receive
				data : JSON.stringify(passenger),
				cache : false,
				success : function(result) {

					$("#passengerModal").modal("toggle");

					var flightId = $("#flight_flightId").val();
					//alert("flightId:" + flightId);
					var flightPrice = $("#flight_price").val();

					$.each(result.currentPassenger, function (key, value) {
						//alert("key: " + key + ", value: " + value);
						$("#reservation_passengerId").val(value.passengerId);
						//alert("passengerId: " + value.passengerId);
						$("#reservation_flightId").val(flightId);
						$("#reservation_ticketFare").val(flightPrice);
					})
					
					
					$("#reservation_flight").val(flight);
					
					$("#reservationModal").modal("toggle");

				},
				error : function(e) {
					alert("Error while saving the Passenger!");
					console.log("ERROR: ", e);
				}
			})

			
		})
		
		$(document).on('click', '.finish-reservation', function() {

			var passengerId = $("#reservation_passengerId").val();
			var flightId = $("#reservation_flightId").val();
			var nrOfBags = $("#reservation_nrOfBags").val();
			var ticketFare = $("#reservation_ticketFare").val();
			
			//alert(passengerId);
			//alert(flightId);
			//alert(nrOfBags);
			//alert(ticketFare);

			var reservationDetails = {
				"passengerId" : passengerId,
				"flightId" : flightId,
				"nrOfBags" : nrOfBags,
				"ticketFare" : ticketFare
			};
			
			//alert(reservationDetails);

			$.ajax({
				type : "POST",
				contentType : "application/json", //type of data being sent
				url : "saveReservation",
				//dataType : "json", // type of data expected to receive
				data : JSON.stringify(reservationDetails),
				cache : false,
				success : function(result) {
					
					$("#reservationModal").modal("toggle");

				},
				error : function(e) {
					alert("Error while saving the Passenger!");
					console.log("ERROR: ", e);
				}
			})

			
		})

	})
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container border rounded" align="center" style="margin: auto; margin-top: 20px; margin-bottom: 30px; background-image: url('./images/TravelandHealthForm.jpg');">
		<div class="row" style="margin: auto; margin-top: 40px; margin-bottom: 50px">
			<div class="col-6" align="center">	
				<div class="container">
					<h1>Flight Tracker</h1>
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
						<a class="btn btn-info active" href="flight">Flight</a>
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
	
	<div class="container border rounded" style="margin-bottom: 30px; background-image: url('./images/louvre-search.jpg');">
		<div class="row">
			<div class="container rounded" style="margin: 20px; background-color: rgba(204, 230, 255, 0.5);">
				<h3 style="margin: 20px 10px 20px 10px; padding-left: 15px"><strong>Find best deals here</strong></h3>
				<frm:form action="getFlights" method="POST" modelAttribute="flight"
					name="searchFlightForm">
					<div class="row" style="margin: 20px 10px 20px 10px">
						<div class="col-3 form-group">
							<label><strong>Departure Airport</strong>:</label>
							<frm:select class="form-control" path="departureAirport">
								<frm:option selected="selected" value="">Select A Departure Airport</frm:option>
								<c:forEach items="${departureAirportSet}" var="deptAirport">
									<frm:option value="${deptAirport}">${deptAirport.airportCode} - ${deptAirport.airportName}</frm:option>
								</c:forEach>
							</frm:select>
						</div>
						<div class="col-3 form-group">
							<label><strong>Arrival Airport</strong>:</label>
							<frm:select class="form-control" path="arrivalAirport">
								<frm:option selected="selected" value="">Select An Arrival Airport</frm:option>
								<c:forEach items="${arrivalAirportSet}" var="arrivAirport">
									<frm:option value="${arrivAirport}">${arrivAirport.airportCode} - ${arrivAirport.airportName}</frm:option>
								</c:forEach>
							</frm:select>
						</div>
						<div class="col-3 form-group">
							<label><strong>Travel Date</strong>:</label>
							<frm:input type="datetime-local" path="departureDate" class="form-control" id="departureDate" name="departureDate"/>
						</div>
						<div class="col-2">
							<label>&nbsp</label><br />
							<input class="btn btn-info" type="submit" id="searchFlights"
								value="SEARCH" />
						</div>
					</div>
				</frm:form>
			</div>
		</div>
	</div>
	<div class="container rounded" style="margin: auto; margin-top: 30px; margin-bottom: 30px">
		<div class="row">
			<div class="col-6 border rounded overflow-auto"  style="margin: auto; padding-top: 20px; height: 400px; background-image: url('./images/plane.jpg');">
				<div class="container rounded" style="padding-top: 15px; padding-bottom: 15px; background-color: rgba(204, 230, 255, 0.5);">
					<c:choose>
						<c:when test="${flightsAvailable != null}">
							<h2 style="padding-left: 30px">Search Results:</h2>
							<c:forEach items="${flightsAvailable}" var="fl">
								<div class="container rounded" style="margin: auto;">
									<div class="row">
										<div class="col-2" align="center">
											<c:choose>
												<c:when test="${fl.airline.getAirlineCode() == 'ASA'}">
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/AlaskaA.png" />
														</a>
													</div>
												</c:when>
												<c:when test="${fl.airline.getAirlineCode() == 'AAY'}">
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/AllegianA.png" />
														</a>
													</div>
												</c:when>
												<c:when test="${fl.airline.getAirlineCode() == 'AAL'}">
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/AA.jpg" />
														</a>
													</div>
												</c:when>
												<c:when test="${fl.airline.getAirlineCode() == 'DAL'}">
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/DA.png" />
														</a>
													</div>
												</c:when>
												<c:when test="${fl.airline.getAirlineCode() == 'HAL'}">
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/HA.webp" />
														</a>
													</div>
												</c:when>
												<c:when test="${fl.airline.getAirlineCode() == 'SWA'}">
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/southwest-airlines-squarelogo-1411584571986.png" />
														</a>
													</div>
												</c:when>
												<c:when test="${fl.airline.getAirlineCode() == 'JBU'}">
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/JBA.png" />
														</a>
													</div>
												</c:when>
												<c:when test="${fl.airline.getAirlineCode() == 'ADR'}">
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/AdriaA.jpg" />
														</a>
													</div>
												</c:when>
												<c:otherwise>
													<div class="container rounded" align="center"
														style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
														<a href="#" style="display: block"> <img
															style="max-width: 100%; max-height: 100%; display: block;"
															src="./images/UA.jpg" />
														</a><br />
													</div>
												</c:otherwise>
											</c:choose>
											<br />
										</div>
										<div class="col-3" align="left">
											<strong>${fl.airline.airlineName}</strong><br />
											<br /> <strong>From</strong>:
											${fl.departureAirport.airportCode}<br /> <strong>To</strong>:
											${fl.arrivalAirport.airportCode}
										</div>
										<div class="col-5" align="left">
											<strong>Depart</strong>:
											${fl.departureDate.getMonthValue()}/${fl.departureDate.getDayOfMonth()}/${fl.departureDate.getYear()}
											${fl.departureDate.getHour()}:${fl.departureDate.getMinute()}<br />
											<strong>Arrive</strong>:
											${fl.arrivalDate.getMonthValue()}/${fl.arrivalDate.getDayOfMonth()}/${fl.arrivalDate.getYear()}
											${fl.arrivalDate.getHour()}:${fl.arrivalDate.getMinute()}<br />
											<br /> <strong>Price</strong>:$${fl.price}
										</div>
										<div class="2" align="center">
											<button class="btn btn-info view-passenger"
												data-flightId="${fl.flightId}"
												data-flightPrice="${fl.price}">Buy</button>
										</div>
									</div>
								</div>
								<hr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="container">
								<h2>Try our flight tracker above</h2>
								<p>
									to find personalized flights or you can search through<br />
									all available flights on your right!
								</p>
								<p>
									There is <strong>always</strong> something for<br /> someone!
								</p>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-6 border rounded overflow-auto"  style="margin: auto; padding-top: 20px; height: 400px; background-image: url('./images/solo-safestdestinations.jpg');">
				<div class="container rounded" style="padding-top: 15px; padding-bottom: 15px; background-color: rgba(204, 230, 255, 0.5);">
					<c:if test="${allFlights != null}">
						<h2 style="padding-left: 30px">All Flights Available:</h2>
						<c:forEach items="${allFlights}" var="af">
							<div class="container rounded" style="margin: auto;">
								<div class="row">
									<div class="col-2" align="center">
										<c:choose>
											<c:when test="${af.airline.getAirlineCode() == 'ASA'}">
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/AlaskaA.png" />
													</a>
												</div>
											</c:when>
											<c:when test="${af.airline.getAirlineCode() == 'AAY'}">
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/AllegianA.png" />
													</a>
												</div>
											</c:when>
											<c:when test="${af.airline.getAirlineCode() == 'AAL'}">
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/AA.jpg" />
													</a>
												</div>
											</c:when>
											<c:when test="${af.airline.getAirlineCode() == 'DAL'}">
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/DA.png" />
													</a>
												</div>
											</c:when>
											<c:when test="${af.airline.getAirlineCode() == 'HAL'}">
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/HA.webp" />
													</a>
												</div>
											</c:when>
											<c:when test="${af.airline.getAirlineCode() == 'SWA'}">
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/southwest-airlines-squarelogo-1411584571986.png" />
													</a>
												</div>
											</c:when>
											<c:when test="${af.airline.getAirlineCode() == 'JBU'}">
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/JBA.png" />
													</a>
												</div>
											</c:when>
											<c:when test="${af.airline.getAirlineCode() == 'ADR'}">
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/AdriaA.jpg" />
													</a>
												</div>
											</c:when>
											<c:otherwise>
												<div class="container rounded" align="center"
													style="vertical-align: middle; height: 80px; width: 80px; background-size: auto;">
													<a href="#" style="display: block"> <img
														style="max-width: 100%; max-height: 100%; display: block;"
														src="./images/UA.jpg" />
													</a>
												</div>
											</c:otherwise>
										</c:choose>
										<br />
									</div>
									<div class="col-3" align="left">
										<strong>${af.airline.airlineName}</strong><br />
										<br /> <strong>From</strong>:
										${af.departureAirport.airportCode}<br /> <strong>To</strong>:
										${af.arrivalAirport.airportCode}
									</div>
									<div class="col-5" align="left">
										<strong>Depart</strong>:
										${af.departureDate.getMonthValue()}/${af.departureDate.getDayOfMonth()}/${af.departureDate.getYear()}
										${af.departureDate.getHour()}:${af.departureDate.getMinute()}<br />
										<strong>Arrive</strong>:
										${af.arrivalDate.getMonthValue()}/${af.arrivalDate.getDayOfMonth()}/${af.arrivalDate.getYear()}
										${af.arrivalDate.getHour()}:${af.arrivalDate.getMinute()}<br />
										<br /> <strong>Price</strong>:$${af.price}
									</div>
									<div class="2" align="center">
										<button class="btn btn-info view-passenger"
											data-flightId="${af.flightId}" data-flightPrice="${af.price}">Buy</button>
									</div>
								</div>
							</div>
							<hr>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	
	<br />
	<sec:authorize
		access="hasAuthority('Administrator') || hasAuthority('Manager')">
		<div class="container">
			<h1>Flight Management</h1>
			<frm:form action="saveFlight" method="POST" modelAttribute="flight"
				name="flightForm">
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
					<label>Flight Status:</label>
					<frm:select class="form-control" path="flightStatus">
						<frm:option selected="selected" value="">Select Flight Status</frm:option>
						<c:forEach items="${flightStatusValues}" var="fs">
							<frm:option value="${fs}">${fs}</frm:option>
						</c:forEach>
					</frm:select>
				</div>
				<div class="form-group">
					<label>Nr. Of Seats:</label>
					<frm:input type="number" path="noSeats" placeholder="Nr. Of Seats"
						class="form-control" id="noSeats" />
				</div>
				<div class="form-group">
					<label>Price:</label>
					<frm:input type="number" path="price" placeholder="Flight Price"
						class="form-control" id="price" />
				</div>
				<input type="submit" value="Create" class="btn btn-info" />
			</frm:form>
			<br />
			<h3>Existing Flights</h3>
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th align="center">Flight ID</th>
						<th align="center">Flight Number Name</th>
						<th align="center">Airline</th>
						<th align="center">Dep. Airport</th>
						<th align="center">Arriv. Airport</th>
						<th align="center">Dep. Time</th>
						<th align="center">Arriv. Time</th>
						<th align="center">Nr. Of Reservations</th>
						<th align="center">Status</th>
						<th align="center">Nr. Seats</th>
						<th align="center">Price</th>
						<th align="center" colspan="2">Action</th>
					</tr>
				</thead>
				<c:forEach items="${flights}" var="f">
					<tr>
						<td align="center">${f.flightId}</td>
						<td align="center">${f.flightNumber}</td>
						<td align="center">${f.airline.airlineName}</td>
						<td align="center">${f.departureAirport.airportCode}</td>
						<td align="center">${f.arrivalAirport.airportCode}</td>
						<td align="center">${f.departureDate}</td>
						<td align="center">${f.arrivalDate}</td>
						<td align="center">${f.reservations.size()}</td>
						<td align="center">${f.flightStatus}</td>
						<td align="center">${f.noSeats}</td>
						<td align="center">${f.price}</td>
						<td align="center"><a
							href="${pageContext.request.contextPath}/deleteFlight?flightId=${f.flightId}">Delete</a>
							&nbsp;&nbsp;<a
							href="${pageContext.request.contextPath}/updateFlight?flightId=${f.flightId}">Update</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</sec:authorize>

	<div class="modal" id="passengerModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Passenger Information</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="passenger_modalBody">
					<div class="container">
						<div>
							<input type="hidden" id="flight_flightId" />
						</div>
						<div>
							<input type="hidden" id="flight_price" />
						</div>
						<div class="form-group">
							<label>First Name:</label> <input class="form-control"
								type="text" id="passenger_firstName" />
						</div>
						<div class="form-group">
							<label>Middle Name:</label> <input class="form-control"
								type="text" id="passenger_middleName" />
						</div>
						<div class="form-group">
							<label>Last Name:</label> <input class="form-control" type="text"
								id="passenger_lastName" />
						</div>
						<div class="form-group">
							<label>Gender:</label> <select class="form-control"
								id="passenger_gender">
								<option selected="selected" value="">Select your gender</option>
								<c:forEach items="${genderValues}" var="gd">
									<option value="${gd}">${gd}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>Email:</label> <input class="form-control" type="text"
								id="passenger_email" />
						</div>
						<div class="form-group">
							<label>Phone Number:</label> <input class="form-control"
								type="text" id="passenger_phoneNumber" />
						</div>
						<div class="form-group">
							<label>Date of Birth:</label> <input class="form-control"
								type="date" id="passenger_dob" />
						</div>
						<div class="form-group">
							<label>What document will you be traveling with?</label> <select
								class="form-control" id="passenger_passport">
								<option selected="selected" value="">Select your form
									of ID</option>
								<c:forEach items="${identificationTypeValues}" var="it">
									<option value="${it}">${it}</option>
								</c:forEach>
							</select>
						</div>
						<div style='margin-top: 20px'>
							<button class='btn btn-primary view-reservation'>Next</button>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						id="closeReviewModal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="reservationModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Reservation Information</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="reservation_modalBody">
					<div class="container">
							<div class="form-group">
								<input class="form-control" type="hidden" id="reservation_passengerId" />
							</div>
							<div class="form-group">
								<input class="form-control" type="hidden" id="reservation_flightId"/>
							</div>
							<div class="form-group">
								<label>Number of Bags:</label>
								<input class="form-control" type="number"
									id="reservation_nrOfBags" />
							</div>
							<div class="form-group">
								<label>Price:</label>
								<input class="form-control" type="number" readonly id="reservation_ticketFare" />
							</div>
							<button class="btn btn-info finish-reservation" >Book Now</button>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						id="closeReviewModal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h3>Blazing Airlines Inc.</h3>
		<p>13522 Canada Goose Ct</p>
	</div>
</body>
</html>