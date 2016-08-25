<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Page - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
<script>
tinymce.init({
  selector: 'textarea',  // change this value according to your HTML
  theme: 'modern',
  toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons',
  menubar: false,
  plugins: [
      'advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker',
      'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
      'save table contextmenu directionality emoticons template paste textcolor'
    ]
});
</script>
</head>
<body>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>

<div id="content">

	<div id="content-bar">
		<h1>Add page</h1>
	</div>

	<div class="app-data">
		
		<form id="form" method="post" action="admin?page=pages">
			<div class="row">
				<div class="col-md-9">
					<div class="form-group">
						<label>Title</label>
						<input type="text" name="title" class="form-control" placeholder="Page title" minlength="2" required />
					</div>
					<div class="form-group">
						<label>Content</label>
						<textarea rows="20" cols="10" name="content" class="form-control"></textarea>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Slug</label>
						<input type="text" name="slug" class="form-control" placeholder="Slug" minlength="2" required />
					</div>
					<div class="form-group">
						<label>Published status</label>
						<select name="isPublished" class="form-control">
							<option value="1">Published</option>
							<option value="0">Draft</option>
						</select>
					</div>
					<div class="form-group">
						<input type="hidden" name="type" value="add" />
						<input type="submit" value="Add page" class="btn btn-primary form-control" />
					</div>
				</div>
				
			</div>
		</form>
	
	</div>
	
	
</div>

<script>
	$("#form").validate();
</script>

</body>
</html>