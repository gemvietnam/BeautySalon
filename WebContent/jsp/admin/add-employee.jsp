<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Category"%> 
<%@page import="models.Service"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Employee - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>

<% List<Service> services = (List<Service>) request.getAttribute("services"); %>

<div id="content">

	<div id="content-bar">
		<h1>Add employee</h1>
	</div>
	
	<div class="app-data">
		
		<%-- <form method="post" action="admin?page=employees">
			<label>First name</label><br/>
			<input type="text" name="firstName" /> <br/>
			<label>Last name</label><br/>
			<input type="text" name="lastName" /><br/>
			<label>Email</label><br/>
			<input type="email" name="email" /><br/>
			<label>Title</label><br/>
			<input type="text" name="title" /><br/>
			<label>Description</label><br/>
			<input type="text" name="description" /><br/>
			<label>Profile picture path</label><br/>
			<input type="text" name="profilePicture" /><br/>
			
			<label>Services</label><br/>
			
			<c:forEach items="${services}" var="service">
				<input type="checkbox" name="service" value="<c:out value="${service.id}"/>"> <c:out value="${service.name}"/><br>
			</c:forEach>
			
			<input type="checkbox" name="vehicle" value="Bike"> I have a bike<br>
	  		<input type="checkbox" name="vehicle" value="Car" checked> I have a car<br>
			
			<input type="submit" value="Add employee" />
		</form> --%>
		
		
		<form method="post" action="admin?page=employees">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>First name</label>
						<input type="text" name="firstName" class="form-control" placeholder="First name" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Last name</label>
						<input type="text" name="lastName" class="form-control" placeholder="Last name" />
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Email</label>
						<input type="text" name="email" class="form-control" placeholder="Email" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Title</label>
						<input type="text" name="title" class="form-control" placeholder="Title" />
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Description</label>
						<input type="text" name="description" class="form-control" placeholder="Description" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Profile picture path</label>
						<input type="text" name="profilePicture" class="form-control" placeholder="Profile picture path" />
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group" style="margin-bottom: 0px;">
								<label>Services</label>
							</div>
						</div>
						<c:forEach items="${services}" var="service">
							<div class="col-md-3">
								<div class="checkbox" style="margin-bottom: 0px;">
								  <label>
								    <input type="checkbox" name="service" value="<c:out value="${service.id}"/>">
								     <c:out value="${service.name}"/>
								  </label>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>	
			</div>		
			
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-12">
					<div class="form-group">
						<input type="submit" value="Add employee" class="btn btn-primary" />
					</div>
				</div>
			</div>

		</form>		
		

	</div>
	
	
</div>


</body>
</html>