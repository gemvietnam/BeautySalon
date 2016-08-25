<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="java.sql.Time"%>
<%@page import="models.Booking"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookings - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<% List<Booking> bookings = (List<Booking>) request.getAttribute("bookings"); %>
<% String keyword = (String) request.getParameter("keyword"); %>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>
<div id="content">

	<div id="content-bar">
		<!-- <div class="pull-right" style="margin-right: 50px;">
			<div class="row">
				<form method="post" action="admin?page=searchEmployees">
					<div class="col-md-8" style="padding: 0px;">
						<input type="text" class="form-control" name="keyword" placeholder="Search bookings" />
					</div>
					<div class="col-md-4" style="padding: 0px;">
						<input type="submit" class="form-control btn btn-primary" value="Search" />
					</div>
				</form>
			</div>
		</div> -->
		<h1>Bookings</h1>
	</div>
	
	
	
	<div class="app-data">
	
		<% if (keyword != null) { %>
			<div>
				<p>Searching for employees with keyword: <strong><%= keyword %></strong>.</p>
				<p><strong><a href="admin?page=employees">Reset search results</a></strong></p>
			</div>
		<% } %>	
	
	
		<table class="table table-bordered table-hover" data-order='[[ 1, "desc" ]]' data-page-length='25'>
			<thead>
				<tr>
					<td>Status</td>
					<td class="created">Created</td>
					<td class="date">Date</td>
					<td>Time</td>
					<td class="employee">Employee</td>
					<td>Treatment</td>
					<td>Customer Name</td>
					<td>Email</td>
					<td>Phone</td>
					<td>Cancel</td>
				</tr>
			</thead>
			
			<tbody>
				<% for (Booking booking : bookings) { %>
					<% String bookingStatus = (booking.getIsActive() == 1) ? "active" : "cancelled"; %>
					<% String bookingLabel = (booking.getIsActive() == 1) ? "success" : "danger"; %>
					<% String disableButton = (booking.getIsActive() == 1) ? "" : "disabled"; %>
					<tr>
						<td><span class="label label-<%= bookingLabel %>"><%= bookingStatus %></span></td>
						<td class="created"><%= Helpers.TimestampToString(booking.getCreated()) %></td>
						<td class="date"><%= booking.getDate() %></td>
						<td><%= Helpers.TimeToHour((Time)booking.getHour()) %></td>
						<td class="employee"><%= booking.getEmployeeName() %></td>
						<td><%= booking.getServiceName() %></td>
						<td class="customerName"><%= booking.getFirstName() %> <%= booking.getLastName() %></td>
						<td><%= booking.getEmail() %></td>
						<td><%= booking.getPhone() %></td>
						<td><a class="btn btn-danger btn-sm <%= disableButton %>" href="?page=cancelBooking&id=<%= booking.getId() %>" role="button">Cancel</a></td>
					</tr>
				<% } %>
			</tbody>
			
		</table>
	
	</div>
	
	
</div>

<script>
$(document).ready( function () {
	$('table').dataTable({
		  "columnDefs": [{
		      "targets": 8,
		      "orderable": false
		    }],
/* 	       dom: 'Bfrtip',
	       buttons: [
	          'pdf',
	          'print'
		    ] */
		} );
});
</script>

</body>
</html>