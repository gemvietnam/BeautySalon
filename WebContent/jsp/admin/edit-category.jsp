<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Category"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Categories - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>

<% Category category = (Category) request.getAttribute("category"); %>

<div id="content">
	
	<div id="content-bar">
		<h1>Edit category</h1>
	</div>
	
	<div class="app-data">
	
		<form id="form" method="post" action="admin?page=categories&type=update" enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" class="form-control" placeholder="Name" value="<%= category.getName() %>" minlength="2" required />
					</div>
					<div class="form-group">
						<label>Description</label>
						<textarea name="description" class="form-control" rows="10"><%= category.getDescription() %></textarea>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Category image</label><br/>
						<% if (category.getPicture() != null) { %>
							<div class="edit-image-thumbnail imageHideable" style="background-image: url('<%= Helpers.getBaseUrl(request) %>/uploads/categories/<%= category.getPicture() %>')"></div>
							<a id="showFileUpload" class="btn btn-danger">Remove</a>
						<% } %><br/>
						<input class="fileUploadHideable" type="file" name="file" size="50" style="display: none"/>
					</div>
				</div>
			</div>
			<%-- <div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Category image</label>
						<% if (category.getPicture() != null) { %>
							<img class="imageHideable" src="<%= Helpers.getBaseUrl(request) %>/uploads/categories/<%= category.getPicture() %>" /><br/>
							<a id="showFileUpload" class="btn btn-danger">Remove</a>
						<% } %><br/>
						<input class="fileUploadHideable" type="file" name="file" size="50" style="display: none"/>
					</div>
				</div>
			</div>	 --%>		
			<div class="row">
				<div class="col-md-12">
					<input type="hidden" name="id" value="<%= category.getId() %>" />
					<input type="hidden" name="type" value="update" />
					<input id="existingImagePath" type="hidden" name="existingImagePath" value="<%= category.getPicture() %>" />
					<input type="submit" value="Update category" class="btn btn-primary"/>
				</div>
			</div>
			
		</form>
	
	</div>
	
	
</div>

<script>
	$("#form").validate();
	$("#showFileUpload").click(function() {
		$(this).hide();
		$(".fileUploadHideable").show();
		$(".imageHideable").hide();
		$("#existingImagePath").val("newFile");
	})
</script>

</body>
</html>