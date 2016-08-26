<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Page" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Page - Prettier CMS</title>
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

<% Page editedPage = (Page) request.getAttribute("page"); %>

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
					
					<div id="standardTemplate" class="template-options">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" value="<%= Helpers.DisplayIfNotNull(editedPage.getTitle()) %>" class="form-control" placeholder="Page title" minlength="2" required />
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="20" cols="10" value="<%= Helpers.DisplayIfNotNull(editedPage.getContent()) %>" name="content" class="form-control"></textarea>
						</div>
					</div>
								
								
					<div id="treatmentsTemplate" class="template-options">
						<div class="panel panel-info">
						  <div class="panel-heading">Treatments template</div>
						  <div class="panel-body">
						    <strong>Treatments template</strong> allows you to display all of the treatments categories added 
							in the dashboard. Categories will be displayed automaticaly at this page.
							To manage displayed data, go to <a href="?page=categories"><strong>Categories</strong></a>.
						  </div>
						</div>
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" value="<%= Helpers.DisplayIfNotNull(editedPage.getTitle()) %>" class="form-control" placeholder="Page title" minlength="2" required />
						</div>
						<div class="form-group">
							<label>Heading</label>
							<input type="text" name="heading" class="form-control" value="<%= Helpers.DisplayIfNotNull(editedPage.getHeading()) %>" placeholder="Heading" minlength="2" required />
						</div>
						<div class="form-group">
							<label>Subheading</label>
							<input type="text" name="subheading" class="form-control" value="<%= Helpers.DisplayIfNotNull(editedPage.getSubheading()) %>" placeholder="Subheading" minlength="2" required />
						</div>
					</div>
					
					
					<div id="employeesTemplate" class="template-options">
						<div class="panel panel-info">
						  <div class="panel-heading">Employees template</div>
						  <div class="panel-body">
						    <strong>Employees template</strong> allows you to display all of the employees added 
							in the dashboard. Employees will be displayed automaticaly at this page.
							To manage displayed data, go to <a href="?page=employees"><strong>Employees</strong></a>.
						  </div>
						</div>
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" value="<%= Helpers.DisplayIfNotNull(editedPage.getTitle()) %>" class="form-control" placeholder="Page title" minlength="2" required />
						</div>
						<div class="form-group">
							<label>Heading</label>
							<input type="text" name="heading" class="form-control" value="<%= Helpers.DisplayIfNotNull(editedPage.getHeading()) %>" placeholder="Heading" minlength="2" required />
						</div>
						<div class="form-group">
							<label>Subheading</label>
							<input type="text" name="subheading" class="form-control" value="<%= Helpers.DisplayIfNotNull(editedPage.getSubheading()) %>" placeholder="Subheading" minlength="2" required />
						</div>
					</div>
					
					
					<div id="galleryTemplate" class="template-options">
						<div class="panel panel-info">
						  <div class="panel-heading">Gallery template</div>
						  <div class="panel-body">
						    <strong>Gallery template</strong> allows you to display all of the images added 
							in the dashboard. Images will be displayed automaticaly at this page.
							To manage displayed data, go to <a href="?page=images"><strong>Images</strong></a>.
						  </div>
						</div>
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" value="<%= Helpers.DisplayIfNotNull(editedPage.getTitle()) %>" class="form-control" placeholder="Page title" minlength="2" required />
						</div>
						<div class="form-group">
							<label>Heading</label>
							<input type="text" name="heading" value="<%= Helpers.DisplayIfNotNull(editedPage.getHeading()) %>"  class="form-control" placeholder="Heading" minlength="2" required />
						</div>
						<div class="form-group">
							<label>Subheading</label>
							<input type="text" name="subheading" value="<%= Helpers.DisplayIfNotNull(editedPage.getSubheading()) %>"  class="form-control" placeholder="Subheading" minlength="2" required />
						</div>
					</div>
					
					
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Template</label>
						<% String selectedStandard = "";
						   String selectedTreatments = "";
						   String selectedEmployees = "";
						   String selectedGallery = "";

						   switch(editedPage.getTemplate()) {
						   		case "standard":
						   			selectedStandard = "selected";
						   			break;
						   		case "treatments":
						   			selectedTreatments = "selected";
						   			break;
						   		case "employees":
						   			selectedEmployees = "selected";
						   			break;
						   		case "gallery":
						   			selectedGallery = "selected";
						   			break;
						   }
						%>
						<select id="templateSelect" name="template" class="form-control">
							<option value="standard" <%= selectedStandard %>>Standard</option>
							<option value="treatments" <%= selectedTreatments %>>Treatments</option>
							<option value="employees" <%= selectedEmployees %>>Employees</option>
							<option value="gallery" <%= selectedGallery %>>Gallery</option>
						</select>
					</div>
					<div class="form-group">
						<label>Slug</label>
						<input type="text" name="slug" class="form-control" placeholder="Slug" minlength="2" required value="<%= Helpers.DisplayIfNotNull(editedPage.getSlug()) %>" />
					</div>
					<div class="form-group">
						<label>Published status</label>
						<% String selectedPublished = "";
						   String selectedDraft = "";
						   if (editedPage.isPublished()) {
							   selectedPublished = "selected";
						   }
						   else {
							   selectedDraft = "selected";
						   }
						%>
						<select name="isPublished" class="form-control">
							<option value="1" <%= selectedPublished %>>Published</option>
							<option value="0" <%= selectedDraft %>>Draft</option>
						</select>
					</div>
					<div class="form-group">
						<input type="hidden" name="type" value="update" />
						<input type="hidden" name="id" value="<%= editedPage.getId() %>" />
						<input type="submit" value="Update page" class="btn btn-primary form-control" />
					</div>
				</div>
				
			</div>
		</form>
	
	</div>
	
	
</div>


<script>
	$(document).ready(function() {
		$(".template-options").hide();
		var selectedTemplate = $("#templateSelect").val();
		var targetOptionsId = "#" + selectedTemplate + "Template";
		$(targetOptionsId).show();
	});
	
	$("#templateSelect").change(function() {
		$(".template-options").hide();
		var selectedTemplate = $("#templateSelect").val();
		var targetOptionsId = "#" + selectedTemplate + "Template";
		$(targetOptionsId).show();
	})
	
	$("input").change(function() {
		var inputName = $(this).attr("name");
		var value = $(this).val();
		var targetInput = "input[name=" + inputName + "]";
		$(targetInput).val(value);
	})
	
	$("#form").validate();
</script>

</body>
</html>