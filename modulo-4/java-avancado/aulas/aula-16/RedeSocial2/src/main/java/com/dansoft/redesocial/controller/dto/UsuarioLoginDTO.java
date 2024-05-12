package com.dansoft.redesocial.controller.dto;

import com.dansoft.redesocial.model.UsuarioLogin;
import com.dansoft.redesocial.model.UsuarioLoginRole;

import lombok.Getter;

@Getter
public class UsuarioLoginDTO {
	private Long id;
	private String login;
	private String senha;
	private UsuarioLoginRole role;

	public UsuarioLoginDTO() {

	}

	public UsuarioLoginDTO(UsuarioLogin user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.senha = user.getSenha();
		this.role = user.getRole();
	}



}
