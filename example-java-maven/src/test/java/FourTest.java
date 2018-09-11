package example;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class FourTest {

	Four four;
	
	@Before
	public void setUp(){
		four = new Four();
	}

	/*
	 * Dans les 2 tests suivants, on utilise la classe Field pour ne pas utiliser 
	 * le getter pour tester le setter et inversement
	 */
	@Test
	public void testSetMsg() throws NoSuchFieldException, IllegalAccessException{
		four.setMessage("foo");
		final Field field = four.getClass().getDeclaredField("message");
		field.setAccessible(true);
		assertTrue(field.get(four).equals("foo"));
	}
	
	@Test
	public void testGetMsg() throws NoSuchFieldException, IllegalAccessException{
		final Field field = four.getClass().getDeclaredField("message");
		field.setAccessible(true);
		field.set(four, "foo");
		assertTrue(four.getMessage().equals("foo"));
	}

}
