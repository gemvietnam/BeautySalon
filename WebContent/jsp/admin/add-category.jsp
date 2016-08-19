<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
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

<div id="content">
	
	<div id="content-bar">
		<h1>Add category</h1>
	</div>
	
	<div class="app-data">
	
		<form method="post" action="admin?page=categories">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" class="form-control" placeholder="Name" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Description</label>
						<input type="text" name="description" class="form-control" placeholder="Description" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Image path</label>
						<input type="text" name="picture" class="form-control" placeholder="Image path" />
					</div>
				</div>
			</div>			
			<div class="row">
				<div class="col-md-12">
					<input type="hidden" name="type" value="add" />
					<input type="submit" value="Add category" class="btn btn-primary"/>
				</div>
			</div>
			
		</form>
	
	</div>
	
	
</div>


</body>
</html>