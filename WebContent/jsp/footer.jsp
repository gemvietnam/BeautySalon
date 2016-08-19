<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>    

<% Calendar now = Calendar.getInstance();
   int year = now.get(Calendar.YEAR);  %>

<footer>
	<div class="wrapper">
		<div class="row">
			<div class="col-md-3">
				<ul>
					<li><a href="#">Home</a></li>
					<li><a href="#">Treatments</a></li>
					<li><a href="#">Employees</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
			<div class="col-md-3">
				<ul>
					<li><a href="#">Home</a></li>
					<li><a href="#">Treatments</a></li>
					<li><a href="#">Employees</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
			<div class="col-md-3">
				<p><strong>Opening hours</strong></p>
				<ul>
					<li><strong>Monday:</strong> 09:00-17:00</li>
					<li><strong>Tuesday:</strong> 09:00-17:00</li>
					<li><strong>Wednesday:</strong> 09:00-17:00</li>
					<li><strong>Thursday:</strong> 09:00-17:00</li>
					<li><strong>Friday:</strong> 09:00-17:00</li>
					<li><strong>Saturday:</strong> 09:00-17:00</li>
					<li><strong>Sunday:</strong> 09:00-17:00</li>
				</ul>
			</div>
			<div class="col-md-3">
				<p><strong><%= getServletContext().getAttribute("companyName") %></strong></p>
				<p><i class="fa fa-map-marker" aria-hidden="true"></i> <%= getServletContext().getAttribute("address") %></p>
				<p><i class="fa fa-phone" aria-hidden="true"></i> <%= getServletContext().getAttribute("phone") %></p>
				<p><i class="fa fa-envelope" aria-hidden="true"></i> <%= getServletContext().getAttribute("email") %></p>
			</div>
		</div>
	</div>

	<div class="col-md-12 text-center copyright">
		<p>Copyright <%= year %> &copy; <%= getServletContext().getAttribute("companyName") %></p>
	</div>

</footer>