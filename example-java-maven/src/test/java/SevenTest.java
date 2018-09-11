package example;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class SevenTest {

	Seven seven;
	
	@Before
	public void setUp() throws Exception {
		seven = new Seven();
	}

	@Test
	public void testLongString() {
		assertTrue(seven.longString(3).equals("foofoofoo"));
	}
	
	@Test
	public void testSetMessage2() throws NoSuchFieldException, IllegalAccessException{
		seven.setMessage2("foo");
		final Field field = seven.getClass().getDeclaredField("message2");
		field.setAccessible(true);
		assertTrue(field.get(seven).equals("foo"));
	}

}
