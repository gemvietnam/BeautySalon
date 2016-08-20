<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>

<% int totalProfit = (int) request.getAttribute("totalProfit"); %>
<% int totalCategories = (int) request.getAttribute("totalCategories"); %>
<% int totalEmployees = (int) request.getAttribute("totalEmployees"); %>
<% int totalServices = (int) request.getAttribute("totalServices"); %>
<% int totalBookings = (int) request.getAttribute("totalBookings"); %>

<div id="content">

	<div id="content-bar">
		<h1>Dashboard</h1>
	</div>
	
	<div class="app-data">
	
		<div class="row">
			<div class="col-md-3 text-center statistic-column">
				<h4>Total profit:</h4>
				<h2><%= totalProfit %> DKK</h2>
			</div>
			<div class="col-md-3 text-center statistic-column">
				<h4>Total services:</h4>
				<h2><%= totalServices %></h2>
			</div>
			<div class="col-md-3 text-center statistic-column">
				<h4>Total employees:</h4>
				<h2><%= totalEmployees %></h2>
			</div>
			<div class="col-md-3 text-center statistic-column">
				<h4>Total bookings:</h4>
				<h2><%= totalBookings %></h2>
			</div>
		</div>
<%-- 		<h1>Total profit: <%= totalProfit %> DKK</h1>
		<h1>Total categories: <%= totalCategories %> </h1>
		<h1>Total employees: <%= totalEmployees %> </h1>
		<h1>Total services: <%= totalServices %> </h1>
		<h1>Total bookings: <%= totalBookings %> </h1> --%>

	</div>
	
	
</div>


</body>
</html>