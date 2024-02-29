package com.dansoft.redesocial.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 16)
    private String nome;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "senha", nullable = false, length = 32)
    private String senha;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Postagem> postagens;
    
	public Usuario(String nome, String email, String password) {
		this.nome = nome;
		this.email = email;
		this.senha = password;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
}
