<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Image"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>
<%@include file="styles.jsp" %>
<!-- Add fancyBox -->
<link rel="stylesheet" href="/BeautySalon/assets/fancybox/source/jquery.fancybox.css?v=2.1.5" type="text/css" media="screen" />
<script type="text/javascript" src="/BeautySalon/assets/fancybox/source/jquery.fancybox.pack.js?v=2.1.5"></script>

<script src="/BeautySalon/assets/masonry/masonry.pkgd.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>

</head>
<body>

<%@include file="navigation.jsp" %>

<% List<Image> images = (List<Image>) request.getAttribute("images"); %>
<% String url = (String) request.getAttribute("javax.servlet.forward.request_uri"); %>

<section id="treatments">
	<div class="wrapper">
		<div class="row">
			<div class="col-md-12">
				<h1>Gallery</h1>
				
				<div class="grid">
				<% for (Image image : images) { %>
					<div class="grid-item">
						<a class="fancybox" rel="group" href="/BeautySalon/uploads/<%= image.getPath() %>"><img src="/BeautySalon/uploads/<%= image.getPath() %>" alt="" /></a>
					</div>
				<% } %>
				</div>
				
			</div>
		</div>
	
	</div>
</section>

<%@include file="calltoaction.jsp" %>

<%@include file="footer.jsp" %>

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