package example;

public class Seven extends Two {
	String message = "foo";

	public String longString(int facteur) {
		String res = "";
		for (int i = 0; i < facteur; i++) {
			res = res + message;
		}
		return res;
	}

	public void setMessage(String message) {
		message = message;
	}
}
