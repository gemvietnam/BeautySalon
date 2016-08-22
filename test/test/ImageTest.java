package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.Image;

public class ImageTest {

	Image i;
	
	@Before
	public void setUp() throws Exception {
		i = new Image();
	}

	@Test
	public void testGetId() {
		i.setId(2);
		assertEquals(2, i.getId());
	}

	@Test
	public void testSetId() {
		i.setId(2);
		assertEquals(2, i.getId());
	}

	@Test
	public void testGetPath() {
		i.setPath("path");
		assertEquals("path", i.getPath());
	}

	@Test
	public void testSetPath() {
		i.setPath("path");
		assertEquals("path", i.getPath());
	}

}