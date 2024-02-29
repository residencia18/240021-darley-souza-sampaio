package com.dansoft.leilao.model;

import jakarta.persistence.*;

@Entity
public class Lance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "valor", nullable = false)
	private double valor;

	@ManyToOne
	@JoinColumn(name = "Concorrente_id", referencedColumnName = "id", nullable = false)
	private Concorrente concorrente;
	
	@ManyToOne
	@JoinColumn(name = "Leilao_id", referencedColumnName = "id", nullable = false)
	private Leilao leilao;

	public Lance(double valor, Leilao leilao, Concorrente concorrente) {
		super();
		this.valor = valor;
		this.leilao = leilao;
		this.concorrente = concorrente;
	}

	public Lance() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	public Concorrente getConcorrente() {
		return concorrente;
	}

	public void setConcorrente(Concorrente concorrente) {
		this.concorrente = concorrente;
	}

}
