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
<% List<String[]> menu = (List<String[]>) request.getAttribute("menu"); %>
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
	
		<table class="table table-bordered table-hover" data-order='[[ 1, "desc" ]]' data-page-length='25'>
			<thead>
				<tr>
					<td>Status</td>
					<td>Title</td>
					<td>Slug</td>
					<td>Last updated</td>
					<td>Author</td>
					<td>Edit</td>
					<td>Delete</td>
					<td>Preview</td>
				</tr>
			</thead>
			
			<tbody>
				<tr class="homepage">
					<td><span class="label label-success">published</span></td>
					<td>Homepage</td>
					<td>index</td>
					<td></td>
					<td></td>
					<td><a class="btn btn-primary btn-sm" href="?page=editHomePage" role="button">Edit</a></td>
					<td><a class="btn btn-danger btn-sm" href="#" role="button" disabled>Delete</a></td>
					<td><a class="btn btn-default btn-sm" href="<%= Helpers.getBaseUrl(request) %>/" target="_BLANK">Preview</a>
				</tr>
				<% for (Page currentPage : pages) { %>
					<% String pageStatus = (currentPage.isPublished()) ? "published" : "draft"; %>
					<% String pageLabel = (currentPage.isPublished()) ? "success" : "danger"; %>
					<tr>
						<td><span class="label label-<%= pageLabel %>"><%= pageStatus %></span></td>
						<td><%= currentPage.getTitle() %></td>
						<td><%= currentPage.getSlug() %></td>
						<td><%= Helpers.TimestampToString(currentPage.getCreated()) %></td>
						<td><%= currentPage.getAuthorName() %></td>
						<td><a class="btn btn-primary btn-sm" href="?page=editPage&id=<%= currentPage.getId() %>" role="button">Edit</a></td>
						<td><a class="btn btn-danger btn-sm" href="?page=deletePage&id=<%= currentPage.getId() %>" role="button">Delete</a></td>
						<td><a class="btn btn-default btn-sm" href="<%= Helpers.getBaseUrl(request) %>/?page=<%= currentPage.getId() %>" target="_BLANK">Preview</a>
					</tr>
				<% } %>
			</tbody>
		</table>
		<br/><br/>
		
		<div class="row">
			<div class="col-md-3">
				<h4><strong>Menu order</strong></h4>
				<ul id="sortable">
					<% for (String[] item : menu) { %>
						<li class="ui-state-default" data-pageId="<%= item[0] %>"><%= item[1] %></li>
					<% } %>
				</ul>
				<input id="updateMenu" type="submit" value="Update menu" class="btn btn-primary form-control" />
			</div>
			<div class="col-md-3">
				<br/><br/>
				<div class="panel panel-info">
				  <div class="panel-heading">Menu order</div>
				  <div class="panel-body">
				    <strong>Menu order</strong> allows you to sort the links displayed in the top
				     navigation bar of the website. Simply drag and drop selected pages to sort 
				     the menu and hit <strong>Update menu</strong> when you are ready to save the changes.
				  </div>
				</div>
			</div>
		</div>
		
		
	</div>
</div>

<script>
$(function() {
  $("#sortable").sortable();
  $("#sortable").disableSelection();
});

var pagesOrder = new Array();

$("#updateMenu").click(function() {
	$("#sortable li").each(function(i) {
		var id = $(this).attr("data-pageId");
		console.log("Page id: " + id);
		pagesOrder.push(id);
		pagesOrder.push(i+1);
	});
	
	$(pagesOrder).each(function(i) {
		console.log(pagesOrder[i]);
	});
	
	$.ajax({
		url: 'admin',
		method: 'post', 
		data: {'page':'pages', 'type': 'updateMenu', 'pagesOrder': pagesOrder},
		success: function(responseText) {
			console.log("Menu has been updated");
		}
	});
});
</script>

<script>
$(document).ready( function () {
	$('table').dataTable({
		  "columnDefs": [{
		      "targets": [5, 6, 7],
		      "orderable": false
		    }],
		} );
});
</script>

</body>
</html>