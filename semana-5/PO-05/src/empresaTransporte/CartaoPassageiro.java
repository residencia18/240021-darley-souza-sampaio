package empresaTransporte;

public class CartaoPassageiro {
	private String tipoCartao;
	private String numeroDoCartao;
	private double saldo;

	public CartaoPassageiro(String tipoCartao, String numeroDoCartao, double saldo) {
		this.tipoCartao = tipoCartao;
		this.numeroDoCartao = numeroDoCartao;
		this.saldo = saldo;
	}

	public String getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public void setNumeroDoCartao(String numeroDoCartao) {
		this.numeroDoCartao = numeroDoCartao;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public void utilizaCartao(double valor) {
		this.saldo = this.saldo - valor;
	}
	
	public void recarregarCartao(double valor) {
		this.saldo = this.saldo + valor;
	}
	
}
