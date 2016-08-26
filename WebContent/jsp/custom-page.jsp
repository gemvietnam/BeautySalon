<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@ page import="java.util.List"%>
<%@ page import="models.Page" %>
<%@ page import="models.Category" %>
<%@ page import="models.Employee" %>
<%@ page import="models.Image" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>
<%@include file="styles.jsp" %>
</head><body>

<%@include file="navigation.jsp" %>
<% Page customPage = (Page) request.getAttribute("customPage"); %>
<% String template = customPage.getTemplate(); %>


<% switch(template) { 
	case "standard": %>

	<section id="treatments">
		<div class="wrapper">
			<div class="row">
				<div class="col-md-12">
					<% if (customPage.getTitle() != null) { %>
						<h1 class="text-center"><%= customPage.getTitle() %></h1>
					<% } %>
					<% if (customPage.getContent() != null) { %>
						<p><%= customPage.getContent() %></p>		
					<% } %>
				</div>
			</div>
		
		</div>
	</section>
	
	<%@include file="calltoaction.jsp" %>
	

<%  break; 
	case "treatments": %>

<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>

	<section id="treatments">
		<div class="wrapper">
			<div class="row">
				<div class="col-md-12 text-center">
				    <% if (customPage.getHeading() != null) { %>
						<h1 class="text-center"><%= customPage.getHeading() %></h1>
					<% } %>
					<% if (customPage.getSubheading() != null) { %>
						<h4 class="text-center"><%= customPage.getSubheading() %></h4>
					<% } %>
				</div>
			</div>
			<br/><br/><br/>
			
			<div class="row">
				<div class="col-md-12">
					<c:forEach items="${categories}" var="category">
						<a href="?page=category&id=<c:out value="${category.id}"/>">
							<div class="col-md-4 text-center treatment" style="background-image: url('<%= Helpers.getBaseUrl(request) %>/uploads/categories/<c:out value="${category.picture}"/>')")>
								<div class="fade-effect">
									<h2><c:out value="${category.name}"/></h2>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>		
			
			
		</div>
	</section>
	
	<%@include file="calltoaction.jsp" %>


<% break; 
   case "employees": %>


	<% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
	
	<section id="employees">
		<div class="wrapper">
			<div class="row">
				<div class="col-md-12 text-center">
					<% if (customPage.getHeading() != null) { %>
						<h1 class="text-center"><%= customPage.getHeading() %></h1>
					<% } %>
					<% if (customPage.getSubheading() != null) { %>
						<h4 class="text-center"><%= customPage.getSubheading() %></h4>
					<% } %>
				</div>
			</div>
			
			<br/><br/><br/>
			
			<div class="row">
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
			</div>
		</div>
	</section>
	
	<%@include file="calltoaction.jsp" %>


<% break;
   case "gallery": %>
	
	<% List<Image> images = (List<Image>) request.getAttribute("images"); %>
	<% String url = (String) request.getAttribute("javax.servlet.forward.request_uri"); %>

	<!-- Add fancyBox -->
	<link rel="stylesheet" href="<%= Helpers.getBaseUrl(request) %>/assets/fancybox/source/jquery.fancybox.css?v=2.1.5" type="text/css" media="screen" />
	<script type="text/javascript" src="<%= Helpers.getBaseUrl(request) %>/assets/fancybox/source/jquery.fancybox.pack.js?v=2.1.5"></script>
	<script src="<%= Helpers.getBaseUrl(request) %>/assets/masonry/masonry.pkgd.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".fancybox").fancybox();
		});
	</script>
	
	<section id="treatments">
		<div class="wrapper">
			<div class="row">
				<div class="col-md-12 text-center">
					<% if (customPage.getHeading() != null) { %>
						<h1 class="text-center"><%= customPage.getHeading() %></h1>
					<% } %>
					<% if (customPage.getSubheading() != null) { %>
						<h4 class="text-center"><%= customPage.getSubheading() %></h4>
					<% } %>
					
					<div class="grid">
					<% for (Image image : images) { %>
						<div class="grid-item">
							<a class="fancybox" rel="group" href="<%= Helpers.getBaseUrl(request) %>/uploads/<%= image.getPath() %>"><img src="<%= Helpers.getBaseUrl(request) %>/uploads/<%= image.getPath() %>" alt="" /></a>
						</div>
					<% } %>
					</div>
					
				</div>
			</div>
		
		</div>
	</section>
	
	<%@include file="calltoaction.jsp" %>
	
	<script>
	$(document).ready(function() {
		$('.grid').masonry({
			  // options
			  itemSelector: '.grid-item',
			  columnWidth: 250
			});
	})
	</script>


<% break;
   } %> 

<%@include file="footer.jsp" %>

</body>
</html>