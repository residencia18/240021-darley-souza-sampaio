package com.dansoft.empresaCoelho;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fatura implements Serializable {
	private static final long serialVersionUID = -9058322035308109178L;
	private Integer id;
	private String matricula;
	private Date data;
	private String ultimaLeitura;
	private String penultimaLeitura;
	private double valor;
	private Boolean quitado;
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();

	public Fatura(String matricula, Date data, String ultimaLeiutra, String penultimaLeitura, double valor,
			boolean quitado) {
		this.matricula = matricula;
		this.data = data;
		this.ultimaLeitura = ultimaLeiutra;
		this.penultimaLeitura = penultimaLeitura;
		this.valor = valor;
		this.quitado = quitado;
	}

	public Fatura() {
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

	public String getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(String ultimaLeiutra) throws Exception {
		if (ultimaLeiutra != null) {
			this.ultimaLeitura = ultimaLeiutra;
		} else {
			throw new Exception("Última leitura não deve ser nula.");
		}

	}

	public String getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(String penultimaLeitura) throws Exception {
		if (penultimaLeitura != null) {
			this.penultimaLeitura = penultimaLeitura;
		} else {
			throw new Exception("Penúltima leitura não deve ser nula.");
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

	public boolean getQuitado() {
		return quitado;
	}

	public void setQuitado(Boolean quitado) throws Exception {
		if (quitado != null) {
			this.quitado = quitado;
		} else {
			throw new Exception("Quitado não deve ser nulo.");
		}

	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) throws Exception {
		if (pagamentos == null)
			throw new Exception("Pagamentos não deve ser nulo.");
		this.pagamentos = pagamentos;
	}
	
	public void calculaValor(double ultimaLeitura, double penultimaLeitura) {
		this.valor = (ultimaLeitura - penultimaLeitura) * 10;
	}

	public void realizarPagamento(double valor, Pagamento pagamento) throws Exception {
		if (valor == 0)
			throw new Exception("O valor a ser pago tem que ser maior que 0.");
		
		if(pagamento == null)
			throw new Exception("Pagamento não deve ser nulo.");

		if(pagamentos.isEmpty()) {
			if (valor >= this.valor) {
				this.quitado = true;
			}
		}else {
			if(valor >= (this.valor - calculaTotalPagamentos())) {				
				this.quitado = true;
			}
		}
		pagamentos.add(pagamento);
	}

	public double calculaTotalPagamentos() {
		double valorTotal = 0;
		for (Pagamento pagamento : pagamentos) {
			valorTotal = valorTotal + pagamento.getValor();
		}
		return valorTotal;
	}

	public void exibirInformacoes() {
		System.out.println("-------------- Fatura -------------------");
		System.out.println("Matrícula: " + this.matricula + "\nData: " + this.data + "\nÚltima Leitura: " + this.ultimaLeitura
				+ "\nPenúltima Leitura: " + this.penultimaLeitura + "\nValor: " + this.valor);
		if (this.quitado)
			System.out.println("Quitado: Sim\n");
		else
			System.out.println("Quitado: Não\n");
		System.out.println("----------------------------------------");
	}
	

}
