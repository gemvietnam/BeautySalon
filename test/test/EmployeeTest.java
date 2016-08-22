package test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.Employee;

public class EmployeeTest {

	Employee employee;
	
	@Before
	public void setUp() throws Exception {
		employee = new Employee();
	}

	@Test
	public void testGetId() {
		employee.setId(2);
		assertEquals(2, employee.getId());
	}

	@Test
	public void testSetId() {
		employee.setId(2);
		assertEquals(2, employee.getId());
	}

	@Test
	public void testGetEmail() {
		employee.setEmail("admin@admin.com");
		assertEquals("admin@admin.com", employee.getEmail());
	}

	@Test
	public void testSetEmail() {
		employee.setEmail("admin@admin.com");
		assertEquals("admin@admin.com", employee.getEmail());
	}

	@Test
	public void testGetFirstName() {
		employee.setFirstName("Jagoda");
		assertEquals("Jagoda", employee.getFirstName());
	}

	@Test
	public void testSetFirstName() {
		employee.setFirstName("Jagoda");
		assertEquals("Jagoda", employee.getFirstName());
	}

	@Test
	public void testGetLastName() {
		employee.setLastName("Przybyla");
		assertEquals("Przybyla", employee.getLastName());
	}

	@Test
	public void testSetLastName() {
		employee.setLastName("Przybyla");
		assertEquals("Przybyla", employee.getLastName());
	}

	@Test
	public void testGetTitle() {
		employee.setTitle("title");
		assertEquals("title", employee.getTitle());
	}

	@Test
	public void testSetTitle() {
		employee.setTitle("title");
		assertEquals("title", employee.getTitle());
	}

	@Test
	public void testGetDescription() {
		employee.setDescription("description");
		assertEquals("description", employee.getDescription());
	}

	@Test
	public void testSetDescription() {
		employee.setDescription("description");
		assertEquals("description", employee.getDescription());
	}

	@Test
	public void testGetProfilePicture() {
		employee.setProfilePicture("path");
		assertEquals("path", employee.getProfilePicture());
	}

	@Test
	public void testSetProfilePicture() {
		employee.setProfilePicture("path");
		assertEquals("path", employee.getProfilePicture());
	}

}
