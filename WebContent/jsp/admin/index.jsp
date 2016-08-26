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
		
		<!-- <div class="row">
			<div class="col-md-6">
				<h3>Profit by month</h3>
				<canvas id="myChart" width="400" height="400"></canvas>
			</div>
		</div> -->

	</div>
	
	
</div>

<script>
var ctx = document.getElementById("myChart");
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["March", "April", "May", "June", "July", "August"],
        datasets: [{
            label: 'Profit',
            data: [1200, 1900, 3000, 5000, 7520, 8535],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
</script>
</body>
</html>