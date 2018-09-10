package example;

public class Four {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isMessageEmpty() {
		return getMessage().isEmpty();
	}
}
