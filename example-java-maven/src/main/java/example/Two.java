package example;

/**
 * 
 * @author Julien
 *
 */
public class Two {
	String message = "foo";

	/**
	 * 
	 * @param one
	 *            classe One
	 * @return "ERREUR" ou "SUCCES"
	 */
	public String comp(One one) {
		if (this.compareTo(one) == -1)
			return "ERREUR";
		return "SUCCES";
	}

	private int compareTo(One one) {
		return message.compareTo(one.foo());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.message.equals(obj);
	}
}
