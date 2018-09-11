package example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FiveTest {

	Five five;
	
	@Before
	public void setUp() throws Exception {
		five = new Five();
	}

	@Test
	public void testIntToStr() {
		assertTrue(five.intToString(1).equals("1"));
	}
	
	@Test
	public void testIntToStr3() {
		assertTrue(five.intToString3(1).equals("1"));
	}

}
