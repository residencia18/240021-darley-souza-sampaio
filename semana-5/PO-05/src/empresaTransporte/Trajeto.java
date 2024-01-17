package empresaTransporte;

import java.util.List;

public class Trajeto {
	private String codigo;
	private List<Trecho> trechos;

	public Trajeto(String codigo, List<Trecho> trechos) {
		this.codigo = codigo;
		this.trechos = trechos;
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

	public void exibirInformacoes() {
		System.out.println("-----------------------------------------------");
		System.out.println("Código Trajeto: " + this.codigo);
		for (Trecho trecho : trechos) {
			trecho.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	public void adicionaTrecho(Trecho trecho) {
		trechos.add(trecho);
	}

	public void removeTrecho(String codigo) {
		for (Trecho trecho : trechos) {
			if (trecho.getCodigo().equals(codigo)) {
				trechos.remove(trecho);
				System.out.println("Trecho removido.\n");
				return;
			}
		}
		System.out.println("Trecho não encontrado.\n");
	}

}
