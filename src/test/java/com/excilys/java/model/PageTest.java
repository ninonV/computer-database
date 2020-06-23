package com.excilys.java.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PageTest {
	
	Page pageMock = Mockito.mock(Page.class);
	
	private int firstLine = 1;
	private int currentPage = 5; 
	private int linesPage = 20;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNextPage() {
		pageMock.nextPage();
		//assertEquals(pageMock.get);
	}

	@Test
	public void testPreviousPage() {
		fail("Not yet implemented");
	}
}
