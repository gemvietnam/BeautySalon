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
<% Category category = (Category) request.getAttribute("category"); %>
<% List<Service> services = (List<Service>) request.getAttribute("services"); %>

<div></div>
<section id="treatments">
	<div class="wrapper">
		<div class="row">
			<div class="col-md-7">
				<h1><%= category.getName() %></h1>
				<p><%= category.getDescription() %></p>
				
				<table class="service-table">
					<c:forEach items="${services}" var="service">
						<tr>
							<td class="service-name"><h3><a href="booking?id=<c:out value="${service.id}"/>"><c:out value="${service.name}"/></a></h3><p><c:out value="${service.description}"/></p></td>
							<td class="servie-time"><c:out value="${service.time}"/> hours<td>
							<td class="service-price"><c:out value="${service.price}"/> DKK</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-5">
				<img src="img/<%= category.getPicture() %>">
				
				<h1>Book a visit online</h1>
				<p>Save time and money by making reservation online. Select a treatment from the list and click <i>Book a visit</i> to proceed to booking form.</p>
				<a class="btn btn-lg btn-primary">Book a visit</a>
			</div>
		</div>
	
		
	</div>
</section>

<script>
$('.service-table tr').click(function() {
	$('.service-table tr').removeClass('selected');
	$(this).addClass('selected');
});
</script>

<%@include file="calltoaction.jsp" %>

<%@include file="footer.jsp" %>

</body>
</html>