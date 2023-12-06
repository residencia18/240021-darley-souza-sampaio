package empresaCoelho;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<Imovel> imoveis = new ArrayList<>();
	private static ArrayList<Fatura> faturas = new ArrayList<>();
	private static ArrayList<Pagamento> pagamentos = new ArrayList<>();
	private static ArrayList<Falha> falhas = new ArrayList<>();
	private static ArrayList<Reparo> reparos = new ArrayList<>();
	private static ArrayList<Cliente> clientes = new ArrayList<>();

	public static void main(String[] args) {
		int opcao;

		do {
			System.out.println("\n---- MENU ---- ");
			System.out.println("1. Gestão de Clientes");
			System.out.println("2. Gestão de Imóveis");
			System.out.println("3. Gestão de Faturas");
			System.out.println("4. Gestão de Pagamentos");
			System.out.println("5. Gestão de Falhas e Reparos");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				menuClientes();
				break;
			case 2:
				menuImoveis();
			case 3:
				menuFaturas();
			case 4:
				menuPagamentos();
			case 5:
				menuFalhasReparos();
				break;
			case 6:
				System.out.println("Saindo do programa.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 0);

		scanner.close();
	}

	private static void menuClientes() {
		int opcaoClientes;

		do {
			System.out.println("\n---- MENU CLIENTES ----:");
			System.out.println("1. Incluir Cliente");
			System.out.println("2. Consultar Cliente");
			System.out.println("3. Listar Clientes");
			System.out.println("4. Excluir Cliente");
			System.out.println("5. Alterar Cliente");
			System.out.println("0. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");
			opcaoClientes = scanner.nextInt();

			switch (opcaoClientes) {
			case 1:
				incluirCliente();
				break;
			case 2:
				consultarCliente();
				break;
			case 3:
				listarClientes();
				break;
			case 4:
				excluirCliente();
				break;
			case 5:
				alterarCliente();
				break;
			case 0:
				System.out.println("Retornando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcaoClientes != 0);
	}

	private static void incluirCliente() {
	}

	private static void consultarCliente() {
	}

	private static void listarClientes() {
	}

	private static void excluirCliente() {
	}

	private static void alterarCliente() {
	}

	private static void menuImoveis() {
		int opcaoImoveis;

		do {
			System.out.println("\n---- MENU IMÓVEIS ----");
			System.out.println("1. Incluir Imóvel");
			System.out.println("2. Consultar Imóvel");
			System.out.println("3. Listar Imóveis");
			System.out.println("4. Excluir Imóvel");
			System.out.println("5. Alterar Imóvel");
			System.out.println("6. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");
			opcaoImoveis = scanner.nextInt();

			switch (opcaoImoveis) {
			case 1:
				incluirImovel();
				break;
			case 2:
				consultarImovel();
				break;
			case 3:
				listarImoveis();
				break;
			case 4:
				excluirImovel();
				break;
			case 5:
				alterarImovel();
				break;
			case 6:
				System.out.println("Retornando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcaoImoveis != 0);
	}

	private static void incluirImovel() {

	}

	private static void consultarImovel() {

	}

	private static void listarImoveis() {

	}

	private static void excluirImovel() {

	}

	private static void alterarImovel() {

	}

	private static void menuFaturas() {
		int escolha;
		do {
			System.out.println("\n---- MENU FATURAS----");
			System.out.println("1. Gerar Fatura para Imóvel");
			System.out.println("2. Listar Todas as Faturas");
			System.out.println("3. Listar Faturas em Aberto");
			System.out.println("4. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");
			escolha = scanner.nextInt();

			switch (escolha) {
			case 1:
				gerarFatura();
				break;
			case 2:
				listarTodasFaturas();
				break;
			case 3:
				listarFaturasEmAberto();
				break;
			case 4:
				System.out.println("Retornando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (escolha != 0);
	}

	private static void gerarFatura() {

	}

	private static void listarTodasFaturas() {

	}

	private static void listarFaturasEmAberto() {

	}

	private static Imovel buscarImovelFatura(int matricula) {

	}

	private static void menuPagamentos() {
		int escolha;
		do {
			System.out.println("\n---- MENU PAGAMENTOS ----");
			System.out.println("1. Incluir Pagamento");
			System.out.println("2. Listar Todos os Pagamentos");
			System.out.println("3. Listar Pagamentos de uma Fatura");
			System.out.println("4. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");
			escolha = scanner.nextInt();

			switch (escolha) {
			case 1:
				incluirPagamento();
				break;
			case 2:
				listarTodosPagamentos();
				break;
			case 3:
				listarPagamentosFatura();
				break;
			case 4:
				System.out.println("Retornando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (escolha != 0);
	}

	private static void incluirPagamento() {

	}

	private static void listarTodosPagamentos() {

	}

	private static void listarPagamentosFatura() {

	}

	private static void menuFalhasReparos() {
		int escolha;
		do {
			System.out.println("\n---- MENU FALHAS E REPAROS ----");
			System.out.println("1. Incluir Falha");
			System.out.println("2. Listar Falhas em Aberto");
			System.out.println("3. Submenu Reparos");
			System.out.println("4. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");
			escolha = scanner.nextInt();

			switch (escolha) {
			case 1:
				incluirFalha();
				break;
			case 2:
				listarFalhasAberto();
				break;
			case 3:
				submenuReparos();
				break;
			case 4:
				System.out.println("Retornando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (escolha != 0);
	}

	private static void incluirFalha() {
		System.out.println("\n---- INCLUIR FALHA ----");
		System.out.println("1. Falha de Geração");
		System.out.println("2. Falha de Distribuição");
		System.out.print("Escolha o tipo de falha: ");
		int tipoFalha = scanner.nextInt();

		scanner.nextLine();

	}

	private static void listarFalhasAberto() {

	}

	private static void submenuReparos() {
		int escolha;
		do {
			System.out.println("\n---- MENU REPAROS ----");
			System.out.println("1. Listar Reparos em Aberto");
			System.out.println("2. Encerrar Reparo");
			System.out.println("3. Voltar ao Menu Falhas e Reparos");
			System.out.print("Escolha uma opção: ");
			escolha = scanner.nextInt();

			switch (escolha) {
			case 1:
				listarReparosAberto();
				break;
			case 2:
				encerrarReparo();
				break;
			case 0:
				System.out.println("Retornando ao Menu Falhas e Reparos.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (escolha != 0);
	}

	private static void listarReparosAberto() {

	}

	private static void encerrarReparo() {

	}

	private static Imovel buscarImovelReparo(int matricula) {

	}

	private static Reparo buscarReparo(int numeroReparo) {

	}
}
