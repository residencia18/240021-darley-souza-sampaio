package empresaTransporte;

public class Motorista extends Pessoa {
	private static final long serialVersionUID = -6278721371833821988L;
	private String identificacao;

	public Motorista(String nome, String cpf, String identificacao) {
		super(nome, cpf);
		this.identificacao = identificacao;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public void exibirInformacoes() {
		System.out.println("-----------------------------------------------");
		System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nIdentificação: "
				+ this.identificacao + "\n");
		System.out.println("-----------------------------------------------");
	}

}
