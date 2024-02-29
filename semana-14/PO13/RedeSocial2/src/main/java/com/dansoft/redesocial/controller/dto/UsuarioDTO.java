package com.dansoft.redesocial.controller.dto;


import com.dansoft.redesocial.model.Usuario;

public class UsuarioDTO {
	private long id;
	private String nome;
	private String email;
	
	public UsuarioDTO(Usuario user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
	}
	
	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
}
