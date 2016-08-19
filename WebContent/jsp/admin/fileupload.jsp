<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Image"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../styles-dashboard.jsp" %>
<script src="/BeautySalon/assets/masonry/masonry.pkgd.min.js"></script>
</head>
<body>

<% List<Image> images = (List<Image>) request.getAttribute("images"); %>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>
<div id="content">

	<div id="content-bar">
		<h1>Images</h1>
	</div>
	
	
	<div class="app-data">

		<h3>Add new image</h3>
		<div class="row">
			<div class="col-md-4">
				<form action="admin?page=images&action=addImage" method="post" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-8">
							<input type="file" name="file" size="500"/>
						</div>
						<div class="col-md-4">
							<input type="submit" value="Upload file" class="btn btn-primary" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<br/><br/>
		
		<h3>Uploaded images</h3>
	
		<div class="grid">
		<% for (Image image : images) { %>
			<div class="grid-item">
				<div class="delete-image btn btn-danger" data-imageId="<%= image.getId() %>"><i class="fa fa-trash"></i></div>
				<a class="fancybox" rel="group" href="/BeautySalon/uploads/<%= image.getPath() %>"><img src="/BeautySalon/uploads/<%= image.getPath() %>" alt="" /></a>
			</div>
		<% } %>
		</div>
	
	</div>
	
</div>

<script>
$(document).ready(function() {
	$(".delete-image").click(function() {
		var imageId = $(this).attr("data-imageId");
		console.log("Delete image with id: " + imageId);
		$(this).parent().hide();
		
		$.ajax({
			url: 'admin?page=images',
			method: 'post', 
			data: {'delete':'delete', 'imageId': imageId},
			success: function(responseText) {
				console.log("Image has been deleted.");
				$('.grid').masonry({
					  // options
					  itemSelector: '.grid-item',
					  columnWidth: 250
					});
			}
		});
	})
})
</script>
<script>
$(document).ready(function() {
	$('.grid').masonry({
		  // options
		  itemSelector: '.grid-item',
		  columnWidth: 250
		});
})
</script>
</body>
</html>