package example;

public class Seven extends Two {
	String message2 = "foo";

	public String longString(int facteur) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < facteur; i++) {
			res.append(message2);
		}
		return res.toString();
	}

	public void setMessage2(String message) {
		message2 = message;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + ((message2 == null) ? 0 : message2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return message2.equals(obj);
	}
}
