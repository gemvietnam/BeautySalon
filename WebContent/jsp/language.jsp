<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>
<%@include file="styles.jsp" %>
</head>
<body>

<%@include file="navigation.jsp" %>
<% String languageText = (String) request.getAttribute("languageText"); %>
<% String translation = (String) request.getAttribute("translation"); %>

<section id="treatments">
	<div class="wrapper">
		<div class="row">
			<div class="col-md-12">
				<h1>Language</h1>
				<p>My example language translation servlet. <%= languageText %></p>
				<h4><%= translation %></h4>
			</div>
		</div>
	
	</div>
</section>

<%@include file="calltoaction.jsp" %>

<%@include file="footer.jsp" %>

</body>
</html>