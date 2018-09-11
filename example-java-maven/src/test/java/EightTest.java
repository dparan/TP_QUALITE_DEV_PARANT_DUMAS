package example;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class EightTest {

	Eight eight;
	
	@Before
	public void setUp() {
		eight = new Eight();
	}
	
	@Test
	public void testConcat() {
		assertTrue(eight.concat("abc", "def").equals("abc-def"));
	}

}
