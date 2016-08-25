<%@page import="assets.Helpers"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="models.User"%>  

<% 
User user = new User();
user.setFirstName("");
user.setLastName("");
if (session.getAttribute("user") != null) {
	user = (User) session.getAttribute("user");
}
%>

<div class="title">
	<h4>Avanti Beauty Salon</h4>
	<h6>Content Management System</h6>
</div>

    
<ul>
	<li class="user"><div class="dropdown">
			  <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  	<i class="fa fa-user"></i> Hello, <%= user.getFirstName() %> <%= user.getLastName() %>
			    <span class="caret pull-right"></span>
			  </a>
			  <ul class="dropdown-menu logout" aria-labelledby="dropdownMenu1">
			    <!-- <li><a href="#">Edit profile</a></li>
			    <li role="separator" class="divider"></li> -->
			    <li><form method="post" action="login?action=logout"><input type="submit" value="Logout"/></form></li>
			  </ul>
			</div></li>
	<li><a href="admin?"><i class="fa fa-dashboard"></i> Dashboard</a></li>
	<li><a href="admin?page=pages"><i class="fa fa-file"></i> Pages</a></li>
	<li><a href="admin?page=categories"><i class="fa fa-folder-open"></i> Categories</a></li>
	<li><a href="admin?page=treatments"><i class="fa fa-dashboard"></i> Treatments</a></li>
	<li><a href="admin?page=employees"><i class="fa fa-users"></i> Employees</a></li>
	<li><a href="admin?page=bookings"><i class="fa fa-calendar"></i> Bookings</a></li>
	<li><a href="admin?page=schedule"><i class="fa fa-calendar"></i> Schedule</a></li>
	<li><a href="admin?page=images"><i class="fa fa-image"></i> Images</a></li>
	<li><a href="admin?page=settings"><i class="fa fa-cogs"></i> Settings</a></li>
	<li><a href="admin?page=translator"><i class="fa fa-cogs"></i> Translator</a></li>
	<li><a href="<%= Helpers.getBaseUrl(request) %>/" target="_BLANK"><i class="fa fa-search"></i> Preview website</a></li>
</ul>