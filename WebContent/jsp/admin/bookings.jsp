<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Booking"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		<div class="pull-right" style="margin-right: 50px;">
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
		</div>
		<h1>Bookings</h1>
	</div>
	
	
	
	<div class="app-data">
	
		<% if (keyword != null) { %>
			<div>
				<p>Searching for employees with keyword: <strong><%= keyword %></strong>.</p>
				<p><strong><a href="admin?page=employees">Reset search results</a></strong></p>
			</div>
		<% } %>	
	
	
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<td>Status</td>
					<td>Date</td>
					<td>Time</td>
					<td>Employee</td>
					<td>Treatment</td>
					<td>Customer Name</td>
					<td>Email</td>
					<td>Phone</td>
					<td>Cancel</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bookings}" var="booking">
					<tr>
						<td>active</td>
						<td><c:out value="${booking.date}"/></td>
						<td><c:out value="${booking.hour}"/></td>
						<td><c:out value="${booking.employeeName}"/></td>
						<td><c:out value="${booking.serviceName}"/></td>
						<td><c:out value="${booking.firstName}"/> <c:out value="${booking.lastName}"/></td>
						<td><c:out value="${booking.email}"/></td>
						<td><c:out value="${booking.phone}"/></td>
						<td><a class="btn btn-danger" href="?page=delete&table=Employees&id=<c:out value="${booking.id}"/>" role="button">Cancel</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>
	
	
</div>


</body>
</html>