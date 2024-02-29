package com.dansoft.redesocial.controller.Form;

import java.util.Random;

import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;

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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public static String gerarCodigoAleatorio() {
		StringBuilder codigo = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 11; i++) {
			codigo.append(random.nextInt(10));
		}

		return codigo.toString();
	}

	public Postagem toPostagem() {
		Postagem postagem = new Postagem();
		postagem.setCodigo(gerarCodigoAleatorio());
		postagem.setTexto(texto);
		postagem.setUsuario(usuario);
		return postagem;
	}
}
