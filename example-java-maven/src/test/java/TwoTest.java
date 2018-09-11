package example;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class TwoTest {

	Object obj;
	One one;
	Two two;
	
	@Before
	public void setUp() {
		one = new One();
		two = new Two();
		
	}
	
	@Test
	public void testComp() {
		assertTrue(two.comp(one).equals("SUCCES"))
	}
}
