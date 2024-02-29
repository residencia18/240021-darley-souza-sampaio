package com.dansoft.formula1.controller.dto;

import com.dansoft.formula1.model.Formula1;

public class Formula1DTO {
	private String pais;
	private String piloto;
	private int vitorias;

	public Formula1DTO(Formula1 formula1) {
		this.pais = formula1.getPais();
		this.piloto = formula1.getPiloto();
		this.vitorias = formula1.getVitorias();
	}

	public String getPais() {
		return pais;
	}

	public String getPiloto() {
		return piloto;
	}

	public int getVitorias() {
		return vitorias;
	}

}
