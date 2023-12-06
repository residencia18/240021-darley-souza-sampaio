package empresaCoelho;

import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String cpf;
	private ArrayList<Imovel> imoveis;

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.imoveis = new ArrayList<>();
	}

	public void adicionarImovel(Imovel imovel) {
		
	}

	public void removerImovel(Imovel imovel) {

	}
	
	public void listarImoveis() {

	}
	
	public void alterarCliente(String novoNome) {
		
    }

	public void gerarFatura() {
		
    }
	
}
