package test;


import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.Before;
import org.junit.Test;

import models.Setting;

public class SettingTest {
	
	Setting s;

	@Before
	public void setUp() throws Exception {
		s = new Setting();
	}

	@Test
	public void testGetFacebook() {
		s.setFacebook("fb");
		assertEquals("fb", s.getFacebook());
	}

	@Test
	public void testSetFacebook() {
		s.setFacebook("fb");
		assertEquals("fb", s.getFacebook());
	}

	@Test
	public void testGetTwitter() {
		s.setTwitter("fb");
		assertEquals("fb", s.getTwitter());
	}

	@Test
	public void testSetTwitter() {
		s.setTwitter("fb");
		assertEquals("fb", s.getTwitter());
	}

	@Test
	public void testGetGooglePlus() {
		s.setGooglePlus("fb");
		assertEquals("fb", s.getGooglePlus());
	}

	@Test
	public void testSetGooglePlus() {
		s.setGooglePlus("fb");
		assertEquals("fb", s.getGooglePlus());
	}

	@Test
	public void testGetInstagram() {
		s.setInstagram("fb");
		assertEquals("fb", s.getInstagram());
	}

	@Test
	public void testSetInstagram() {
		s.setInstagram("fb");
		assertEquals("fb", s.getInstagram());
	}

	@Test
	public void testGetPinterest() {
		s.setPinterest("fb");
		assertEquals("fb", s.getPinterest());
	}

	@Test
	public void testSetPinterest() {
		s.setPinterest("fb");
		assertEquals("fb", s.getPinterest());
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
	public void testGetSiteTitle() {
		s.setSiteTitle("Title");
		assertEquals("Title", s.getSiteTitle());
	}

	@Test
	public void testSetSiteTitle() {
		s.setSiteTitle("Title");
		assertEquals("Title", s.getSiteTitle());
	}

	@Test
	public void testGetSiteDescription() {
		s.setSiteDescription("Description");
		assertEquals("Description", s.getSiteDescription());
	}

	@Test
	public void testSetSiteDescription() {
		s.setSiteDescription("Description");
		assertEquals("Description", s.getSiteDescription());
	}

	@Test
	public void testGetCompanyName() {
		s.setCompanyName("Description");
		assertEquals("Description", s.getCompanyName());
	}

	@Test
	public void testSetCompanyName() {
		s.setCompanyName("Description");
		assertEquals("Description", s.getCompanyName());
	}

	@Test
	public void testGetVatNumber() {
		s.setVatNumber("text");
		assertEquals("text", s.getVatNumber());
	}

	@Test
	public void testSetVatNumber() {
		s.setVatNumber("text");
		assertEquals("text", s.getVatNumber());
	}

	@Test
	public void testGetAddress() {
		s.setAddress("text");
		assertEquals("text", s.getAddress());
	}

	@Test
	public void testSetAddress() {
		s.setAddress("text");
		assertEquals("text", s.getAddress());
	}

	@Test
	public void testGetPhone() {
		s.setPhone("text");
		assertEquals("text", s.getPhone());
	}

	@Test
	public void testSetPhone() {
		s.setPhone("text");
		assertEquals("text", s.getPhone());
	}

	@Test
	public void testGetEmail() {
		s.setEmail("text");
		assertEquals("text", s.getEmail());
	}

	@Test
	public void testSetEmail() {
		s.setEmail("text");
		assertEquals("text", s.getEmail());
	}

	@Test
	public void testGetOpenMonday() {
		Time time = new Time(247828);
		s.setOpenMonday(time);
		assertEquals(time, s.getOpenMonday());
	}

	@Test
	public void testSetOpenMonday() {
		Time time = new Time(247828);
		s.setOpenMonday(time);
		assertEquals(time, s.getOpenMonday());
	}

}