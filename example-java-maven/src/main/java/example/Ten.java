package example;

import java.security.SecureRandom;
import java.util.Arrays;

public class Ten {
	double a = 16777216.0;
	double b = 1.0;
	double c = a + b;

	double d = a + b;

	public boolean isOdd(int x) {
		return x % 2 != 0;
	}

	public String getSecureRandomCode(int length) {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[length];
		random.nextBytes(bytes);
		return Arrays.toString(bytes);
	}
}
