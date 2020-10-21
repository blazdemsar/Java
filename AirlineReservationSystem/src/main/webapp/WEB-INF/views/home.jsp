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
<!-- <script src="js/popper.min.js"></script> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
	
	.carousel-inner img {
		width: 100%;
		height: 100%;
		-webkit-filter: blur(5px);
	}
	
	.carousel-caption {
		position: absolute;
		right: 20%;
		top:40%;
		left: 20%;
		z-index: 10;
		color: #fff;
		text-align: center;
	}
</style>
<meta charset="ISO-8859-1">
<title>Welcome To Blazing Airlines!</title>
</head>
<body>
	<div class="container border rounded" align="center" style="margin: auto; margin-top: 20px; width: 1110px; margin-bottom: 20px; background-image: url('./images/TravelandHealthForm.jpg');">
		<div class="row" style="margin: auto; margin-top: 40px; margin-bottom: 50px">
			<div class="col-6" align="center">	
				<div class="container">
					<h1>Welcome to Blazing Airlines!</h1>
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
						<a class="btn btn-info active" href="home">Home</a>
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
	<div class="container rounded" align="center" style="margin: auto; margin-top: 20px; margin-bottom: 20px; width: 1140;">
		<div id="trip-carousel" style="margin: auto; margin-bottom: 40px" class="carousel slide carousel-fade rounded" data-ride="carousel">
			<ul class="carousel-indicators">
    			<li data-target="#trip-carousel" data-slide-to="0" class="active"></li>
    			<li data-target="#trip-carousel" data-slide-to="1"></li>
    			<li data-target="#trip-carousel" data-slide-to="2"></li>
    			<li data-target="#trip-carousel" data-slide-to="3"></li>
    			<li data-target="#trip-carousel" data-slide-to="4"></li>
  			</ul>
  			<div class="carousel-inner">
    			<div class="carousel-item active">
      				<img src="./images/annapolis-maryland-travel-list.jpg" alt="Annapolis - Maryland" >
      				<div class="carousel-caption">
    					<h1>Find Best Deals For Your Next Adventure!</h1>
  					</div>
    			</div>
    			<div class="carousel-item">
      				<img src="./images/how-to-get-to-predjama-castle-from-ljubljana-slovenia-travel.jpg" alt="Predjama Castle - Slovenia" >
      				<div class="carousel-caption">
    					<h1>Create an account</h1>
  					</div>
   				</div>
    			<div class="carousel-item">
      				<img src="./images/activity1320x742-1.jpg" alt="Random Traveler Exploring" >
      				<div class="carousel-caption">
    					<h1>Enjoy our exclusive benefits</h1>
  					</div>
    			</div>
    			<div class="carousel-item">
      				<img src="./images/solo-travel-myth-bust_1.jpg" alt="Solo Traveler" >
      				<div class="carousel-caption">
    					<h1>Get discounts on flights for being a loyal customer</h1>
  					</div>
    			</div>
    			<div class="carousel-item">
      				<img src="./images/st-augustine-florida-travel.jpg" alt="St. Augustine - Florida" >
      				<div class="carousel-caption">
    					<h1>Subscribe to our newsletter and be the first one to know about special offers</h1>
  					</div>
    			</div>
  			</div>
  			<a class="carousel-control-prev" href="#trip-carousel" data-slide="prev">
    			<span class="carousel-control-prev-icon"></span>
  			</a>
  			<a class="carousel-control-next" href="#trip-carousel" data-slide="next">
    			<span class="carousel-control-next-icon"></span>
  			</a>
		</div>
	</div>
	<!-- <div class="container rounded" style="margin: auto; margin-top: 30px; margin-bottom: 30px">
		<div class="row">
			<div class="col-6 border rounded overflow-auto"  style="margin: auto; padding-top: 20px; height: 400px;">
				<div class="container rounded" style="padding-top: 15px; padding-bottom: 15px; background-color: rgba(204, 230, 255, 0.5);">
					
				</div>
			</div>
			<div class="col-6 border rounded overflow-auto"  style="margin: auto; padding-top: 20px; height: 400px;">
				<div class="container rounded" style="padding-top: 15px; padding-bottom: 15px; background-color: rgba(204, 230, 255, 0.5);">
					
				</div>
			</div>
		</div>
	</div> -->
	<div class="jumbotron text-center" style="margin-bottom:0">
  		<h3>Blazing Airlines Inc.</h3>
		<p>13522 Canada Goose Ct</p>
	</div>
</body>
</html>