package example;

import java.util.logging.Logger;

/**
 * 
 * @author Julien
 *
 */
public class Three {
	String message = "foo";
	private static final Logger LOGGER = Logger.getLogger(Three.class.getName());

	/**
	 * 
	 * @param two
	 * @return les messages concaténés
	 */
	public String concat(Two two) {
		return message + two.message;
	}

	public String concat2(One one) {
		return message + one.foo();
	}

	public String concat3(One one, Two two) {
		StringBuilder sb = new StringBuilder(message);
		String res = sb.append('_').append(one.foo()).append('-').append(two.message).toString();
		LOGGER.info(res);
		return res;
	}
}
