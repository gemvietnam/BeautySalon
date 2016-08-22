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

<% Integer totalProfit = (Integer) request.getAttribute("totalProfit"); %>
<% Integer totalCategories = (Integer) request.getAttribute("totalCategories"); %>
<% Integer totalEmployees = (Integer) request.getAttribute("totalEmployees"); %>
<% Integer totalServices = (Integer) request.getAttribute("totalServices"); %>
<% Integer totalBookings = (Integer) request.getAttribute("totalBookings"); %>

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