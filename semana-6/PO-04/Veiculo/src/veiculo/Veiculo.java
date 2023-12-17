package veiculo;

class Veiculo {
	private String modelo;
	private String cor;
	private int ano;

	public Veiculo(String modelo, String cor, int ano) {
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getModelo() {
		return modelo;
	}

	public String getCor() {
		return cor;
	}

	public int getAno() {
		return ano;
	}

	public void acelerar() {
		System.out.println("Veículo acelerando");
	}

	public void ligar() {
		System.out.println("Veículo ligado");
	}

	public void parar() {
		System.out.println("Veículo parado");
	}
}