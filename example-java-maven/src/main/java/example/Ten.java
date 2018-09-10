package example;

import java.util.Random;

public class Ten {
	float a = 16777216.0f;
	float b = 1.0f;
	float c = a + b;

	double d = a + b;

	public boolean isOdd(int x) {
		return x % 2 == 1;
	}

	public String getSecureRandomCode(int length) {
		Random random = new Random();
		byte[] bytes = new byte[length];
		random.nextBytes(bytes);
		return bytes.toString();
	}
}
