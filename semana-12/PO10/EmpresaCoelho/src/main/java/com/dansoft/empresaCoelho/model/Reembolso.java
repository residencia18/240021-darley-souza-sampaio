package com.dansoft.empresaCoelho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reembolso implements Serializable {
	private static final long serialVersionUID = -3800864016307066118L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data", nullable = false)
	private Date data;

	@Column(name = "valor", nullable = false)
	private double valor;

	@OneToOne
	@JoinColumn(name = "Pagamento_id", nullable = false)
	private Pagamento pagamento;

	public Reembolso(Date data, double valor, Pagamento pagamento) {
		super();
		this.data = data;
		this.valor = valor;
		this.pagamento = pagamento;
	}

	public Reembolso() {
	}

	public Integer getId() {
		return id;
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

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) throws Exception {
		if (pagamento == null)
			throw new Exception("O pagamento não deve ser nulo.\n");
		this.pagamento = pagamento;
	}

	public void exibirInformacoes() {
		System.out.println("--------------- Reembolso ---------------");
		System.out.println("Data: " + this.data + "\nValor: R$" + this.valor);
		System.out.println("-----------------------------------------\n");
	}

}
