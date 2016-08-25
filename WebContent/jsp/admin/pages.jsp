<%@page import="assets.Helpers"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Page"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pages - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<% List<Page> pages = (List<Page>) request.getAttribute("pages"); %>
<% String keyword = (String) request.getParameter("keyword"); %>

<div id="navigation">	
<%@include file="navigation.jsp" %>
</div>

<div id="content">

	<div id="content-bar">
		<a href="?page=addPage" class="btn btn-primary pull-right">Add new page</a>
		<h1>Pages</h1>
	</div>

	<div class="app-data">
	
		<% if (keyword != null) { %>
			<div>
				<p>Searching for employees with keyword: <strong><%= keyword %></strong>.</p>
				<p><strong><a href="admin?page=employees">Reset search results</a></strong></p>
			</div>
		<% } %>	
	
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<td>Status</td>
					<td>Created</td>
					<td>Title</td>
					<td>Slug</td>
					<td>Edit</td>
					<td>Delete</td>
					<td>Preview</td>
				</tr>
			</thead>
			
			<tbody>
				<% for (Page currentPage : pages) { %>
					<% String pageStatus = (currentPage.isPublished()) ? "published" : "draft"; %>
					<% String pageLabel = (currentPage.isPublished()) ? "success" : "danger"; %>
					<tr>
						<td><span class="label label-<%= pageLabel %>"><%= pageStatus %></span></td>
						<td><%= Helpers.TimestampToString(currentPage.getCreated()) %></td>
						<td><%= currentPage.getTitle() %></td>
						<td><%= currentPage.getSlug() %></td>
						<td><a class="btn btn-primary btn-sm" href="?page=editPage&id=<%= currentPage.getId() %>" role="button">Edit</a></td>
						<td><a class="btn btn-danger btn-sm" href="?page=deletePage&id=<%= currentPage.getId() %>" role="button">Delete</a></td>
						<td><a class="btn btn-default btn-sm" href="<%= Helpers.getBaseUrl(request) %>/?page=<%= currentPage.getId() %>" target="_BLANK">Preview</a>
					</tr>
				<% } %>
			</tbody>
		</table>
		
		<div class="row">
			<div class="col-md-6">
				<h3>Menu order</h3>
				<ul id="sortable">
					<% for (Page currentPage : pages) { %>
						<li class="ui-state-default" data-pageId="<%= currentPage.getId() %>"><%= currentPage.getTitle() %></li>
					<% } %>
				</ul>
				<input id="updateMenu" type="submit" value="Update menu" class="btn btn-primary" />
			</div>
		</div>
		
		
	</div>
</div>

<script>
$(function() {
  $("#sortable").sortable();
  $("#sortable").disableSelection();
});

var pagesOrder = [];

$("#updateMenu").click(function() {
	$("#sortable li").each(function(i) {
		var id = $(this).attr("data-pageId");
		console.log("Page id: " + id);
		pagesOrder.push(id);
		pagesOrder.push(i+1);
	});
/* 	console.log("Our array: " + pagesOrder[0].order); */
	
	$.ajax({
		url: 'admin',
		method: 'post', 
		data: {'page':'pages', 'type': 'updateMenu', 'pagesOrder': [1,5,6,2,6,7]},
		success: function(responseText) {
			console.log("Menu has been updated");
		}
	});
});
</script>

</body>
</html>