<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@ page import="java.util.List"%>
<%@ page import="models.Page" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>
<%@include file="styles.jsp" %>
</head>
<body>

<%@include file="navigation.jsp" %>
<% Page customPage = (Page) request.getAttribute("customPage"); %>
<% String template = customPage.getTemplate(); %>
<% System.out.println("PAGE TEMPLATE IS: " + template); %>


<% switch(template) { 
	case "standard": %>
<%@include file="page-templates/standard.jspf" %>
<%  break; 
	case "treatments": %>
<%@include file="page-templates/treatments.jspf" %>
<% break; 
   case "employees": %>
<%@include file="page-templates/employees.jspf" %>
<% break;
   case "gallery": %>
<%@include file="page-templates/gallery.jspf" %>
<% break;
   } %> 

<%@include file="footer.jsp" %>

</body>
</html>