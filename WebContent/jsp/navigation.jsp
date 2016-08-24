<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
        <li><a href="<%= Helpers.getBaseUrl(request) %>/">Home</a></li>
        <li><a href="?page=about">About</a></li>
        <li><a href="?page=treatments">Treatments</a></li>
        <li><a href="?page=employees">Our Staff</a></li>
        <li><a href="?page=gallery">Gallery</a></li>
        <li><a href="?page=contact">Contact</a></li>
        <!-- <li><a href="website?page=treatments"><strong>Book a visit</strong></a></li> -->
        
        <!-- <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Login <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="admin">Admin panel</a></li>
          </ul>
        </li>
      </ul> -->
      
    
      
      <!-- <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Login</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul> -->
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>