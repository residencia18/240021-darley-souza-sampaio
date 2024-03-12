package com.dansoft.redesocial.controller.Form;

import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.validations.Validations;

public class UsuarioForm {
	private String nome;
	private String email;
	private String senha;


	private static Validations validations = new Validations();
	
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
		if(!validations.emailValidation(email))
			throw new Exception("Email inválido.");
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws Exception {
		if(!validations.keyValidation(senha))
			throw new Exception("Senha inválida.");
		this.senha = senha;
	}
	
	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		return usuario;
	}

}
