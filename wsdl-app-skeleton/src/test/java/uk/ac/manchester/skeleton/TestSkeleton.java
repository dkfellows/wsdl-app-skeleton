package uk.ac.manchester.skeleton;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSkeleton {
	WebApplication webapp;

	@Before
	public void setUp() throws Exception {
		webapp = new WebApplication();
	}

	@After
	public void tearDown() throws Exception {
		// Not needed here
	}

	@Test
	public void testSayHello() {
		assertEquals("Hello, World!", webapp.sayHello("World"));
		assertEquals("Hello, there!", webapp.sayHello("there"));
	}
}
