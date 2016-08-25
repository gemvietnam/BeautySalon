package assets;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;

public class Helpers {
	
	public static String DateToString(Date date) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String stringDate = dateFormat.format(date);
		return stringDate;
	}
	
	public static String TimestampToString(Timestamp date) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String stringDate = dateFormat.format(date);
		return stringDate;
	}
	
	public static String TimeToHumanHour(Timestamp time) {
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String stringDate = dateFormat.format(time);
		return stringDate;
	}
	
	public static String TimeToHour(Time time) {
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String stringDate = dateFormat.format(time);
		return stringDate;
	}
	
	public static boolean ServiceHasEmployees(int serviceId) {
		
		BeautyDAO beautyDAO = new BeautyDAOImpl();
		return beautyDAO.serviceHasEmployees(serviceId);
	}
	
	public static String getBaseUrl( HttpServletRequest request ) {
	    if ( ( request.getServerPort() == 80 ) ||
	         ( request.getServerPort() == 443 ) )
	      return request.getScheme() + "://" +
	             request.getServerName() +
	             request.getContextPath();
	    else
	      return request.getScheme() + "://" +
	             request.getServerName() + ":" + request.getServerPort() +
	             request.getContextPath();
	}

	
}
