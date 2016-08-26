<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="java.sql.Time"%>
<%@page import="models.Service"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Treatments - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<% List<Service> services = (List<Service>) request.getAttribute("services"); %>
<% String newCatName = (String) request.getParameter("name"); %>
<% String keyword = (String) request.getParameter("keyword"); %>
<% String table = (String) request.getParameter("table"); %>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>
<div id="content">

	<div id="content-bar">
		<a href="?page=addTreatment" class="btn btn-primary pull-right">Add new treatment</a>
		<!-- <div class="pull-right" style="margin-right: 50px;">
			<div class="row">
				<form method="post" action="admin?page=searchTreatments">
					<div class="col-md-8" style="padding: 0px;">
						<input type="text" class="form-control" name="keyword" placeholder="Search treatments" />
					</div>
					<div class="col-md-4" style="padding: 0px;">
						<input type="submit" class="form-control btn btn-primary" value="Search" />
					</div>
				</form>
			</div>
		</div> -->
		<h1>Treatments</h1>
	</div>
	
	<% if (newCatName != null) { %>
		<div class="alert alert-success alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Success!</strong> The treatment <i><%= newCatName %></i> has been added.
		</div>
	<% } %>
	
	<% if (table != null) { %>
		<div class="alert alert-success alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Success!</strong> The treatment has been deleted.
		</div>
	<% } %>	
	
	<div class="app-data">
	
		<% if (keyword != null) { %>
			<div>
				<p>Searching for treatments with keyword: <strong><%= keyword %></strong>.</p>
				<p><strong><a href="admin?page=treatments">Reset search results</a></strong></p>
			</div>
		<% } %>
	
		<table class="table table-bordered table-hover" data-order='[[ 0, "asc" ]]' data-page-length='25'>
			<thead>
				<tr>
					<td>Category</td>
					<td>Name</td>
					<td>Description</td>
					<td class="price">Price</td>
					<td>Duration</td>
					<td class="lastUpdated">Last updated</td>
					<td class="author-name">Author</td>
					<td>Edit</td>
					<td>Delete</td>
				</tr>
			</thead>
			<tbody>
				<% for (Service service : services) { %>
					<tr>
						<td><%= service.getCategoryName() %></td>
						<td><%= service.getName() %></td>
						<td><%= service.getDescription() %></td>
						<td class="price"><%= service.getPrice() %> DKK</td>
						<td><%= Helpers.TimeToHour((Time)service.getTime()) %> hours</td>
						<td><%= Helpers.TimestampToString(service.getLastUpdated()) %></td>
						<td><%= service.getAuthorName() %></td>
						<td><a class="btn btn-primary" href="?page=editTreatment&id=<%= service.getId() %>" role="button">Edit</a></td>
						<td><a class="btn btn-danger" href="?page=deleteService&id=<%= service.getId() %>" role="button">Delete</a></td>
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
		      "targets": [6, 5],
		      "orderable": false
		    }],
		} );
});
</script>
</body>
</html>