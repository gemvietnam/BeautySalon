package test;

import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.Before;
import org.junit.Test;

import models.Service;

public class ServiceTest {

	Service s;
	
	@Before
	public void setUp() throws Exception {
		s = new Service();
	}

	@Test
	public void testGetId() {
		s.setId(2);
		assertEquals(2, s.getId());
	}

	@Test
	public void testSetId() {
		s.setId(2);
		assertEquals(2, s.getId());
	}

	@Test
	public void testGetName() {
		s.setName("Jagoda");
		assertEquals("Jagoda", s.getName());
	}

	@Test
	public void testSetName() {
		s.setName("Jagoda");
		assertEquals("Jagoda", s.getName());
	}

	@Test
	public void testGetDescription() {
		s.setDescription("Description");
		assertEquals("Description", s.getDescription());
	}

	@Test
	public void testSetDescription() {
		s.setDescription("Description");
		assertEquals("Description", s.getDescription());
	}

	@Test
	public void testGetPrice() {
		s.setPrice(200);
		assertEquals(200, s.getPrice());
	}

	@Test
	public void testSetPrice() {
		s.setPrice(200);
		assertEquals(200, s.getPrice());
	}

	@Test
	public void testGetCategoryId() {
		s.setCategoryId(2);
		assertEquals(2, s.getCategoryId());
	}

	@Test
	public void testSetCategoryId() {
		s.setCategoryId(2);
		assertEquals(2, s.getCategoryId());
	}

	@Test
	public void testGetCategoryName() {
		s.setCategoryName("Category");
		assertEquals("Category", s.getCategoryName());
	}

	@Test
	public void testSetCategoryName() {
		s.setCategoryName("Category");
		assertEquals("Category", s.getCategoryName());
	}

	@Test
	public void testGetTime() {
		Time time = new Time(24274);
		s.setTime(time);
		assertEquals(time, s.getTime());
	}

	@Test
	public void testSetTime() {
		Time time = new Time(24274);
		s.setTime(time);
		assertEquals(time, s.getTime());
	}

}