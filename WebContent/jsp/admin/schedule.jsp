<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Booking"%>
<%@page import="models.Employee"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Schedule - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<% List<Booking> bookings = (List<Booking>) request.getAttribute("bookings"); %>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
<% String keyword = (String) request.getParameter("keyword"); %>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>
<div id="content">

	<div id="content-bar">
		<div class="pull-right" style="min-width: 400px;">
			<div class="row">
				<div class="col-md-4">
					<label>Employee:</label>
				</div>
				<div class="col-md-8">
					<select id="employeeId" name="employeeId" class="form-control">
						<% for (Employee employee : employees) { %>
							<option value="<%= employee.getId() %>"><%= employee.getFirstName() %> <%= employee.getLastName() %></option> 
						<% } %>
					</select>
				</div>
			</div>
		</div>
		<h1>Schedule</h1>
	</div>

	<div class="app-data">
	
		<%-- <label>Select employee:</label>
		<div class="row">
			<div class="col-md-4">
				<select id="employeeId" name="employeeId" class="form-control">
					<% for (Employee employee : employees) { %>
						<option value="<%= employee.getId() %>"><%= employee.getFirstName() %> <%= employee.getLastName() %></option> 
					<% } %>
				</select>
			</div>
		</div> --%>
		
		
		<div id="responseText">
		
		</div>
	
	</div>
	
</div>

<script>
	
	$(document).ready(function() {
	
		getData();

		$('#employeeId').on("change", function() {
			getData();
		}); 
		
		function getData() {
			var employeeId = $('#employeeId').val();

			console.log("Get data for employee: #" + employeeId);
			
			$.ajax({
				url: 'admin',
				method: 'post', 
				data: {'page':'schedule', 'employeeId': employeeId},
				success: function(responseText) {
					console.log("hejka");
					$('#responseText').html(responseText);
				}
			});
		}
	});	

</script>

<script src="/BeautySalon/assets/rowspanizer/jquery.rowspanizer.min.js"></script>

<script>
	$('.scheduleTable').rowspanizer({vertical_align: 'middle'});
</script>

</body>
</html>