package com.dansoft.empresaCoelho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 6353968182259257509L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "matricula", nullable = false, length = 15)
	private String matricula;

	@Column(name = "data", nullable = false)
	private Date data;

	@Column(name = "valor", nullable = false)
	private double valor;

	@ManyToOne
	@JoinColumn(name = "Fatura_id", nullable = false)
	private Fatura fatura;

	@OneToOne(mappedBy = "pagamento")
	private Reembolso reembolso;

	public Pagamento(String matricula, Date data, double valor, Fatura fatura, Reembolso reembolso) {
		super();
		this.matricula = matricula;
		this.data = data;
		this.valor = valor;
		this.fatura = fatura;
		this.reembolso = reembolso;
	}

	public Pagamento() {
	}

	public Integer getId() {
		return id;
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
		if (data == null)
			throw new Exception("A data não deve ser nula.");
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) throws Exception {
		if (valor == 0)
			throw new Exception("O valor tem que ser maior que zero.");
		this.valor = valor;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) throws Exception {
		if (fatura == null)
			throw new Exception("A fatura não deve ser nula");
		this.fatura = fatura;
	}

	public Reembolso getReembolso() {
		return reembolso;
	}

	public void setReembolso(Reembolso reembolso) throws Exception {
		if (reembolso == null)
			throw new Exception("O reembolso não deve ser nulo");
		this.reembolso = reembolso;
	}

	public void exibirInformacoes() {
		System.out.println("--------------- Pagamento ---------------");
		System.out.println("Matrícula: " + this.matricula + "\nData: " + this.data + "\nValor: R$" + this.valor);
		System.out.println("-----------------------------------------\n");
	}

}
