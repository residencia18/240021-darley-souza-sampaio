package com.dansoft.redesocial.controller.dto;

import java.util.Date;

import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;

import lombok.Getter;

@Getter
public class PostagemDTO {
	private Long id;
	private String codigo;
	private String texto;
	private Usuario usuario;
	private Date dataPostagem;

	public PostagemDTO(Postagem postagem) {
		this.id = postagem.getId();
		this.codigo = postagem.getCodigo();
		this.texto = postagem.getTexto();
		this.usuario = postagem.getUsuario();
		this.dataPostagem = postagem.getDataPostagem();
	}
}
