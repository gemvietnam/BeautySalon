package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.EmployeeServices;

public class EmployeeServicesTest {

	EmployeeServices e;
	
	@Before
	public void setUp() throws Exception {
		e = new EmployeeServices();
	}

	@Test
	public void testGetEmployeeId() {
		e.setEmployeeId(2);
		assertEquals(2, e.getEmployeeId());
	}

	@Test
	public void testSetEmployeeId() {
		e.setEmployeeId(2);
		assertEquals(2, e.getEmployeeId());
	}

	@Test
	public void testGetServiceId() {
		e.setServiceId(2);
		assertEquals(2, e.getServiceId());
	}

	@Test
	public void testSetServiceId() {
		e.setServiceId(2);
		assertEquals(2, e.getServiceId());
	}

}
