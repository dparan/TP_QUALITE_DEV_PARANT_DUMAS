package example;

import java.security.SecureRandom;
import java.util.Arrays;

public class Ten {
	public static final int DEUX = 2;
	public static final double A = 16777216.0;
	double b = 1.0;
	double c = A + b;


	public boolean isOdd(int x) {
		return x % DEUX != 0;
	}

	public String getSecureRandomCode(int length) {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[length];
		random.nextBytes(bytes);
		return Arrays.toString(bytes);
	}
}
