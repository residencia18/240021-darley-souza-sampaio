package empresaTransporte;

public class Veiculo {
	private String tipo;
	private String codigo;
	private String placa;

	public Veiculo(String tipo, String codigo, String placa) {
		this.tipo = tipo;
		this.codigo = codigo;
		this.placa = placa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	void exibirInformacoes() {
		System.out.println("\nTipo: " + this.tipo + "\n" + "Código: " + this.codigo + "\nPlaca: " + this.placa + "\n");
	}

}
