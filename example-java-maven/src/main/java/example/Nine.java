package example;

public enum Nine {
	OBJ_1(1, "Objet 1"), OBJ_2(2, "Objet 2");

	private int code;
	private String name;

	Nine(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/* la méthode setName est inutile car les valeurs des attributs d'un enum n'ont pas vocation à être modifiées
}
