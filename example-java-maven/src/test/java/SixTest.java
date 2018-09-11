package example;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class SixTest {

	Six six;
	
	@Before
	public void setUp() throws Exception {
		six = new Six();
	}

	/*
	 * Dans les 2 tests suivants, on utilise la classe Field pour ne pas utiliser 
	 * le getter pour tester le setter et inversement
	 */
	@Test
	public void testSetFileName() throws NoSuchFieldException, IllegalAccessException{
		six.setFileName("foo");
		final Field field = six.getClass().getDeclaredField("fileName");
		field.setAccessible(true);
		assertTrue(field.get(six).equals("foo"));
	}
	
	@Test
	public void testGetFileName() throws NoSuchFieldException, IllegalAccessException{
		final Field field = six.getClass().getDeclaredField("fileName");
		field.setAccessible(true);
		field.set(six, "foo");
		assertTrue(six.getFileName().equals("foo"));
	}

}
