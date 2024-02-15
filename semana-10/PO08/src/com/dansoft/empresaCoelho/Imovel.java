package com.dansoft.empresaCoelho;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dansoft.validations.Validations;

public class Imovel implements Serializable {
	private static final long serialVersionUID = 1724779994092875174L;
	private String matricula;
	private String endereco;
	private String ultimaLeitura;
	private String penultimaLeitura;
	private List<Fatura> faturas = new ArrayList<Fatura>();

	public Imovel(String matricula, String endereco, String ultimaLeitura, String penultimaLeitura) {
		this.matricula = matricula;
		this.endereco = endereco;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
	}

	public Imovel() {
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

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) throws Exception {
		if(faturas == null)
			throw new Exception("Faturas não deve ser nulo.");
		this.faturas = faturas;
	}

	public void listarFaturas() {
		for (Fatura fatura : faturas) {
			fatura.exibirInformacoes();
		}

	}
	
	public void listarFaturasEmAberto() {
		for (Fatura fatura : faturas) {
			if(!fatura.getQuitado())
				fatura.exibirInformacoes();
		}

	}

	public void adicionarFatura(Fatura fatura) throws Exception {
		if (fatura != null)
			faturas.add(fatura);
		else
			throw new Exception("Fatura não deve ser nula.");
	}

	public void exibirInformacoes() {
		System.out.println("------------------- Imóveis -------------------");
		System.out.println("Matrícula: " + this.matricula + "\nEndereco: " + this.endereco + "\nÚltima Leitura: "
				+ this.ultimaLeitura + "\nPenúltima Leitura: " + this.penultimaLeitura);
		if (this.faturas != null) {
			System.out.println("\n--- Faturas ---");
			listarFaturas();
		}
		System.out.println("-----------------------------------------------\n");
	}

	

}
