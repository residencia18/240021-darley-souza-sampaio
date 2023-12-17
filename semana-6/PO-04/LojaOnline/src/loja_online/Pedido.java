package loja_online;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	int numeroPedido;
	private String cpf;
	List<ItemPedido> itens;
		
	public Pedido(int numeroPedido, String cpf, List<ItemPedido> itens) {
	    super();
	    this.numeroPedido = numeroPedido;
	    this.cpf = cpf;
	    this.itens = (itens != null) ? new ArrayList<>(itens) : new ArrayList<>();
	}


	public void adicionarItem(String descricao, double preco) {
        ItemPedido item = new ItemPedido(descricao, preco);
        itens.add(item);
    }

    public void adicionarItens(List<ItemPedido> itens) {
        this.itens.addAll(itens);
    }

    public double calcularTotal(double percentualDesconto) {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.preco;
        }
        return total - (total * percentualDesconto / 100);
    }

    public double calcularTotal(int numeroPrestacoes, double juro) {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.preco;
        }
        return total + (total * juro / 100 * numeroPrestacoes);
    }

    public void exibirInformacoes() {
        System.out.println("Número do Pedido: " + numeroPedido);
        System.out.println("CPF do Cliente: " + cpf);
        System.out.println("Itens do Pedido:");
        for (ItemPedido item : itens) {
            System.out.println("- " + item.descricao + ": R$ " + item.preco);
        }
    }
}
