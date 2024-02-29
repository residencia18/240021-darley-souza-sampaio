package com.dansoft.leilao.controller.dto;

import com.dansoft.leilao.model.Concorrente;
import com.dansoft.leilao.model.Lance;
import com.dansoft.leilao.model.Leilao;

public class LanceDTO {
	private Long id;
	private Concorrente concorrente;
	private Leilao leilao;
	private double valor;

	public LanceDTO(Lance lance) {
		this.id = lance.getId();
		this.concorrente = lance.getConcorrente();
		this.leilao = lance.getLeilao();
		this.valor = lance.getValor();
	}

	public Long getId() {
		return id;
	}

	public Concorrente getConcorrente() {
		return concorrente;
	}

	public Leilao getLeilao() {
		return leilao;
	}

	public double getValor() {
		return valor;
	}

}
