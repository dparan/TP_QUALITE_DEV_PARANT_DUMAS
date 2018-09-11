package example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TenTest {

	Ten ten;
	
	@Before
	public void setUp() throws Exception {
		ten = new Ten();
	}

	@Test
	public void testIsOdd() {
		assertTrue(ten.isOdd(5));
		assertTrue(ten.isOdd(1));
		assertFalse(ten.isOdd(0));
		assertFalse(ten.isOdd(4));
	}
	
	@Test
	public void testGetSecureRandomCode() {
		assertFalse(ten.getSecureRandomCode(10).equals(ten.getSecureRandomCode(10)));
	}

}
