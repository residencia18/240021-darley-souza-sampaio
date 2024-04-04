package com.dansoft.redesocial.controller.dto;
import com.dansoft.redesocial.model.Usuario;

import lombok.Getter;

@Getter
public class UsuarioDTO {
	private long id;
	private String nome;
	private String email;

	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
	}

}
