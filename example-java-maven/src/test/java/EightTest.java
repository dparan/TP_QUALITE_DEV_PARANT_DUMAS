package example;

import static org.junit.Assert.*;

import org.junit.Test;

public class EightTest {

	Eight e = new Eight();
	
	@Test
	public void testConcat() {
		assertTrue(e.concat("abc", "def").equals("abc-def"));
	}

}
