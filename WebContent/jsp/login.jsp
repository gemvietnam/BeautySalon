<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login to the dashboard - Prettier CMS</title>
<%@include file="styles-dashboard.jsp" %>

<% Boolean isError = (Boolean) request.getAttribute("isError"); %>
</head>

<body id="login">

<div class="row">
	<div class="col-md-2 col-md-offset-5" style="margin-top: 200px;">
		<div>
			<form action="login" method="post">
			  <div class="form-group">
			    <label>Email address</label>
			    <input type="email" class="form-control" name="email" placeholder="Email">
			  </div>
			  <div class="form-group">
			    <label>Password</label>
			    <input type="password" class="form-control" name="password" placeholder="Password">
			  </div>
			  <button type="submit" class="btn btn-default form-control">Login</button>
			</form>
		</div>
		
		<% if (isError == true) { %>
			<div class="alert alert-danger" role="alert">
				<p>You have entered wrong email and/or password. Please try again.</p>
			</div>
		<% } %>

	</div>
</div>

</body>
</html>