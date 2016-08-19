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
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>
<%@include file="styles.jsp" %>

  
</head>
<body>

<%@include file="navigation.jsp" %>

<% String firstName = request.getParameter("firstName"); %>

<section id="treatments">
	<div class="wrapper">
		
		<h2>You have booked a visit successfuly</h2>
		
		<h3>Selected treatment:</h3>
		<c:out value="${service.name}"/>
		<p><c:out value="${service.description}"/></p>
		<p>Price: <c:out value="${service.price}"/> DKK</p>
		<p>Duration: <c:out value="${service.time}"/> hours</p>
	
	
		<h3>Your personal data:</h3>
		<label>Name:</label><br/>
		<%= request.getParameter("firstName") %> <%= request.getParameter("lastName") %><br/>
		<label>Email address:</label><br/>
		<%= request.getParameter("email") %><br/>
		<label>Phone number:</label><br/>
		<%= request.getParameter("phone") %><br/>
		
		<h3>Cancelation of the visit can be done minimum 24 hours before the appointment. To cancel the visit please call +45 10204082.</h3>
		
		
	</div>
</section>

<%@include file="calltoaction.jsp" %>

<%@include file="footer.jsp" %>

</body>
</html>