package example;

public class Eight {
	public static One foo = new One();

	public String concat(String str1, String str2) {
		StringBuffer foo = new StringBuffer('x');
		foo.append(str1).append("-").append(str2);
		return foo.toString();
	}
}
