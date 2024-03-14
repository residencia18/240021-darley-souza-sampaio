package com.dansoft.redesocial.controller.dto;

import java.util.List;

import com.dansoft.redesocial.model.Usuario;

public class UsuarioDTO {
	private long id;
	private String nome;
	private String email;
	private List<Usuario> amigos;

	public UsuarioDTO(Usuario user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.amigos = user.getAmigos();
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

	public List<Usuario> getAmigos() {
		return amigos;
	}

}
