<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Setting"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Settings - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>

<% Setting settings = (Setting) application.getAttribute("settings"); %>

<form method="post" action="admin?page=saveSettings" class="form-horizontal" enctype="multipart/form-data">
<div id="content">

	<div id="content-bar">
		<input type="submit" class="btn btn-primary pull-right" value="Save settings" />
		<h1>Settings</h1>
	</div>
	
	<div class="app-data">
	
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
	  <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">General</a></li>
	  <li role="presentation"><a href="#contact" aria-controls="contact" role="tab" data-toggle="tab">Contact information</a></li>
	  <li role="presentation"><a href="#socialmedia" aria-controls="socialmedia" role="tab" data-toggle="tab">Social media</a></li>
	  <li role="presentation"><a href="#openinghours" aria-controls="openinghours" role="tab" data-toggle="tab">Opening hours</a></li>
	</ul>
	
	<!-- Tab panes -->
	<div class="tab-content">
		  <div role="tabpanel" class="tab-pane active" id="home">
			  	<div class="form-group">
					<label class="col-sm-2 control-label">Site title</label>
					<div class="col-sm-10">
						<input type="text" name="siteTitle" class="form-control" placeholder="Site Title" value="<%= settings.getSiteTitle() %>" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Site description</label>
					<div class="col-sm-10">
						<textarea class="form-control" name="siteDescription" placeholder="The description that will appear in google search." rows="3"><%= settings.getSiteDescription() %></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Logo</label>
					<div class="col-sm-2">
						<div class="image-thumbnail" style="background-image: url('<%= Helpers.getBaseUrl(request) %>/uploads/settings/<%= settings.getLogo() %>')"></div>
					</div>
					<div class="col-sm-8">
						<input type="file" name="logo"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Favicon</label>
					<div class="col-sm-2">
						<div class="image-thumbnail favicon" style="background-image: url('<%= Helpers.getBaseUrl(request) %>/uploads/settings/<%= settings.getFavicon() %>')"></div>
					</div>
					<div class="col-sm-8">
						<input type="file" name="favicon"/>
					</div>
				</div>
		  </div>
		  <div role="tabpanel" class="tab-pane" id="contact">
			  	<div class="form-group">
					<label class="col-sm-2 control-label">Company name</label>
					<div class="col-sm-10">
						<input type="text" name="companyName" class="form-control" placeholder="Company name" value="<%= settings.getCompanyName() %>" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">CVR number</label>
					<div class="col-sm-10">
						<input type="text" name="vatNumber" class="form-control" placeholder="CVR number" value="<%= settings.getVatNumber() %>" />
					</div>
				</div>						
				<div class="form-group">
					<label class="col-sm-2 control-label">Address</label>
					<div class="col-sm-10">
						<input type="text" name="address" class="form-control" placeholder="Your address" value="<%= settings.getAddress() %>" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Phone number</label>
					<div class="col-sm-10">
						<input type="text" name="phone" class="form-control" placeholder="Phone numbre" value="<%= settings.getPhone() %>" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Email address</label>
					<div class="col-sm-10">
						<input type="text" name="email" class="form-control" placeholder="Email address" value="<%= settings.getEmail() %>" />
					</div>
				</div>
		  </div>
		  <div role="tabpanel" class="tab-pane" id="socialmedia">
				  <div class="form-group">
						<label class="col-sm-2 control-label">Facebook</label>
						<div class="col-sm-10">
							<input type="url" name="facebook" class="form-control" placeholder="Facebook profile link" value="<%= settings.getFacebook() %>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Twitter</label>
						<div class="col-sm-10">
							<input type="url" name="twitter" class="form-control" placeholder="Twitter profile link" value="<%= settings.getTwitter() %>" />
						</div>
					</div>						
					<div class="form-group">
						<label class="col-sm-2 control-label">Google+</label>
						<div class="col-sm-10">
							<input type="url" name="googlePlus" class="form-control" placeholder="Google+ profile link" value="<%= settings.getGooglePlus() %>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Instagram</label>
						<div class="col-sm-10">
							<input type="url" name="instagram" class="form-control" placeholder="Instagram profile link" value="<%= settings.getInstagram() %>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Pinterest</label>
						<div class="col-sm-10">
							<input type="url" name="pinterest" class="form-control" placeholder="Pinterest profile link" value="<%= settings.getPinterest() %>" />
						</div>
					</div>
		  </div>
		  <div role="tabpanel" class="tab-pane" id="openinghours">
			  <div class="form-group">
					<label class="col-sm-2 control-label">Monday</label>
					<div class="col-sm-10">
						<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Tuesday</label>
					<div class="col-sm-10">
						<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Wednesday</label>
					<div class="col-sm-10">
						<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Thursday</label>
					<div class="col-sm-10">
						<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Friday</label>
					<div class="col-sm-10">
						<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Saturday</label>
					<div class="col-sm-10">
						<input type="time" value="09:00:00" /> - <input type="time" value="14:00:00" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Sunday</label>
					<div class="col-sm-10">
						<input type="time" value="09:00:00" /> - <input type="time" value="14:00:00" />
					</div>
				</div>
		  </div>
	</div>
	
