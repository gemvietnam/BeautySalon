package test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import models.Page;

public class PageTest {

	Page p;
	
	@Before
	public void setUp() throws Exception {
		p = new Page();
	}

	@Test
	public void testGetCreated() {
		Timestamp timestamp = new Timestamp(27487878);
		p.setCreated(timestamp);
		assertEquals(timestamp, p.getCreated());
	}

	@Test
	public void testSetCreated() {
		Timestamp timestamp = new Timestamp(27487878);
		p.setCreated(timestamp);
		assertEquals(timestamp, p.getCreated());
	}

	@Test
	public void testGetIsPublished() {
		p.setIsPublished(1);
		assertEquals(1, p.getIsPublished());
	}

	@Test
	public void testSetIsPublished() {
		p.setIsPublished(1);
		assertEquals(1, p.getIsPublished());
	}

	@Test
	public void testGetId() {
		p.setId(1);
		assertEquals(1, p.getId());
	}

	@Test
	public void testSetId() {
		p.setId(1);
		assertEquals(1, p.getId());
	}

	@Test
	public void testGetSlug() {
		p.setSlug("slug");
		assertEquals("slug", p.getSlug());
	}

	@Test
	public void testSetSlug() {
		p.setSlug("slug");
		assertEquals("slug", p.getSlug());
	}

	@Test
	public void testGetTitle() {
		p.setTitle("title");
		assertEquals("title", p.getTitle());
	}

	@Test
	public void testSetTitle() {
		p.setTitle("title");
		assertEquals("title", p.getTitle());
	}

	@Test
	public void testGetContent() {
		p.setContent("content");
		assertEquals("content", p.getContent());
	}

	@Test
	public void testSetContent() {
		p.setContent("content");
		assertEquals("content", p.getContent());
	}

	@Test
	public void testIsPublished() {
		p.setIsPublished(1);
		assertEquals(true, p.isPublished());
	}

}