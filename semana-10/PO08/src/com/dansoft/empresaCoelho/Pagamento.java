package com.dansoft.empresaCoelho;

import java.io.Serializable;
import java.util.Date;

public class Pagamento implements Serializable {
	private static final long serialVersionUID = 6353968182259257509L;
	private Date data;
	private double valor;
	private Reembolso reembolso = new Reembolso();

	public Pagamento(Date data, double valor) {
		super();
		this.data = data;
		this.valor = valor;
	}

	public Pagamento() {
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

	public Reembolso getReembolso() {
		return reembolso;
	}

	public void setReembolso(Reembolso reembolso) throws Exception {
		if(reembolso == null)
			throw new Exception("Reembolso não deve ser nulo.");
		this.reembolso = reembolso;
	}

	public void aplicarReembolso(double valor, Date data) throws Exception {
		Reembolso reembolso = new Reembolso();
		if (data == null)
			throw new Exception("A data não deve ser nula.");
		reembolso.setData(data);

		if (valor == 0)
			throw new Exception("O valor deve ser acima de 0.");
		reembolso.setValor(valor);
		
		setReembolso(reembolso);
	}
	
	public void exibirInformacoes() {
		System.out.println("Data: " + this.data + "\nValor: " + this.valor);
		if(reembolso.getData() != null && reembolso.getValor() != 0) {
			System.out.println("\nReembolso: ");
			reembolso.exibirInformacoes();
		}
		System.out.println();
	}

}
