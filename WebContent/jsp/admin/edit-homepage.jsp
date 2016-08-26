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
		<h1>Edit page</h1>
	</div>

	<div class="app-data">
		
		<form id="form" method="post" action="admin?page=pages">
			<div class="row">
				<div id="pageOptions" class="col-md-9">
					<div id="homepageTemplate">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" class="form-control" placeholder="Page title" minlength="2" required value="Home" disabled/>
						</div>
						
						<!-- HEADER EDITOR -->
						<div class="panel panel-default">
						  <div class="panel-heading">
						    <h3 class="panel-title">Header</h3>
						  </div>
						  <div class="panel-body">
						    <div class="form-group">
								<label>Heading</label>
								<input type="text" name="homeHeading" class="form-control" placeholder="Page heading" minlength="2" required />
							</div>
							<div class="form-group">
								<label>Subheading</label>
								<input type="text" name="homeSubheading" class="form-control" placeholder="Page subheading" minlength="2" required />
							</div>
							<div class="form-group">
								<label>Background image</label>
								<input type="file" name="homeBackgroundImage" required />
							</div>
						  </div>
						</div>
						
						<!-- LEFT-ALIGNED TEXT EDITOR -->
						<div class="panel panel-default">
						  <div class="panel-heading">
						    <h3 class="panel-title">Left-aligned section</h3>
						  </div>
						  <div class="panel-body">
						    <div class="form-group">
								<label>Heading</label>
								<input type="text" name="lsHeading" class="form-control" placeholder="Section heading" minlength="2" required />
							</div>
							<div class="form-group">
								<label>Subheading</label>
								<input type="text" name="lsSubheading" class="form-control" placeholder="Section subheading" minlength="2" required />
							</div>
							<div class="form-group">
								<label>Image</label>
								<input type="file" name="lsImage" required />
							</div>
						  </div>
						</div>
						
						<!-- RIGHT-ALIGNED TEXT EDITOR -->
						<div class="panel panel-default">
						  <div class="panel-heading">
						    <h3 class="panel-title">Right-aligned section</h3>
						  </div>
						  <div class="panel-body">
						    <div class="form-group">
								<label>Heading</label>
								<input type="text" name="rsHeading" class="form-control" placeholder="Section heading" minlength="2" required />
							</div>
							<div class="form-group">
								<label>Subheading</label>
								<input type="text" name="rsSubheading" class="form-control" placeholder="Section subheading" minlength="2" required />
							</div>
							<div class="form-group">
								<label>Image</label>
								<input type="file" name="rsImage" required />
							</div>
						  </div>
						</div>
						
						<!-- TREATMENTS TEXT EDITOR -->
						<div class="panel panel-default">
						  <div class="panel-heading">
						    <h3 class="panel-title">Treatments section</h3>
						  </div>
						  <div class="panel-body">
						    <div class="form-group">
								<label>Heading</label>
								<input type="text" name="tHeading" class="form-control" placeholder="Section heading" minlength="2" required />
							</div>
							<div class="form-group">
								<label>Subheading</label>
								<input type="text" name="tSubheading" class="form-control" placeholder="Section subheading" minlength="2" required />
							</div>
						  </div>
						</div>
						
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-info">
					  <div class="panel-heading">Homepage editor</div>
					  <div class="panel-body">
					    <strong>Homepage editor</strong> allows you to edit the content of your website's homepage. It has predefined template, the only thing you have to do is fill in the data. If you want to create other pages, go to <a href="?page=pages"><strong>Pages</strong></a>.
					  </div>
					</div>
					<div class="form-group">
						<label>Published status</label>
						<select name="isPublished" class="form-control">
							<option value="1">Published</option>
							<option value="0" disabled>Draft</option>
						</select>
					</div>
					<div class="form-group">
						<input type="hidden" name="type" value="add" />
						<input type="submit" value="Update page" class="btn btn-primary form-control" />
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