package example;

import java.util.logging.Level;
import java.util.logging.Logger;



public class One {
	String message = "foo";
	private Logger log;

	public String foo() {
		return message;
	}

	/**
	 * Méthode de test
	 */
	public void unusedMethod() {
		log.log(Level.INFO,message);
	} 
}
