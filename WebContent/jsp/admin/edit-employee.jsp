<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Category"%> 
<%@page import="models.Service"%> 
<%@page import="models.Employee"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Employees - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>

<% List<Service> services = (List<Service>) request.getAttribute("services"); %>
<% Employee employee = (Employee) request.getAttribute("employee"); %>
<% List<Integer> serviceIds = (List<Integer>) request.getAttribute("serviceIds"); %>

<div id="content">

	<div id="content-bar">
		<h1>Edit employee</h1>
	</div>
	
	<div class="app-data">
		
		<form id="form" method="post" action="admin?page=employees">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>First name</label>
						<input type="text" name="firstName" class="form-control" placeholder="First name" value="<%= employee.getFirstName() %>" minlength=2" required />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Last name</label>
						<input type="text" name="lastName" class="form-control" placeholder="Last name" value="<%= employee.getLastName() %>" minlength="2" required />
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Email</label>
						<input type="text" name="email" class="form-control" placeholder="Email" value="<%= employee.getEmail() %>" required />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Title</label>
						<input type="text" name="title" class="form-control" placeholder="Title" value="<%= employee.getTitle() %>" minlength="2" required />
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Description</label>
						<input type="text" name="description" class="form-control" placeholder="Description" value="<%= employee.getDescription() %>" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Profile picture path</label>
						<input type="text" name="profilePicture" class="form-control" placeholder="Profile picture path" value="<%= employee.getProfilePicture() %>"/>
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group" style="margin-bottom: 0px;">
								<label>Treatments</label>
							</div>
						</div>
						
						<div class="services columns-3">
						
						<% for (Service service : services) { %>
							<% String isChecked = ""; %>
							<% for (int serviceId : serviceIds) { %>
								<% if (serviceId == service.getId()) { 
									isChecked = "checked";
								} %>
							<% } %> 
							
								<div class="checkbox">
								  <label>
								    <input type="checkbox" name="service" value="<%= service.getId() %>" <%= isChecked %>> <%= service.getName() %>
								  </label>
								</div>

							<% } %>
						
						</div>
				
					</div>
				</div>	
			</div>		
			
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-12">
					<div class="form-group">
						<input type="hidden" name="id" value="<%= employee.getId() %>" />
						<input type="hidden" name="type" value="update" />
						<input type="submit" value="Update employee" class="btn btn-primary" />
					</div>
				</div>
			</div>

		</form>		
		

	</div>
	
	
</div>

<script>
	$("#form").validate();
</script>

</body>
</html>