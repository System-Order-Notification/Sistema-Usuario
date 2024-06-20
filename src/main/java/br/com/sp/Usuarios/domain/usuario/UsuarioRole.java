package br.com.sp.Usuarios.domain.usuario;

public enum UsuarioRole {
	ADMIN(1, "Admin"),
	USER(2, "Usuário");
	
	private Integer id;
	private String name;
	
	private UsuarioRole(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
