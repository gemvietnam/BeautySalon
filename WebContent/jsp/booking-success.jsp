<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Booking"%> 
<%@page import="models.Service"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>
<%@include file="styles.jsp" %>

  
</head>
<body>

<%@include file="navigation.jsp" %>

<% String firstName = request.getParameter("firstName"); %>
<% Booking booking = (Booking) request.getAttribute("addedBooking"); %>
<% Service bookedService = (Service) request.getAttribute("bookedService"); %>

<section id="treatments">
	<div class="wrapper">
		
		<div class="row">
			<div class="col-md-6">
				<h1>You have booked a visit</h1>
				<h3>Selected treatment:</h3>
				<h3><%= bookedService.getName() %></h3>
				<p>Price: <%= bookedService.getPrice() %> DKK</p>
				<p>Duration: <%= Helpers.TimeToHour(bookedService.getTime()) %> hours</p>
				
				<h3>Your personal data:</h3>
				<label>Name:</label><br/>
				<%= booking.getFirstName() %> <%= booking.getLastName() %><br/>
				<label>Email address:</label><br/>
				<%= booking.getEmail() %><br/>
				<label>Phone number:</label><br/>
				<%= booking.getPhone() %><br/>
			</div>
			<div class="col-md-6">
				<h2>Cancelation conditions</h2>
				<h3>Cancelation of the visit can be done minimum 24 hours before the appointment. 
				To cancel the visit please call <strong>+45 10204082</strong>.</h3>
			</div>
		</div>
		
		<h3>Cancelation of the visit can be done minimum 24 hours before the appointment. To cancel the visit please call +45 10204082.</h3>
		
		
	</div>
</section>

<%@include file="calltoaction.jsp" %>

<%@include file="footer.jsp" %>

</body>
</html>