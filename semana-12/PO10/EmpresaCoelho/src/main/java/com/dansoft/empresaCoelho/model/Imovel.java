package com.dansoft.empresaCoelho.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.dansoft.validations.Validations;

@Entity
public class Imovel implements Serializable {
	private static final long serialVersionUID = 1724779994092875174L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "matricula", nullable = false, length = 15)
	private String matricula;

	@Column(name = "endereco", nullable = false, length = 255)
	private String endereco;

	@Column(name = "ultima_leitura", nullable = false, length = 10)
	private String ultimaLeitura;

	@Column(name = "penultima_leitura", nullable = false, length = 10)
	private String penultimaLeitura;

	@ManyToOne
	@JoinColumn(name = "Cliente_id", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "imovel")
	private List<Fatura> faturas;

	public Imovel(String matricula, String endereco, String ultimaLeitura, String penultimaLeitura, Cliente cliente,
			List<Fatura> faturas) {
		super();
		this.matricula = matricula;
		this.endereco = endereco;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
		this.cliente = cliente;
		this.faturas = faturas;
	}

	public Imovel() {
	}

	public Integer getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) throws Exception {
		if (matricula == null)
			throw new Exception("A matrícula não deve ser nula.");
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) throws Exception {
		if (endereco == null)
			throw new Exception("O endereço não deve ser nulo.");
		this.endereco = endereco;

	}

	public String getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(String ultimaLeitura) throws Exception {
		Validations validationLeitura = new Validations();
		if (ultimaLeitura == null)
			throw new Exception("Última leitura não deve ser nula.");
		if (!validationLeitura.isValidLeitura(ultimaLeitura)) {
			throw new Exception("A leitura deve conter somente números.");
		}
		this.ultimaLeitura = ultimaLeitura;

	}

	public String getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(String penultimaLeitura) throws Exception {

		Validations validationLeitura = new Validations();
		if (penultimaLeitura == null)
			throw new Exception("Penúltima leitura não deve ser nula.");

		if (!validationLeitura.isValidLeitura(penultimaLeitura)) {
			throw new Exception("A leitura deve conter somente números.");
		}
		this.penultimaLeitura = penultimaLeitura;

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) throws Exception {
		if (cliente == null)
			throw new Exception("Cliente não deve ser nulo.\n");
		this.cliente = cliente;
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) throws Exception {
		if (faturas == null)
			throw new Exception("A lista de faturas não deve ser nula.");
		this.faturas = faturas;
	}

	public void exibirInformacoes() {
		System.out.println("------------------- Imóveis -------------------");
		System.out.println("Matrícula: " + this.matricula + "\nEndereco: " + this.endereco + "\nÚltima Leitura: "
				+ this.ultimaLeitura + "\nPenúltima Leitura: " + this.penultimaLeitura);
		System.out.println("-----------------------------------------------\n");
	}

}
