<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Employee"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<% List<Employee> employeeList = (List<Employee>) request.getAttribute("employees"); %>
<% String newCatName = (String) request.getParameter("firstName"); %>
<% String table = (String) request.getParameter("table"); %>
<% String keyword = (String) request.getParameter("keyword"); %>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>
<div id="content">

	<div id="content-bar">
		<a href="?page=addEmployee" class="btn btn-primary pull-right">Add new employee</a>
		<!-- <div class="pull-right" style="margin-right: 50px;">
			<div class="row">
				<form method="post" action="admin?page=searchEmployees">
					<div class="col-md-8" style="padding: 0px;">
						<input type="text" class="form-control" name="keyword" placeholder="Search employees" />
					</div>
					<div class="col-md-4" style="padding: 0px;">
						<input type="submit" class="form-control btn btn-primary" value="Search" />
					</div>
				</form>
			</div>
		</div> -->
		<h1>Employees</h1>
	</div>
	
	<% if (newCatName != null) { %>
		<div class="alert alert-success alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Success!</strong> The employee <i><%= newCatName %></i> has been added.
		</div>
	<% } %>
	
	<% if (table != null) { %>
		<div class="alert alert-success alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Success!</strong> The employee has been deleted.
		</div>
	<% } %>
	
	
	<div class="app-data">
	
		<% if (keyword != null) { %>
			<div>
				<p>Searching for employees with keyword: <strong><%= keyword %></strong>.</p>
				<p><strong><a href="admin?page=employees">Reset search results</a></strong></p>
			</div>
		<% } %>	
	
		<table class="table table-bordered table-hover" data-order='[[ 1, "asc" ]]' data-page-length='10'>
			<thead>
				<tr>
					<td>Picture</td>
					<td class="firstName">First Name</td>
					<td class="lastName">Last Name</td>
					<td>Title</td>
					<td>Description</td>
					<td>Author</td>
					<td class="lastUpdated">Last updated</td>
					<td>Edit</td>
					<td>Delete</td>
				</tr>
			</thead>
			<tbody>
				<% for (Employee employee : employeeList) { %>
					<tr>
						<td><div class="image-thumbnail" style="background-image: url('<%= Helpers.getBaseUrl(request) %>/uploads/employees/<%= employee.getProfilePicture() %>')"></div></td>
						<td><%= employee.getFirstName() %></td>
						<td><%= employee.getLastName() %></td>
						<td><%= employee.getTitle() %></td>
						<td><%= employee.getDescription() %></td>
						<td><%= employee.getAuthorName() %></td>
						<td><%= employee.getLastUpdated() %></td>
						<td><a class="btn btn-primary" href="?page=editEmployee&id=<%= employee.getId() %>" role="button">Edit</a></td>
						<td><a class="btn btn-danger" href="?page=deleteEmployee&id=<%= employee.getId() %>" role="button">Delete</a></td>
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
		      "targets": [0,5,6],
		      "orderable": false
		    }],
		} );
});
</script>

</body>
</html>