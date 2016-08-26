<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Category"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categories - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>
<% String newCatName = (String) request.getParameter("name"); %>
<% String keyword = (String) request.getParameter("keyword"); %>
<% String table = (String) request.getParameter("table"); %>
<% String type = (String) request.getParameter("type"); %>


<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>
<div id="content">

	<div id="content-bar">
		<a href="?page=addCategory" class="btn btn-primary pull-right">Add new category</a>
		<!-- <div class="pull-right" style="margin-right: 50px;">
			<div class="row">
				<form method="post" action="admin?page=searchCategories">
					<div class="col-md-8" style="padding: 0px;">
						<input type="text" class="form-control" name="keyword" placeholder="Search categories" />
					</div>
					<div class="col-md-4" style="padding: 0px;">
						<input type="submit" class="form-control btn btn-primary" value="Search" />
					</div>
				</form>
			</div>
		</div> -->
		<h1>Categories</h1>
	</div>
	
	<% if (newCatName != null) { %>
		<div class="alert alert-success alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Success!</strong> The category <i><%= newCatName %></i> has been modified.
		</div>
	<% } %>

	<% if (table != null) { %>
		<div class="alert alert-success alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Success!</strong> The category has been deleted.
		</div>
	<% } %>	
	
	<div class="app-data">
	
		<% if (keyword != null) { %>
			<div>
				<p>Searching for treatments with keyword: <strong><%= keyword %></strong>.</p>
				<p><strong><a href="admin?page=categories">Reset search results</a></strong></p>
			</div>
		<% } %>
		
	
		<table class="table table-bordered table-hover" data-order='[[ 1, "desc" ]]' data-page-length='25'>
			<thead>
				<tr>
					<td>Name</td>
					<td>Description</td>
					<td>Image</td>
					<td class="author-name">Author</td>
					<td>Last updated</td>
					<td>Edit</td>
					<td>Delete</td>
				</tr>
			</thead>
			<tbody>
				<%-- <c:forEach items="${categories}" var="category"> --%>
				<% for (Category category : categories) { %>
					<tr>
						<td><%= category.getName() %></td>
						<td><%= category.getDescription() %></td>
						<td><div class="image-thumbnail" style="background-image: url('<%= Helpers.getBaseUrl(request) %>/uploads/categories/<%= category.getPicture() %>')"></div></td>
						<td class="author-name"><%= category.getAuthorName() %></td>
						<td class="lastUpdated"><%= Helpers.TimestampToString(category.getLastUpdated()) %></td>
						<td><a class="btn btn-primary" href="?page=editCategory&id=<%= category.getId() %>" role="button">Edit</a></td>
						<td><a class="btn btn-danger" href="?page=delete&table=Categories&id=<%= category.getId() %>" role="button">Delete</a></td>
					</tr>
				<% } %>
				<%-- </c:forEach> --%>
			</tbody>
		</table>
	
	</div>
	
	
</div>

<script>
$(document).ready( function () {
	$('table').dataTable({
		  "columnDefs": [{
		      "targets": [2, 5, 6],
		      "orderable": false
		    }],
		} );
});
</script>
</body>
</html>