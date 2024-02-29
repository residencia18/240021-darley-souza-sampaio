package com.dansoft.leilao.controller.dto;

import com.dansoft.leilao.model.Leilao;

public class LeilaoDTO {
	private Long id;
	private String descricao;
	private double valor_minimo;
	private int status;

	public LeilaoDTO(Leilao leilao) {
		this.id = leilao.getId();
		this.descricao = leilao.getDescricao();
		this.valor_minimo = leilao.getValor_minimo();
		this.status = leilao.getStatus();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor_minimo() {
		return valor_minimo;
	}

	public int getStatus() {
		return status;
	}

}
