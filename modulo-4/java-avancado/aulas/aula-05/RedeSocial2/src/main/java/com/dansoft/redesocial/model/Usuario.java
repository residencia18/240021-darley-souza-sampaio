package com.dansoft.redesocial.model;

import java.util.List;

import com.dansoft.redesocial.validations.Validations;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Usuario {

	private static Validations validations = new Validations();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@Column(name = "email", length = 50, unique = true)
	private String email;

	@Column(name = "senha", nullable = false, length = 32)
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Postagem> postagens;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "amigos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "amigo_id"))
	private List<Usuario> amigos;

	public Usuario(String nome, String email, String password, List<Usuario> amigos) {
		this.nome = nome;
		this.email = email;
		this.senha = password;
		this.amigos = amigos;
	}

	public Usuario() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		if (nome == null)
			throw new Exception("Erro: Nome não deve ser nulo.");
		if (!validations.nameValidation(nome))
			throw new Exception("Erro: Nome inválido.");
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if (email == null)
			throw new Exception("Erro: Email não deve ser nulo.");
		if (!validations.emailValidation(email))
			throw new Exception("Erro: Email inválido.");
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws Exception {
		if (senha == null)
			throw new Exception("Erro: Senha não deve ser nula.");
		if (!validations.keyValidation(senha))
			throw new Exception("Erro: Senha inválida.");
		this.senha = senha;
	}

	public List<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Usuario> amigos) throws Exception {
		if (amigos == null)
			throw new Exception("Erro: Lista de amigos não deve ser nula");
		this.amigos = amigos;
	}

}