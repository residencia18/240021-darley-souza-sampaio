package com.dansoft.empresaCoelhoClasses;

import java.io.Serializable;
import java.util.Date;

public class Reembolso implements Serializable {
	private static final long serialVersionUID = -3800864016307066118L;
	private Integer id;
	private Date data;
	private double valor;

	public Reembolso(Date data, double valor) {
		this.data = data;
		this.valor = valor;
	}

	public Reembolso() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) throws Exception {
		if (data != null) {
			this.data = data;
		} else {
			throw new Exception("A data não deve ser nula.");
		}

	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) throws Exception {
		if (valor != 0) {
			this.valor = valor;
		} else {
			throw new Exception("O valor tem que ser maior que zero.");
		}
	}

	public void exibirInformacoes() {
		System.out.println("--------------- Reembolso ---------------");
		System.out.println("Data: " + this.data + "\nValor: R$" + this.valor);
		System.out.println("-----------------------------------------\n");
	}

}
