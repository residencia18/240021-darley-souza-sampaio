package empresaTransporte;

public class Cobrador extends Pessoa {
	private String identificacao;

	public Cobrador(String nome, String cpf, String identificacao) {
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
