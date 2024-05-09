package com.dansoft.redesocial.controller.Form;

import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemForm {
	private String texto;
	private Usuario usuario;

	public PostagemForm() {
		super();
	}

	public PostagemForm(String codigo, String texto, Usuario usuario) {
		super();
		this.texto = texto;
		this.usuario = usuario;
	}

	public Postagem toPostagem() throws Exception {
		Postagem postagem = new Postagem();
		postagem.setCodigo(Postagem.gerarCodigoAleatorio());
		postagem.setTexto(texto);
		postagem.setUsuario(usuario);
		return postagem;
	}
}
