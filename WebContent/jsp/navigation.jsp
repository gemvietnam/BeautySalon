<%@page import="assets.Helpers"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
    
<% List<String[]> menu = (List<String[]>) getServletContext().getAttribute("menu"); 
   System.out.println(menu);
%>    
    
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="?"><%= getServletContext().getAttribute("siteTitle") %></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    
      <ul class="social-media navbar-right">
      	<li><a href="<%= getServletContext().getAttribute("facebook") %>"><i class="fa fa-facebook"></i></a></li>
      	<li><a href="<%= getServletContext().getAttribute("twitter") %>"><i class="fa fa-twitter"></i></a></li>
      	<li><a href="<%= getServletContext().getAttribute("instagram") %>"><i class="fa fa-instagram"></i></a></li>
      	<li><a href="<%= getServletContext().getAttribute("pinterest") %>"><i class="fa fa-pinterest"></i></a></li>
      </ul>
    
      <ul class="nav navbar-nav navbar-right">
        <%-- <li><a href="<%= Helpers.getBaseUrl(request) %>/">Home</a></li> --%>
<!--         <li><a href="?page=about">About</a></li>
        <li><a href="?page=treatments">Treatments</a></li>
        <li><a href="?page=employees">Our Staff</a></li>
        <li><a href="?page=gallery">Gallery</a></li>
        <li><a href="?page=contact">Contact</a></li> -->
        <% for (String[] pageData : menu) { %>
        	<li><a href="?page=<%= pageData[0] %>"><%= pageData[1] %></a></li>
        <% } %> 
	  </ul>	

    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>