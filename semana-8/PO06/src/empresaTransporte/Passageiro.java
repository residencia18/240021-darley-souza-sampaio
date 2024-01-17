package empresaTransporte;

public class Passageiro extends Pessoa {
	private static final long serialVersionUID = -5534406648858919870L;
	private CartaoPassageiro cartao;

	public Passageiro(String nome, String cpf) {
		super(nome, cpf);
		this.cartao = null;
	}

	public CartaoPassageiro getCartao() {
		return cartao;
	}

	public void setCartao(CartaoPassageiro cartao) {
		this.cartao = cartao;
	}

	public void exibirInformacoes() {
		System.out.println("-----------------------------------------------");
		System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf());
		if (cartao != null) {
			System.out.println("Tipo de Cartão: " + cartao.getTipoCartao() + "\nNúmero Cartão: "
					+ cartao.getNumeroDoCartao() + "\nSaldo Cartão: " + cartao.getSaldo());
		}else {
			System.out.println("Cartão não cadastrado.");
		}
		System.out.println("-----------------------------------------------");
	}

}