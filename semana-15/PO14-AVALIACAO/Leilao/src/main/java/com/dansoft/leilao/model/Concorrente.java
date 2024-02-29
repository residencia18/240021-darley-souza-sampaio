package com.dansoft.leilao.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Concorrente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@Column(name = "cpf", nullable = false, length = 50)
	private String cpf;
//
//	@OneToMany(mappedBy = "concorrente", cascade = CascadeType.ALL)
//	private List<Lance> lances;

	public Concorrente(String nome, String cpf, List<Lance> lances) {
		super();
		this.nome = nome;
		this.cpf = cpf;
//		this.lances = lances;
	}

	public Concorrente() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public List<Lance> getLances() {
//		return lances;
//	}
//
//	public void setLances(List<Lance> lances) {
//		this.lances = lances;
//	}

}
