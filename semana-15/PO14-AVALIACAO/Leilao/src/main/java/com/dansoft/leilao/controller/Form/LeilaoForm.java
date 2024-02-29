package com.dansoft.leilao.controller.Form;

import com.dansoft.leilao.model.Leilao;

public class LeilaoForm {
	private String descricao;
	private double valor_minimo;
	private int status;

	public LeilaoForm(String descricao, double valor_minimo, int status) {
		this.descricao = descricao;
		this.valor_minimo = valor_minimo;
		this.status = status;
	}

	public LeilaoForm() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor_minimo;
	}

	public void setValor(double valor_minimo) {
		this.valor_minimo = valor_minimo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Leilao toLeilao() {
		Leilao leilao = new Leilao();
		leilao.setDescricao(descricao);
		leilao.setValor_minimo(valor_minimo);
		leilao.setStatus(status);
		return leilao;
	}

}
