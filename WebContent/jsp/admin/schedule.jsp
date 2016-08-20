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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<% List<Booking> bookings = (List<Booking>) request.getAttribute("bookings"); %>
<% String keyword = (String) request.getParameter("keyword"); %>

<div id="navigation">
	<%@include file="navigation.jsp" %>
</div>
<div id="content">

	<div id="content-bar">
		<div class="pull-right" style="margin-right: 50px;">
			<div class="row">
				<form method="post" action="admin?page=searchEmployees">
					<div class="col-md-8" style="padding: 0px;">
						<input type="text" class="form-control" name="keyword" placeholder="Search bookings" />
					</div>
					<div class="col-md-4" style="padding: 0px;">
						<input type="submit" class="form-control btn btn-primary" value="Search" />
					</div>
				</form>
			</div>
		</div>
		<h1>Schedule</h1>
	</div>
	
	
	
	<div class="app-data">
		
		
		
		<table id="test-table" class="scheduleTable table table-bordered">
			<thead>
				<tr>
					<td></td>
					<% 
						Calendar day = Calendar.getInstance();
						for (int i=0; i<7; i++) { 
							int dayInt = day.get(Calendar.DAY_OF_WEEK);
							String dayName = "";
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
					        String strDate = sdf.format(day.getTime());
							
							switch(dayInt){
							    case 2:
							    	dayName="Monday";
							        break;
							    case 3:
							    	dayName="Tuesday";
							        break;
							    case 4:
							    	dayName="Wednesday";
							        break;
							    case 5:
							    	dayName="Thursday";
							        break;
							    case 6:
							    	dayName="Friday";
							        break;
							    case 7:
							    	dayName="Saturday";
							        break;
							    case 1:
							    	dayName="Sunday";
							        break;
						    } %>
						    
							<td><%= dayName %> (<%= strDate %>)</td>
							
							<% day.add(Calendar.HOUR, 24); %>
					<% } %>
					
				</tr>
			</thead>
			<tbody>
				
				<% 
				Calendar timeSlot = new GregorianCalendar(2016,8,20,9,00,00);
				
				for (int i = 0; i < 17; i++) { 
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			        String strDate = sdf.format(timeSlot.getTime());
			        /* String fieldClass = "";
			        String name = "";
			        int rowspan = 1; */
				%>
					
					<tr>
						<td><%= strDate %></td>
						
						<%
						
						Calendar today = Calendar.getInstance();
						
						for (int j=0; j<7; j++) { 

								SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
						        String currentDate = simpleFormat.format(today.getTime());
						        
						        System.out.println("currentdate: " + currentDate);
								
						        String fieldClass = "";
						        String name = "";
						        int rowspan = 1;
						        
								for (Booking booking : bookings) {
									
									DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									String bookingDate = dateFormat.format(booking.getDate());
									System.out.println("bookingdate: " + bookingDate);
									
							
										if (bookingDate.equals(currentDate)) {
											
											System.out.println("DATES ARE FUCKING THE SAME");
											
											Time startTime = booking.getHour();
											int startTimeHours = startTime.getHours();
											int startTimeMinutes = startTime.getMinutes();
											Calendar startTimeCal = new GregorianCalendar(2016,8,20,startTimeHours,startTimeMinutes,00);
											
											Time durationTime = booking.getServiceDuration();
											int durationHours = durationTime.getHours();
											int durationMinutes = durationTime.getMinutes();
											
											Calendar endTimeCal = (Calendar) startTimeCal.clone();
											endTimeCal.add(Calendar.HOUR_OF_DAY, durationHours);
											endTimeCal.add(Calendar.MINUTE, durationMinutes);
											
											
											if (startTimeCal.equals(timeSlot) || (startTimeCal.before(timeSlot) && endTimeCal.after(timeSlot))) {
												System.out.println("ZIELONY");
												fieldClass = "booked";
												name = booking.getServiceName();
											}

										}
								}
								
								%>
								<td class="<%= fieldClass %>" ><%= name %> </td>
								<%
								
								today.add(Calendar.HOUR, 24);
							}
							%>	
						
<%-- 						<%
						for (int k=0; k<7; k++) { 
								 %>
						        
						        <td class="<%= fieldClass %>" rowspan="<%= rowspan %>"><%= name %></td>
						        
						<% } %> --%>
						
						
					</tr>
					
				<% 
					timeSlot.add(Calendar.MINUTE, 30);
				} %>
				
				
			</tbody>
		</table>
	
	</div>
	
</div>

<script src="/BeautySalon/assets/rowspanizer/jquery.rowspanizer.min.js"></script>

<script>
	$('.scheduleTable').rowspanizer({vertical_align: 'middle'});
</script>

</body>
</html>