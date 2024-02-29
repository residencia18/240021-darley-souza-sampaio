package com.dansoft.leilao.model;
import jakarta.persistence.*;

@Entity
public class Leilao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", nullable = false, length = 255)
	private String descricao;

	@Column(name = "valor_minimo", nullable = false)
	private double valor_minimo;

	@Column(name = "status", nullable = false)
	private int status;

	public Leilao(Long id, String descricao, double valor_minimo, int status) {
		this.descricao = descricao;
		this.valor_minimo = valor_minimo;
		this.status = status;
	}

	public Leilao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor_minimo() {
		return valor_minimo;
	}

	public void setValor_minimo(double valor_minimo) {
		this.valor_minimo = valor_minimo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
