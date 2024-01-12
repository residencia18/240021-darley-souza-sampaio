package empresaTransporte;

public class Passageiro extends Pessoa {
	private CartaoPassageiro cartao;

	public Passageiro(String nome, String cpf, CartaoPassageiro cartao) {
		super(nome, cpf);
		this.cartao = cartao;
	}

	public CartaoPassageiro getCartao() {
		return cartao;
	}

	public void setCartao(CartaoPassageiro cartao) {
		this.cartao = cartao;
	}
	
	public void exibirInformacoes() {
		System.out.println(
				"Nome: " + this.getNome() + "\n" +
				"CPF: " + this.getCpf() + "\n" +
				"Tipo de Cartão: " + cartao.getTipoCartao() + "\n" +
				"Número Cartão: " + cartao.getNumeroDoCartao() + "\n" +
				"Saldo Cartão: " + cartao.getSaldo()
				);
	}
}
