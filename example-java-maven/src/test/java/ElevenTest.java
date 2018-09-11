package example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ElevenTest {

	Eleven eleven;
	
	@Before
	public void setUp() throws Exception {
		eleven = new Eleven();
	}

	@Test
	public void testTestValue() {
		assertTrue(eleven.testValue("CBA"));
		assertTrue(eleven.testValue("ABZEIFNCBA"));
		assertFalse(eleven.testValue("ABA"));
	}

}
