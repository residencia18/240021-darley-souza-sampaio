package empresaTransporte;

import java.io.Serializable;

public class Trecho implements Serializable {
	static final long serialVersionUID = 844329502085082605L;
	private String codigo;
	private PontoParada pontoDeParadaOrigem;
	private PontoParada pontoDeParadaDestino;
	private int intervaloEstimado; // em minutos

	public Trecho(String codigo, PontoParada pontoDeParadaOrigem, PontoParada pontoDeParadaDestino,
			int intervaloEstimado) {
		this.codigo = codigo;
		this.pontoDeParadaOrigem = pontoDeParadaOrigem;
		this.pontoDeParadaDestino = pontoDeParadaDestino;
		this.intervaloEstimado = intervaloEstimado;
	}

	public Trecho() {
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public PontoParada getPontoDeParadaOrigem() {
		return pontoDeParadaOrigem;
	}

	public void setPontoDeParadaOrigem(PontoParada pontoDeParadaOrigem) {
		this.pontoDeParadaOrigem = pontoDeParadaOrigem;
	}

	public PontoParada getPontoDeParadaDestino() {
		return pontoDeParadaDestino;
	}

	public void setPontoDeParadaDestino(PontoParada pontoDeParadaDestino) {
		this.pontoDeParadaDestino = pontoDeParadaDestino;
	}

	public int getIntervaloEstimado() {
		return intervaloEstimado;
	}

	public void setIntervaloEstimado(int intervaloEstimado) {
		this.intervaloEstimado = intervaloEstimado;
	}

	public void exibirInformacoes() {
		System.out.println("-----------------------------------------------");
		System.out.println("Código Trecho: " + this.codigo);
		System.out.println("Ponto de Origem: ");
		this.pontoDeParadaOrigem.exibirInformacoes();
		System.out.println("Ponto de Destino: ");
		this.pontoDeParadaDestino.exibirInformacoes();
		System.out.println("Tempo Estimado: " + this.intervaloEstimado + " minutos\n");
		System.out.println("-----------------------------------------------");
	}

}
