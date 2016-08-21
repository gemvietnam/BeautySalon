package assets;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Helpers {
	
	public static String DateToString(Date date) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm");
		String stringDate = dateFormat.format(date);
		return stringDate;
	}
	
	public static String TimestampToString(Timestamp date) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm");
		String stringDate = dateFormat.format(date);
		return stringDate;
	}
	
}
