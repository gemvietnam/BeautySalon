package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;
import models.Booking;
import models.Category;
import models.Employee;
import models.Service;

/**
 * Servlet implementation class BookingController
 */
@WebServlet("/booking")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/jsp/booking.jsp";
		String action = request.getParameter("page");
		
		System.out.println("ACTION IS: " + action);
		
		if (action != null && action.equals("ajax")) {
			
			System.out.println("Getting into ajax actionset");
			String dateString = request.getParameter("date");
			String durationString = request.getParameter("duration");
			Time serviceDuration = Time.valueOf(durationString);	
			
			Date date = Date.valueOf(dateString);
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			System.out.println("GOT AJAX DATA" + date + employeeId);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Time[]> bookedTimes = beautyDAO.getAvailableHoursByDateAndEmployee(date, employeeId);
			response.setContentType("text/plain");
			
			Calendar availableTime = new GregorianCalendar(2016,8,20,9,00,00);
		
			for (int i = 0; i < 17; i++) { 
			
				String buttonClass = "btn-success";
				
				for (Time[] time : bookedTimes) { 
					Time startTime = time[0];
					
					int startTimeHours = startTime.getHours();
					int startTimeMinutes = startTime.getMinutes();
					Calendar bookedTime = new GregorianCalendar(2016,8,20,startTimeHours,startTimeMinutes,00);
					
					Time duration = time[1];
					int durationHours = duration.getHours();
					int durationMinutes = duration.getMinutes();
					
					Calendar bookedTimeEnd = new GregorianCalendar(2016,8,20,startTimeHours,startTimeMinutes,00);
					bookedTimeEnd.add(Calendar.HOUR_OF_DAY, durationHours);
					bookedTimeEnd.add(Calendar.MINUTE, durationMinutes);

					// CALCULATE POTENTIAL BOOKING END TIME
					
					int serviceDurationHours = serviceDuration.getHours();
					int serviceDurationMinutes = serviceDuration.getMinutes();

					Calendar potentialEndTime = (Calendar) availableTime.clone();
					potentialEndTime.add(Calendar.HOUR_OF_DAY, serviceDurationHours);	
					potentialEndTime.add(Calendar.MINUTE, serviceDurationMinutes);	
					
					if (availableTime.after(bookedTime) && availableTime.before(bookedTimeEnd)) {
						buttonClass = "btn-danger disabled";
					}
					else if (availableTime.equals(bookedTime) || availableTime.equals(bookedTimeEnd)) {
						buttonClass = "btn-danger disabled";
					}
					else if (potentialEndTime.after(bookedTime) && availableTime.before(bookedTimeEnd)) {
						buttonClass = "btn-danger disabled";
					}
				}
				
				
				int myHour = availableTime.get(Calendar.HOUR_OF_DAY);
				String myMinute;
				if (availableTime.get(Calendar.MINUTE) == 0) { 
					myMinute = "00";
				} 
				else { 
					myMinute = String.valueOf(availableTime.get(Calendar.MINUTE));
				}
				
				response.getWriter().write("<a class='btn " + buttonClass + "' role='button' data-hour='"+myHour+":"+myMinute+":00'>"+ myHour +":"+ myMinute +"</a>");
				
				availableTime.add(Calendar.MINUTE, 30);	
			}
			
		}
		
		else {
		
			System.out.println("Displaying booking page");
			if (action != null) {
				switch (action) {
				case "success":
					url = "/jsp/booking-success.jsp";
					addBooking(request, response);
					break;
				}
			}
			
			System.out.println("Getting employees for service");
			getEmployeesByServiceId(request, response);
			
			System.out.println("Getting available hours");
			getAvailableHoursByDateAndEmployee(request, response);
			
			System.out.println("Finished");
			
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}

	private void getEmployeesByServiceId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int serviceId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Employee> employees = beautyDAO.getEmployeesByServiceId(serviceId);
			Service service = beautyDAO.getServiceById(serviceId);
			
			request.setAttribute("employees", employees);
			request.setAttribute("service", service);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void getAvailableHoursByDateAndEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Inside getting hours function");
			Date date = java.sql.Date.valueOf("2016-08-21");
			int employeeId = 2;
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Time[]> bookedTimes = beautyDAO.getAvailableHoursByDateAndEmployee(date, employeeId);
			request.setAttribute("bookedTimes", bookedTimes);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void addBooking(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			
			Booking b = new Booking();
			b.setFirstName(request.getParameter("firstName"));
			b.setLastName(request.getParameter("lastName"));
			b.setEmail(request.getParameter("email"));
			b.setPhone(request.getParameter("phone"));

			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			b.setEmployeeId(employeeId);
			int serviceId = Integer.parseInt(request.getParameter("serviceId"));
			b.setServiceId(serviceId);
			
			Time hour = Time.valueOf(time);
			b.setHour(hour);
			
			Date dateConverted = Date.valueOf(date);
			b.setDate(dateConverted);
			
			Timestamp created = new Timestamp(System.currentTimeMillis());
			b.setCreated(created);
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Booking addedBookingData = beautyDAO.addBooking(b);
			Service bookedServiceData = beautyDAO.getServiceById(serviceId);			
			request.setAttribute("addedBooking", addedBookingData);
			request.setAttribute("bookedService", bookedServiceData);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
}
