package com.dansoft.empresaCoelhoClasses;

import java.io.Serializable;

import com.dansoft.validations.Validations;

public class Imovel implements Serializable {
	private static final long serialVersionUID = 1724779994092875174L;
	private Integer id;
	private String matricula;
	private String endereco;
	private String ultimaLeitura;
	private String penultimaLeitura;

	public Imovel(String matricula, String endereco, String ultimaLeitura, String penultimaLeitura) {
		this.matricula = matricula;
		this.endereco = endereco;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
	}

	public Imovel() {
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

	public void setMatricula(String matricula) throws Exception {
		if (matricula != null) {
			this.matricula = matricula;
		} else {
			throw new Exception("A matrícula não deve ser nula.");
		}

	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) throws Exception {
		if (endereco != null) {
			this.endereco = endereco;
		} else {
			throw new Exception("O endereço não deve ser nulo.");
		}

	}

	public String getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(String ultimaLeitura) throws Exception {
		Validations validationLeitura = new Validations();
		if (ultimaLeitura != null) {
			if (!validationLeitura.isValidLeitura(ultimaLeitura)) {
				throw new Exception("A leitura deve conter somente números.");
			}
			this.ultimaLeitura = ultimaLeitura;
		} else {
			throw new Exception("Última leitura não deve ser nula.");
		}

	}

	public String getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(String penultimaLeitura) throws Exception {

		Validations validationLeitura = new Validations();
		if (penultimaLeitura != null) {
			if (!validationLeitura.isValidLeitura(penultimaLeitura)) {
				throw new Exception("A leitura deve conter somente números.");
			}
			this.penultimaLeitura = penultimaLeitura;
		} else {
			throw new Exception("Penúltima leitura não deve ser nula.");
		}

	}

	public void exibirInformacoes() {
		System.out.println("------------------- Imóveis -------------------");
		System.out.println("Matrícula: " + this.matricula + "\nEndereco: " + this.endereco + "\nÚltima Leitura: "
				+ this.ultimaLeitura + "\nPenúltima Leitura: " + this.penultimaLeitura);
		System.out.println("-----------------------------------------------\n");
	}

}
