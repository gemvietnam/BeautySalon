package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;

import org.junit.Before;
import org.junit.Test;

import models.Booking;

public class BookingTest {

	Booking booking;
	
	@Before
	public void setUp() throws Exception {
		booking = new Booking();
	}

	@Test
	public void testGetIsActive() {
		booking.setIsActive(0);
		assertEquals(0, booking.getIsActive());
	}

	@Test
	public void testSetIsActive() {
		booking.setIsActive(0);
		assertEquals(0, booking.getIsActive());
	}

	@Test
	public void testGetServiceName() {
		booking.setServiceName("Treatment");
		assertEquals("Treatment", booking.getServiceName());
	}

	@Test
	public void testSetServiceName() {
		booking.setServiceName("Treatment");
		assertEquals("Treatment", booking.getServiceName());
	}

	@Test
	public void testGetEmployeeName() {
		booking.setEmployeeName("John Doe");
		assertEquals("John Doe", booking.getEmployeeName());
	}

	@Test
	public void testSetEmployeeName() {
		booking.setEmployeeName("John Doe");
		assertEquals("John Doe", booking.getEmployeeName());
	}

	@Test
	public void testGetServiceDuration() {
		Time time = new Time(1471861980);
		booking.setServiceDuration(time);
		assertEquals(time, booking.getServiceDuration());
	}

	@Test
	public void testSetServiceDuration() {
		Time time = new Time(1471861980);
		booking.setServiceDuration(time);
		assertEquals(time, booking.getServiceDuration());
	}

	@Test
	public void testGetServicePrice() {
		booking.setServicePrice(200);
		assertEquals(200, booking.getServicePrice());
	}

	@Test
	public void testSetServicePrice() {
		booking.setServicePrice(200);
		assertEquals(200, booking.getServicePrice());
	}

	@Test
	public void testGetId() {
		booking.setId(2);
		assertEquals(2, booking.getId());
	}

	@Test
	public void testSetId() {
		booking.setId(2);
		assertEquals(2, booking.getId());
	}

	@Test
	public void testGetDate() {
		Date date = new Date(1471861980);
		booking.setDate(date);
		assertEquals(date, booking.getDate());
	}

	@Test
	public void testSetDate() {
		Date date = new Date(1471861980);
		booking.setDate(date);
		assertEquals(date, booking.getDate());
	}

	@Test
	public void testGetHour() {
		Time time = new Time(1471861980);
		booking.setHour(time);
		assertEquals(time, booking.getHour());
	}

	@Test
	public void testSetHour() {
		Time time = new Time(1471861980);
		booking.setHour(time);
		assertEquals(time, booking.getHour());
	}

	@Test
	public void testGetEmployeeId() {
		booking.setEmployeeId(1);
		assertEquals(1, booking.getEmployeeId());
	}

	@Test
	public void testSetEmployeeId() {
		booking.setEmployeeId(1);
		assertEquals(1, booking.getEmployeeId());
	}

	@Test
	public void testGetServiceId() {
		booking.setServiceId(1);
		assertEquals(1, booking.getServiceId());
	}

	@Test
	public void testSetServiceId() {
		booking.setServiceId(1);
		assertEquals(1, booking.getServiceId());
	}

	@Test
	public void testGetFirstName() {
		booking.setFirstName("Jagoda");
		assertEquals("Jagoda", booking.getFirstName());
	}

	@Test
	public void testSetFirstName() {
		booking.setFirstName("Jagoda");
		assertEquals("Jagoda", booking.getFirstName());
	}

	@Test
	public void testGetLastName() {
		booking.setLastName("Przybyla");
		assertEquals("Przybyla", booking.getLastName());
	}

	@Test
	public void testSetLastName() {
		booking.setLastName("Przybyla");
		assertEquals("Przybyla", booking.getLastName());
	}

	@Test
	public void testGetEmail() {
		booking.setEmail("email@email.com");
		assertEquals("email@email.com", booking.getEmail());
	}

	@Test
	public void testSetEmail() {
		booking.setEmail("email@email.com");
		assertEquals("email@email.com", booking.getEmail());
	}

	@Test
	public void testGetPhone() {
		booking.setPhone("501239882");
		assertEquals("501239882", booking.getPhone());
	}

	@Test
	public void testSetPhone() {
		booking.setPhone("501239882");
		assertEquals("501239882", booking.getPhone());
	}

}
