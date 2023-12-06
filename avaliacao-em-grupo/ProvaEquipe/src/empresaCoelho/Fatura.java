package empresaCoelho;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fatura {
	private LocalDate dataEmissao;
	private int ultimaLeitura;
	private int penultimaLeitura;
	private double valor;
	private boolean quitado;
	private ArrayList<Pagamento> pagamentos;

	public Fatura(int ultimaLeitura, int penultimaLeitura) {
		this.dataEmissao = LocalDate.now();
		this.ultimaLeitura = ultimaLeitura;
		this.penultimaLeitura = penultimaLeitura;
		this.valor = calcularValor();
		this.quitado = false;
		this.pagamentos = new ArrayList<>();
	}

	private double calcularValor() {
		return (ultimaLeitura - penultimaLeitura) * 10.0;
	}

	public void quitarFatura() {
		this.quitado = true;
		System.out.println("Fatura quitada com sucesso.");
	}

	public void incluirPagamento(double valorPagamento) {

	}

	public void listarPagamentos() {

	}

	private double calcularTotalPagamentos() {

	}

	public boolean isQuitado() {
		return quitado;
	}
}
