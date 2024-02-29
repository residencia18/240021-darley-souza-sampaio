package com.dansoft.redesocial.controller.dto;

import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;

public class PostagemDTO {
	private Long id;
	private String codigo;
	private String texto;
	private Usuario usuario;

	public PostagemDTO(Postagem postagem) {
		this.id = postagem.getId();
		this.codigo = postagem.getCodigo();
		this.texto = postagem.getTexto();
		this.usuario = postagem.getUsuario();
	}

	public long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getTexto() {
		return texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
