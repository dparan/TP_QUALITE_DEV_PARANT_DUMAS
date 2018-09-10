package example;

public class Eleven {

	public boolean testValue(String msg) {
		if (msg.equals("ABC") || msg.equals("DEF") || "XYZ".equals(msg)) {
			if (msg.endsWith("KLM") && msg.contains("AZE")) {
				return true;
			} else if (msg.endsWith("POI") && msg.contains("BGH")) {
				return true;
			} else if (msg.contains("TOP")) {
				return true;
			} else {
				return false;
			}
		} else if (msg.endsWith("CBA")) {
			return true;
		}
		return false;
	}

}
