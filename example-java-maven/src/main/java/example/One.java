package example;


import java.util.logging.Logger;



public class One {
	String message = "foo";
	private static final Logger LOGGER = Logger.getLogger("One-Logger");
	


	public String foo() {
		return message;
	}

	/**
	 * Méthode de test
	 */
	public void unusedMethod() {
		LOGGER.info(message);
	} 
}
