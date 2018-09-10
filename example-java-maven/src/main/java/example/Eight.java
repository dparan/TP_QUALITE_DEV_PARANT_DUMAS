package example;

public class Eight {
	public static final One foo = new One();

	public String concat(String str1, String str2) {
		StringBuilder sb = new StringBuilder("x");
		sb.append(str1).append("-").append(str2);
		return sb.toString();
	}
}
