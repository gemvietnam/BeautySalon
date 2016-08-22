package assets;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	
	public static boolean ServiceHasEmployees(int serviceId) {
		
		BeautyDAO beautyDAO = new BeautyDAOImpl();
		return beautyDAO.serviceHasEmployees(serviceId);
	}
	
}
