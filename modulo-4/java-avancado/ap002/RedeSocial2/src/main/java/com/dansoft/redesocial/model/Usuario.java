package com.dansoft.redesocial.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 50)
	@NotEmpty(message = "O nome não deve ser vazio")
	@NotNull(message = "O nome não deve ser nulo")
	private String nome;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Postagem> postagens = new ArrayList<Postagem>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "amigos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "amigo_id"))
	private List<Usuario> amigos = new ArrayList<Usuario>();

	public Usuario(String nome, List<Usuario> amigos) {
		this.nome = nome;
		this.amigos = amigos;
	}

	public Usuario() {

	}

}