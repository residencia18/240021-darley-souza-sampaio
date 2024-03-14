package com.dansoft.redesocial.controller.Form;

import com.dansoft.redesocial.model.Usuario;

public class UsuarioForm {
	private String nome;
	private String email;
	private String senha;
	
	public UsuarioForm() {
		super();
	}

	public UsuarioForm(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws Exception {
		this.senha = senha;
	}
	
	public Usuario toUsuario() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		return usuario;
	}
 
}
