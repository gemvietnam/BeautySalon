<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Employee"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>>
<%@include file="styles.jsp" %>
</head>
<body>

<%@include file="navigation.jsp" %>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>

<section id="treatments">
	<div class="wrapper">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="text-center">Meet our stuff</h1>
				<h4>We are always trying to provide the best quality services.</h4>
			</div>
		</div>
		
		<br/><br/><br/>
		
		<div class="row">
			<c:forEach items="${employees}" var="employee">
				<div class="col-md-4">
					<img src="img/<c:out value="${employee.profilePicture}"/>" />
					<h1><c:out value="${employee.firstName}"/> <c:out value="${employee.lastName}"/></h1>
					<h3><c:out value="${employee.title}"/></h3>
					<p><c:out value="${employee.description}"/></p>
				</div>
			</c:forEach>
		</div>
	</div>
</section>

<%@include file="calltoaction.jsp" %>

<%@include file="footer.jsp" %>

</body>
</html>