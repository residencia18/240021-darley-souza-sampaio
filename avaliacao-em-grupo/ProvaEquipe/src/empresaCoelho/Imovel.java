package empresaCoelho;

import java.util.ArrayList;

public class Imovel {
	private int matricula;
	private String endereco;
	private int ultimaLeitura;
	private int penultimaLeitura;
	private ArrayList<Fatura> faturas;

	public Imovel(int matricula, String endereco, int ultimaLeitura, int penultimaLeitura) {
		super();
		this.matricula = matricula;
		this.endereco = endereco;
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
		this.faturas = new ArrayList<>();
	}

	public void gerarFatura() {

	}

	public int calcularConsumo() {

	}

	public void alterarImovel() {
	}

	public void listarFaturas(boolean apenasEmAberto) {

	}

	public void realizarPagamento(double valorPagamento) {

	}

	public void listarPagamentos() {

	}

	public void listarPagamentosFatura(int numeroFatura) {

	}

}
