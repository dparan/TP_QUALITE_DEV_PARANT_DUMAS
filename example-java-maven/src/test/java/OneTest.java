package example;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class OneTest {

	One one = new One()
			
	@Before
	public void setUp() {
		one = new One();
	}
	
	@Test
	public void testReturnMsg() {
		assertTrue(one.foo().equals("foo"));
	}

}
