package com.dansoft.empresaCoelho;

import java.io.Serializable;
import java.util.Date;

public class Pagamento implements Serializable {
	private static final long serialVersionUID = 6353968182259257509L;
	private Integer id;
	private String matricula;
	private Date data;
	private double valor;

	public Pagamento(Date data, double valor) {
		super();
		this.data = data;
		this.valor = valor;
	}

	public Pagamento() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
		System.out.println("--------------- Pagamento ---------------");
		System.out.println("Matrícula: " + this.matricula + "\nData: " + this.data + "\nValor: " + this.valor);
		System.out.println("-----------------------------------------\n");
		
	}

}
