package example;

public enum Nine {
	OBJ_1(1, "Objet 1"), OBJ_2(2, "Objet 2");

	public int code;
	private String name;

	Nine(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public void setCode(int code) {
		this.code = code;
	}

	void setName(String name) {
		this.name = name;
	}
}