<%-- 		<form method="post" action="admin?page=saveSettings" class="form-horizontal">
			<h3>General settings</h3>
			<div class="form-group">
				<label class="col-sm-2 control-label">Site title</label>
				<div class="col-sm-10">
					<input type="text" name="siteTitle" class="form-control" placeholder="Site Title" value="<%= settings.getSiteTitle() %>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Site description</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="siteDescription" placeholder="The description that will appear in google search." rows="3"><%= settings.getSiteDescription() %></textarea>
				</div>
			</div>
			
			<h3>Contact information</h3>
			<div class="form-group">
				<label class="col-sm-2 control-label">Company name</label>
				<div class="col-sm-10">
					<input type="text" name="companyName" class="form-control" placeholder="Company name" value="<%= settings.getCompanyName() %>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">CVR number</label>
				<div class="col-sm-10">
					<input type="text" name="vatNumber" class="form-control" placeholder="CVR number" value="<%= settings.getVatNumber() %>" />
				</div>
			</div>						
			<div class="form-group">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-10">
					<input type="text" name="address" class="form-control" placeholder="Your address" value="<%= settings.getAddress() %>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Phone number</label>
				<div class="col-sm-10">
					<input type="text" name="phone" class="form-control" placeholder="Phone numbre" value="<%= settings.getPhone() %>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Email address</label>
				<div class="col-sm-10">
					<input type="text" name="email" class="form-control" placeholder="Email address" value="<%= settings.getEmail() %>" />
				</div>
			</div>
			
			
			<h3>Social media</h3>
			<div class="form-group">
				<label class="col-sm-2 control-label">Facebook</label>
				<div class="col-sm-10">
					<input type="url" name="facebook" class="form-control" placeholder="Facebook profile link" value="<%= settings.getFacebook() %>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Twitter</label>
				<div class="col-sm-10">
					<input type="url" name="twitter" class="form-control" placeholder="Twitter profile link" value="<%= settings.getTwitter() %>" />
				</div>
			</div>						
			<div class="form-group">
				<label class="col-sm-2 control-label">Google+</label>
				<div class="col-sm-10">
					<input type="url" name="googlePlus" class="form-control" placeholder="Google+ profile link" value="<%= settings.getGooglePlus() %>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Instagram</label>
				<div class="col-sm-10">
					<input type="url" name="instagram" class="form-control" placeholder="Instagram profile link" value="<%= settings.getInstagram() %>" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Pinterest</label>
				<div class="col-sm-10">
					<input type="url" name="pinterest" class="form-control" placeholder="Pinterest profile link" value="<%= settings.getPinterest() %>" />
				</div>
			</div>

			
			
			<h3>Opening hours</h3>
			<div class="form-group">
				<label class="col-sm-2 control-label">Monday</label>
				<div class="col-sm-10">
					<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Tuesday</label>
				<div class="col-sm-10">
					<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Wednesday</label>
				<div class="col-sm-10">
					<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Thursday</label>
				<div class="col-sm-10">
					<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Friday</label>
				<div class="col-sm-10">
					<input type="time" value="09:00:00" /> - <input type="time" value="18:00:00" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Saturday</label>
				<div class="col-sm-10">
					<input type="time" value="09:00:00" /> - <input type="time" value="14:00:00" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Sunday</label>
				<div class="col-sm-10">
					<input type="time" value="09:00:00" /> - <input type="time" value="14:00:00" />
				</div>
			</div>
			<input type="submit" class="btn btn-primary pull-right" value="Save settings" />
		</form> --%>
		
	</div>
	
	
</div>
</form>


<script>
	$("#myTabs a").click(function(e) {
		e.preventDefault();
		$(this).tab("show");
	})
</script>

</body>
</html>