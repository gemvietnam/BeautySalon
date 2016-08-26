package test;

import static org.junit.Assert.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class HelpersTest {

	@Test
	public final void testDateToString() {			
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, 8);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		java.util.Date date = calendar.getTime();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
		String dateString = assets.Helpers.DateToString(sqlDate);
		assertEquals(dateString, "2016-09-01 00:00");
	}

	@Test
	public final void testTimestampToString() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, 8);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		String dateString = assets.Helpers.TimestampToString(timestamp);
		assertEquals(dateString, "2016-09-01 00:00");
	}
	
	@Test
	public final void testTimeToHumanHour() {
		
		Time time = new Time(65236235);
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String stringDate = dateFormat.format(time);
		assertEquals(stringDate, "19:07");
	}
	
	@Test
	public final void DisplayIfNotNull() {
		String string = "";
		Boolean display;
		if (string != null) {
			display = true;
		}
		else {
			display = false;
		}
		assertEquals(display, true);
	}

}
