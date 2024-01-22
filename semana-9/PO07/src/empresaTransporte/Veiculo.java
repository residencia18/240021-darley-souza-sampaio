package empresaTransporte;

import java.io.Serializable;

public class Veiculo implements Serializable {
	private static final long serialVersionUID = -5430961664006222927L;
	private String tipo;
	private String codigo;
	private String placa;

	public Veiculo(String tipo, String codigo, String placa) {
		this.tipo = tipo;
		this.codigo = codigo;
		this.placa = placa;
	}
	public Veiculo() {
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
		System.out.println("-----------------------------------------------");
		System.out.println("Código: " + this.codigo + "\nTipo: " + this.tipo + "\nPlaca: " + this.placa);
		System.out.println("-----------------------------------------------");
	}

}
