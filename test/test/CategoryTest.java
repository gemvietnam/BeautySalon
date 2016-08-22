package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.Category;

public class CategoryTest {

	Category category;
	
	@Before
	public void setUp() throws Exception {
		category = new Category();
	}

	@Test
	public void testGetId() {
		category.setId(1);
		assertEquals(1, category.getId());
	}

	@Test
	public void testSetId() {
		category.setId(1);
		assertEquals(1, category.getId());
	}

	@Test
	public void testGetName() {
		category.setName("Jagoda");
		assertEquals("Jagoda", category.getName());
	}

	@Test
	public void testSetName() {
		category.setName("Jagoda");
		assertEquals("Jagoda", category.getName());
	}

	@Test
	public void testGetDescription() {
		category.setDescription("Przybyla");
		assertEquals("Przybyla", category.getDescription());
	}

	@Test
	public void testSetDescription() {
		category.setDescription("Przybyla");
		assertEquals("Przybyla", category.getDescription());
	}

	@Test
	public void testGetPicture() {
		category.setPicture("path");
		assertEquals("path", category.getPicture());
	}

	@Test
	public void testSetPicture() {
		category.setPicture("path");
		assertEquals("path", category.getPicture());
	}

}
