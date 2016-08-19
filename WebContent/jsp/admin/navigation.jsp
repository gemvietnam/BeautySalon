<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="models.User"%>  

<%-- <% User user = (User) session.getAttribute("user"); %> --%>

<div class="title">
	<h4>Avanti Beauty Salon</h4>
	<h6>Content Management System</h6>
</div>

<div class="user">
	<div class="row">
		<div class="col-md-3">
			<img src="img/user.jpg" class="user-img"/>
		</div>
		<div class="col-md-9">
			<div class="dropdown">
			  <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			    <%-- <%= user.getFirstName() %> <%= user.getLastName() %> --%>
			    <span class="caret"></span>
			  </a>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			    <li><a href="#">My profile</a></li>
			    <li><a href="#">Edit profile</a></li>
			    <li role="separator" class="divider"></li>
			    <li><form method="post" action="login"><input type="submit" value="Logout"/></form></li>
			  </ul>
			</div>
			
		</div>
	</div>
</div>
    
<ul>
	<li><a href="admin?"><i class="fa fa-dashboard"></i> Dashboard</a></li>
	<li><a href="admin?page=categories"><i class="fa fa-folder-open"></i> Categories</a></li>
	<li><a href="admin?page=treatments"><i class="fa fa-dashboard"></i> Treatments</a></li>
	<li><a href="admin?page=employees"><i class="fa fa-users"></i> Employees</a></li>
	<li><a href="admin?page=bookings"><i class="fa fa-calendar"></i> Bookings</a></li>
	<li><a href="admin?page=images"><i class="fa fa-image"></i> Images</a></li>
	<li><a href="admin?page=settings"><i class="fa fa-cogs"></i> Settings</a></li>
	<li><a href="website"><i class="fa fa-search"></i> Preview website</a></li>
</ul>