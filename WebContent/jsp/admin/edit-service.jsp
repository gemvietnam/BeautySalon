<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Service"%>
<%@page import="models.Category"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>

<% Service service = (Service) request.getAttribute("service"); %>
<% List<Category> categories = (List<Category>) request.getAttribute("categories"); %>

<div id="content">

	<div id="content-bar">
		<h1>Add treatment</h1>
	</div>

	<div class="app-data">
		
		<form method="post" action="admin?page=treatments">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" class="form-control" placeholder="Name" value="<%= service.getName() %>" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Description</label>
						<input type="text" name="description" class="form-control" placeholder="Description" value="<%= service.getDescription() %>"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>Price</label>
						<div class="input-group">
					      <input type="number" name="price" class="form-control" placeholder="Price" value="<%= service.getPrice() %>">
					      <div class="input-group-addon">DKK</div>
					    </div> 
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Time</label>
						<div class="input-group">
					      <input type="time" name="time" class="form-control" value="<%= service.getTime() %>">
					      <div class="input-group-addon">hours</div>
					    </div> 
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Category</label>
						<select name="categoryId" class="form-control">
							<% int catId = service.getCategoryId(); %>
							<% for (Category category : categories) { 
								String selectedText;
								if (category.getId() == catId) {
									selectedText = "selected";
								} else {
									selectedText = "";
								} %>		
								<option value="<%= category.getId() %>" <%= selectedText %>><%= category.getName() %></option>
							<% } %>
							
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<input type="hidden" name="id" value="<%= service.getId() %>" />
					<input type="hidden" name="type" value="update" />
					<input type="submit" value="Update treatment" class="btn btn-primary" />
				</div>
			</div>
		</form>
	
	</div>
	
	
</div>


</body>
</html>