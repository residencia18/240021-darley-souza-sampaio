package com.dansoft.empresaCoelho.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Fatura implements Serializable {
	private static final long serialVersionUID = -9058322035308109178L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "matricula", nullable = false, length = 15)
	private String matricula;

	@Column(name = "data", nullable = false)
	private Date data;

	@Column(name = "ultima_leitura", nullable = false, length = 10)
	private String ultimaLeitura;

	@Column(name = "penultima_leitura", nullable = false, length = 10)
	private String penultimaLeitura;

	@Column(name = "valor", nullable = false)
	private double valor;

	@Column(name = "quitado", nullable = false)
	private Boolean quitado;

	@ManyToOne
	@JoinColumn(name = "Imovel_id", nullable = false)
	private Imovel imovel;

	@OneToMany(mappedBy = "fatura")
	private List<Pagamento> pagamentos;

	public Fatura(String matricula, Date data, String ultimaLeitura, String penultimaLeitura, double valor,
			Boolean quitado, Imovel imovel, List<Pagamento> pagamentos) {
		this.matricula = matricula;
		this.data = data;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
		this.valor = valor;
		this.quitado = quitado;
		this.imovel = imovel;
		this.pagamentos = pagamentos;
	}

	public Fatura() {
	}

	public Integer getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) throws Exception {
		if (matricula == null)
			throw new Exception("A matricula não deve ser nula.");
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

	public String getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(String ultimaLeiutra) throws Exception {
		if (ultimaLeiutra == null)
			throw new Exception("Última leitura não deve ser nula.");
		this.ultimaLeitura = ultimaLeiutra;

	}

	public String getPenultimaLeitura() {
		return penultimaLeitura;
	}

	public void setPenultimaLeitura(String penultimaLeitura) throws Exception {
		if (penultimaLeitura == null)
			throw new Exception("Penúltima leitura não deve ser nula.");
		this.penultimaLeitura = penultimaLeitura;

	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) throws Exception {
		if (valor == 0)
			throw new Exception("O valor tem que ser maior que zero.");
		this.valor = valor;
	}

	public boolean getQuitado() {
		return quitado;
	}

	public void setQuitado(Boolean quitado) throws Exception {
		if (quitado == null)
			throw new Exception("Quitado não deve ser nulo.");
		this.quitado = quitado;

	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) throws Exception {
		if (imovel == null)
			throw new Exception("O imóvel não pode ser nulo.\n");
		this.imovel = imovel;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) throws Exception {
		if (pagamentos == null)
			throw new Exception("A lista de pagamentos não pode ser nula.\n");
		this.pagamentos = pagamentos;
	}

	public void calculaValor(double ultimaLeitura, double penultimaLeitura) {
		this.valor = (ultimaLeitura - penultimaLeitura) * 10;
	}

	public void exibirInformacoes() {
		System.out.println("-------------- Fatura -------------------");
		System.out.println("Matrícula: " + this.matricula + "\nData: " + this.data + "\nÚltima Leitura: "
				+ this.ultimaLeitura + "\nPenúltima Leitura: " + this.penultimaLeitura + "\nValor: R$" + this.valor);
		if (this.quitado)
			System.out.println("Quitado: Sim\n");
		else
			System.out.println("Quitado: Não\n");
		System.out.println("----------------------------------------");
	}

}
