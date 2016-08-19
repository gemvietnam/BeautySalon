<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= getServletContext().getAttribute("siteTitle") %> - <%= getServletContext().getAttribute("siteDescription") %></title>
<%@include file="styles.jsp" %>
</head>
<body>

<%@include file="navigation.jsp" %>

<section id="treatments">
	<div class="wrapper">
		<div class="row">
			<div class="col-md-12">
				<h1>Contact</h1>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In auctor arcu in lobortis iaculis. Curabitur porta ex libero, et egestas felis volutpat a. Suspendisse ut est in est feugiat molestie. Pellentesque convallis neque sollicitudin, aliquam leo vitae, euismod lectus. Fusce varius diam dolor, eu molestie sapien porta a. Donec dignissim, ex non pulvinar luctus, dui lacus elementum nulla, ut consequat sapien ligula sed ligula. Donec mollis vehicula diam eget fermentum. Donec tempor nisi ac purus convallis viverra.

Donec quis massa ornare, ullamcorper leo eget, rhoncus nulla. Fusce sit amet sagittis risus, vel pharetra lectus. Sed nec facilisis elit. Nunc varius at metus a congue. Mauris mollis dui risus, id placerat ante convallis vel. Cras in turpis et magna tristique sollicitudin a id leo. Morbi dictum sodales nibh eget pretium. Maecenas dignissim mauris vitae aliquet euismod.

Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras egestas vehicula lectus vel pulvinar. Nulla luctus, lorem malesuada semper scelerisque, leo odio egestas enim, in auctor felis dui in lacus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nullam porttitor magna ut auctor fermentum. Maecenas at dolor vitae turpis dignissim varius id sed neque. Sed condimentum volutpat aliquet. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.

Quisque nunc ipsum, tempor ut feugiat ac, rutrum vitae neque. Donec a erat nec nulla luctus mollis ut vitae lacus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc placerat semper elit, at porttitor quam. Etiam lorem mi, tristique ac mattis faucibus, feugiat id libero. Nam at arcu sapien. Nulla ullamcorper faucibus rutrum. Aliquam in turpis feugiat quam faucibus pulvinar eu et libero. Quisque velit magna, elementum quis porttitor sit amet, lobortis sodales neque.</p>
			</div>
		</div>
	
	</div>
</section>

<%@include file="calltoaction.jsp" %>

<%@include file="footer.jsp" %>

</body>
</html>