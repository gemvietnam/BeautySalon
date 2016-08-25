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
			<%-- <c:forEach items="${employees}" var="employee"> --%>
			<% for (int i = 1; i <= employees.size(); i++) { %>
				<% if ((i == 1) || (i % 3 == 1)) { %>
					<div class="row">
				<% } %>
					<div class="col-md-4 employee-profile">
						<div class="employee-thumbnail" style="background-image: url('<%= Helpers.getBaseUrl(request) %>/uploads/employees/<%= employees.get(i-1).getProfilePicture() %>');"></div>
						<%-- <img src="<%= Helpers.getBaseUrl(request) %>/uploads/employees/<c:out value="${employee.profilePicture}"/>" /> --%>
						<h1><%= employees.get(i-1).getFirstName() %> <%= employees.get(i-1).getLastName() %></h1>
						<h3><%= employees.get(i-1).getTitle() %></h3>
						<p><%= employees.get(i-1).getDescription() %></p>
					</div>
				<% if (i % 3 == 0) { %>
					</div>
				<% } %>
			<% } %>
			<%-- </c:forEach> --%>
		</div>
	</div>
</section>

<%@include file="calltoaction.jsp" %>

<%@include file="footer.jsp" %>

</body>
</html>