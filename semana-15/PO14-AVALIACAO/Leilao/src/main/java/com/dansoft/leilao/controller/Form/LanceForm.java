package com.dansoft.leilao.controller.Form;

import com.dansoft.leilao.model.Concorrente;
import com.dansoft.leilao.model.Lance;
import com.dansoft.leilao.model.Leilao;

public class LanceForm {
	private Concorrente concorrente;
	private Leilao leilao;
	private double valor;

	public LanceForm(Concorrente concorrente, Leilao leilao, double valor) {
		super();
		this.concorrente = concorrente;
		this.leilao = leilao;
		this.valor = valor;
	}

	public LanceForm() {
		super();
	}

	public Concorrente getConcorrente() {
		return concorrente;
	}

	public void setConcorrente(Concorrente concorrente) {
		this.concorrente = concorrente;
	}

	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Lance toLance() {
	    Lance lance = new Lance();
	    lance.setConcorrente(concorrente);
	    lance.setLeilao(leilao);
	    lance.setValor(valor);
	    return lance;
	}
}
