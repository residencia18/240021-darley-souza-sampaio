package loja_online;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	static List<Pedido> pedidos = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("\n ----- Menu -----" + "\n1. Criar Pedido" + "\n2. Adicionar Item ao Pedido"
					+ "\n3. Calcular Total do Pedido com Desconto à Vista" + "\n4. Calcular Total do Pedido a Prazo"
					+ "\n5. Exibir Informações do Pedido" + "\n6. Sair\n");

			System.out.print("Escolha uma opção: ");

			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				System.out.print("Digite o número do pedido: ");
				int numeroPedido = scanner.nextInt();
				scanner.nextLine();

				System.out.print("Digite o CPF do cliente: ");
				String cpfCliente = scanner.nextLine();

				criaPedido(numeroPedido, cpfCliente);
				break;
			case 2:
				System.out.print("Digite o número do pedido: ");
				int numeroPedidoAdicao = scanner.nextInt();
				scanner.nextLine();

				insereItemPedido(scanner, numeroPedidoAdicao);
				break;
			case 3:
				System.out.print("Digite o número do pedido: ");
				int numeroPedidoDesconto = scanner.nextInt();
				scanner.nextLine();

				calculaPrecoDesconto(scanner, numeroPedidoDesconto);
				break;
			case 4:
				System.out.print("Digite o número do pedido: ");
				int numeroPedidoPrazo = scanner.nextInt();
				scanner.nextLine();

				calculaPedidoPrazo(scanner, numeroPedidoPrazo);
				break;
			case 5:
				System.out.print("Digite o número do pedido: ");
				int numeroPedidoExibicao = scanner.nextInt();
				scanner.nextLine();

				exibirInformacoesPedido(scanner, numeroPedidoExibicao);

				break;
			case 0:
				System.out.println("Saindo do programa. Até logo!\n");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.\n");
				break;
			}

		} while (opcao != 0);

		scanner.close();
	}

	private static void criaPedido(int numeroPedido, String cpfCliente) {
		Pedido novoPedido = new Pedido(numeroPedido, cpfCliente, null);
		pedidos.add(novoPedido);

		System.out.println("Pedido criado com sucesso!\n\n");
	}

	private static void insereItemPedido(Scanner scanner, int numeroPedidoAdicao) {
		Pedido pedidoSelecionado = null;
		for (Pedido pedido : pedidos) {
			if (pedido.numeroPedido == numeroPedidoAdicao) {
				pedidoSelecionado = pedido;
				break;
			}
		}

		if (pedidoSelecionado != null) {
			System.out.print("Digite a descrição do item: ");
			String descricaoItem = scanner.nextLine();

			System.out.print("Digite o preço do item: ");
			double precoItem = scanner.nextDouble();

			pedidoSelecionado.adicionarItem(descricaoItem, precoItem);
			System.out.println("Item adicionado ao pedido com sucesso!\n");
		} else {
			System.out.println("Pedido não encontrado!\n");
		}
	}

	private static void calculaPrecoDesconto(Scanner scanner, int numeroPedidoDesconto) {
		Pedido pedidoComDesconto = encontrarPedido(pedidos, numeroPedidoDesconto);

		if (pedidoComDesconto != null) {
			System.out.print("Digite o percentual de desconto à vista: ");
			double percentualDesconto = scanner.nextDouble();

			double totalComDesconto = pedidoComDesconto.calcularTotal(percentualDesconto);

			System.out.println("Total do pedido com desconto à vista: R$ " + totalComDesconto + "\n");
		} else
			System.out.println("Pedido não encontrado!\n");
	}

	private static void calculaPedidoPrazo(Scanner scanner, int numeroPedidoPrazo) {
		Pedido pedidoAPrazo = encontrarPedido(pedidos, numeroPedidoPrazo);

		if (pedidoAPrazo != null) {
			System.out.print("Digite o número de prestações: ");
			int numeroPrestacoes = scanner.nextInt();

			System.out.print("Digite o percentual de juros a prazo: ");
			double juroPrazo = scanner.nextDouble();

			double totalAPrazo = pedidoAPrazo.calcularTotal(numeroPrestacoes, juroPrazo);
			
			System.out.println("Total do pedido a prazo: R$ " + totalAPrazo + "\n");
		} else
			System.out.println("Pedido não encontrado!\n");

	}

	private static void exibirInformacoesPedido(Scanner scanner, int numeroPedidoExibicao) {
		Pedido pedidoExibicao = encontrarPedido(pedidos, numeroPedidoExibicao);

		if (pedidoExibicao != null)
			pedidoExibicao.exibirInformacoes();
		else
			System.out.println("Pedido não encontrado!\n");
	}
	
	private static Pedido encontrarPedido(List<Pedido> pedidos, int numeroPedido) {
		for (Pedido pedido : pedidos) {
			if (pedido.numeroPedido == numeroPedido) {
				return pedido;
			}
		}
		return null;
	}
}
