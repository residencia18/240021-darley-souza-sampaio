package com.dansoft.leilao.controller.Form;

import com.dansoft.leilao.model.Concorrente;

public class ConcorrenteForm {
	private String nome;
	private String cpf;

	public ConcorrenteForm(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public ConcorrenteForm() {
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

	public Concorrente toConcorrente() {
		Concorrente concorrente = new Concorrente();
		concorrente.setNome(nome);
		concorrente.setCpf(cpf);
		return concorrente;
	}
}
