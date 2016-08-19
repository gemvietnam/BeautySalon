<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Category"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>
<%@include file="styles.jsp" %>
</head>
<body>

<%@include file="navigation.jsp" %>
<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>

<section id="treatments">
	<div class="wrapper">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="text-center">Treatments</h1>
				<h4>We are always trying to provide the best quality services.</h4>
			</div>
		</div>
		<br/><br/><br/>
		
		<div class="row">
			<div class="col-md-12">
				<c:forEach items="${categories}" var="category">
					<a href="?page=category&id=<c:out value="${category.id}"/>">
						<div class="col-md-4 text-center treatment" style="background-image: url('img/<c:out value="${category.picture}"/>')")>
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

<%@include file="footer.jsp" %>

</body>
</html>