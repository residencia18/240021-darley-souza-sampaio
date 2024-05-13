package com.dansoft.redesocial.controller.Form;

import com.dansoft.redesocial.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioForm {
	private String nome;

	public UsuarioForm() {
		super();
	}

	public UsuarioForm(String nome) {
		super();
		this.nome = nome;
	}

	public Usuario toUsuario() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		return usuario;
	}

}
