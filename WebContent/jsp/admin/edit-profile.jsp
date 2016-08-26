<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Category"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit profile - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<% User userData = (User) request.getAttribute("userData"); %>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>

<div id="content">
	
	<div id="content-bar">
		<h1>Edit your profile</h1>
	</div>
	
	<div class="app-data">
	
		<form method="post" action="admin?page=editProfile">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>First name</label>
						<input type="text" name="firstName" value="<%= Helpers.DisplayIfNotNull(userData.getFirstName()) %>" class="form-control" placeholder="First name" minlength="2" required />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Last name</label>
						<input type="text" name="lastName" value="<%= Helpers.DisplayIfNotNull(userData.getLastName()) %>" class="form-control" placeholder="Last name" minlength="2" required />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Email</label>
						<input type="email" name="email" value="<%= Helpers.DisplayIfNotNull(userData.getEmail()) %>" class="form-control" placeholder="Email" required />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Change password</label>
						<input type="password" name="password" class="form-control" placeholder="Password" minlength="8" />
					</div>
				</div>
			</div>			
			<div class="row">
				<div class="col-md-12">
					<input type="hidden" name="id" value="<%= userData.getId() %>" />
					<input type="hidden" name="update" value="true" />
					<input type="submit" value="Update profile" class="btn btn-primary"/>
				</div>
			</div>
			
		</form>
	
	</div>
	
	
</div>

<script>
	$("form").validate();
</script>

</body>
</html>