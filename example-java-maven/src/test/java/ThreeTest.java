package example;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class ThreeTest {

	One one;
	Two two;
	Three three;
	
	@Before
	public void setUp() {
		one = new One();
		two = new Two();
		three = new Three();
		
	}
	
	@Test
	public void testConcat1() {
		assertTrue(three.concat(two).equals("foofoo"));
	}
	
	@Test
	public void testConcat2() {
		assertTrue(three.concat2(one).equals("foofoo"));
	}
	
	@Test
	public void testConcat3() {
		assertTrue(three.concat3(one, two).equals("foo_foo-foo"));
	}
}
