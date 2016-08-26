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
				<h4><strong><%= bookedService.getName() %></strong></h4>
				<p>Price: <%= bookedService.getPrice() %> DKK<br/>
				   Duration: <%= Helpers.TimeToHour(bookedService.getTime()) %> hours<br/></p><br/>
				
				<label>Your name:</label><br/>
				<p><%= booking.getFirstName() %> <%= booking.getLastName() %></p>
				<label>Email address:</label><br/>
				<p><%= booking.getEmail() %></p>
				<label>Phone number:</label><br/>
				<p><%= booking.getPhone() %></p>
			</div>
			<div class="col-md-6">
				<h1>Cancelation conditions</h1>
				<p>Cancelation of the visit can be done minimum 24 hours before the appointment. 
				To cancel the visit please call <strong>+45 10204082</strong>.</p>
			</div>
		</div>
	
		
	</div>
</section>

<%@include file="footer.jsp" %>

</body>
</html>