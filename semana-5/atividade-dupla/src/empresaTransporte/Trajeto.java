package empresaTransporte;

import java.util.List;

public class Trajeto {
	private String codigo;
	private List<Trecho> trechos;
	private Veiculo veiculoAssociado;

	public Trajeto(String codigo, List<Trecho> trechos, Veiculo veiculoAssociado) {
		this.codigo = codigo;
		this.trechos = trechos;
		this.veiculoAssociado = veiculoAssociado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Trecho> getTrechos() {
		return trechos;
	}

	public void setTrechos(List<Trecho> trechos) {
		this.trechos = trechos;
	}

	public Veiculo getVeiculoAssociado() {
		return veiculoAssociado;
	}

	public void setVeiculoAssociado(Veiculo veiculoAssociado) {
		this.veiculoAssociado = veiculoAssociado;
	}

}
