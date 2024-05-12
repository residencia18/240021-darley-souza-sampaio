package com.dansoft.redesocial.controller.Form;

import com.dansoft.redesocial.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioForm {
	private String nome;
	private String email;

	public UsuarioForm() {
		super();
	}

	public UsuarioForm(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public Usuario toUsuario() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		return usuario;
	}

}
