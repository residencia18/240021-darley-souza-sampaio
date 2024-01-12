package empresaTransporte;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {

	private static Scanner scanner = new Scanner(System.in);
	private static List<Veiculo> veiculos = new ArrayList<>();
	private static List<Passageiro> passageiros = new ArrayList<>();
	private static List<PontoParada> pontos = new ArrayList<>();
	private static List<Trecho> trechos = new ArrayList<>();
	
	public static void main(String[] args) {
		int choice;

		do {
			System.out.println("----- Menu Principal -----\n" + "1. Gerenciar Veículos\n" + "2. Gerenciar Motoristas\n"
					+ "3. Gerenciar Cobradores\n" + "4. Gerenciar Passageiros\n" + "5. Gerenciar Pontos de Parada\n"
					+ "6. Gerenciar Trajetos\n" + "7. Gerenciar Trechos\n" + "8. Gerenciar Jornadas\n"
					+ "9. Gerenciar Cartões de Passageiro\n" + "10. Sair\n");
			System.out.print("Escolha uma opção: ");

			choice = scanner.nextInt();
			System.out.println();
			scanner.nextLine();

			switch (choice) {
			case 1:
				menuVeiculos();
				break;
			case 2:
				menuMotoristas();
				break;
			case 3:
				menuCobradores();
				break;
			case 4:
				menuPassageiros();
				break;
			case 5:
				menuPontosDeParada();
				break;
			case 6:
				menuTrajetos();
				break;
			case 7:
				menuTrechos();
				break;
			case 8:
				menuJornadas();
				break;
			case 9:
				menuCartoesDePassageiro();
				break;
			case 10:
				System.out.println("Saindo do programa. Até logo!");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (choice != 10);
	}

	private static void menuVeiculos() {
		int option;

		do {
			System.out.println("----- Menu Veículos -----\n" + "1. Cadastrar Veículo\n" + "2. Editar Veículo\n"
					+ "3. Excluir Veículo\n" + "4. Listar Veículos\n" + "5. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				cadastrarVeiculo();
				break;
			case 2:
				editarVeiculo();
				break;
			case 3:
				excluirVeiculo();
				break;
			case 4:
				listarVeiculos();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void cadastrarVeiculo() {
		System.out.print("Informe o tipo do veículo: ");
		String tipo = scanner.nextLine();

		System.out.print("Informe o código do veículo: ");
		String codigo = scanner.nextLine();

		System.out.print("Informe a placa do veículo: ");
		String placa = scanner.nextLine();

		Veiculo veiculo = new Veiculo(tipo, codigo, placa);
		veiculos.add(veiculo);

		System.out.println("Veículo Cadastrado\n\n");
	}

	private static void editarVeiculo() {
		String alteracao;
		System.out.print("Informe o código do veículo: ");
		String codigo = scanner.nextLine();

		System.out.println("1 - Tipo\n2 - Código\n3 - Placa\n");
		System.out.print("Selecione a opção que deseja editar: ");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		switch (opcao) {
		case 1: {
			System.out.print("Digite o tipo: ");
			alteracao = scanner.next();
		}
			break;
		case 2: {
			System.out.print("Digite o codigo: ");
			alteracao = scanner.next();
		}
			break;
		case 3: {
			System.out.print("Digite a placa: ");
			alteracao = scanner.next();
		}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcao);
		}

		for (Veiculo veiculo : veiculos) {
			if (veiculo.getCodigo().equals(codigo)) {
				if (opcao == 1)
					veiculo.setTipo(alteracao);
				else if (opcao == 2)
					veiculo.setCodigo(alteracao);
				else if (opcao == 3)
					veiculo.setPlaca(alteracao);
			}
		}
		System.out.println("Veículo Editado\n\n");
	}

	private static void excluirVeiculo() {
		System.out.print("Informe o código do veículo: ");
		String codigo = scanner.nextLine();

		for (Veiculo veiculo : veiculos) {
			if (veiculo.getCodigo().equals(codigo)) {
				veiculos.remove(veiculo);
				System.out.println("Veículo removido.");
				return;
			}
		}

		System.out.println("Veículo não encontrado.");
	}

	private static void listarVeiculos() {
		System.out.println("----- Todos Veículos -----\n\n");
		for (Veiculo veiculo : veiculos) {
			veiculo.exibirInformacoes();
		}
	}

	private static void menuMotoristas() {
		int option;

		do {
			System.out.println("----- Menu Motorista -----\n" + "1. Cadastrar Motorista\n" + "2. Editar Motorista\n"
					+ "3. Excluir Motorista\n" + "4. Listar Motorista\n" + "5. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				cadastrarMotorista();
				break;
			case 2:
				editarMotorista();
				break;
			case 3:
				excluirMotorista();
				break;
			case 4:
				listarMotorista();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void cadastrarMotorista() {

	}

	private static void editarMotorista() {

	}

	private static void excluirMotorista() {

	}

	private static void listarMotorista() {

	}

	private static void menuCobradores() {
		int option;

		do {
			System.out.println("----- Menu Cobradores -----");
			System.out.println("1. Cadastrar Cobrador");
			System.out.println("2. Editar Cobrador");
			System.out.println("3. Excluir Cobrador");
			System.out.println("4. Listar Cobradores");
			System.out.println("5. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				cadastrarCobrador();
				break;
			case 2:
				editarCobrador();
				break;
			case 3:
				excluirCobrador();
				break;
			case 4:
				listarCobradores();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void cadastrarCobrador() {

	}

	private static void editarCobrador() {

	}

	private static void excluirCobrador() {

	}

	private static void listarCobradores() {

	}

	private static void menuPassageiros() {
		int option;

		do {
			System.out.println("----- Menu Passageiros -----");
			System.out.println("1. Cadastrar Passageiro");
			System.out.println("2. Editar Passageiro");
			System.out.println("3. Excluir Passageiro");
			System.out.println("4. Listar Passageiros");
			System.out.println("5. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				cadastrarPassageiro();
				break;
			case 2:
				editarPassageiro();
				break;
			case 3:
				excluirPassageiro();
				break;
			case 4:
				listarPassageiros();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void cadastrarPassageiro() {
		System.out.print("Nome: ");
		String nome = scanner.nextLine();

		System.out.print("CPF; ");
		String cpf = scanner.nextLine();

		System.out.print("Tipo de cartão (idoso, estudantil ou transporte): ");
		String tipoCartao = scanner.nextLine();

		System.out.print("Número do Cartão: ");
		String numeroCartao = scanner.nextLine();

		CartaoPassageiro cartao = new CartaoPassageiro(tipoCartao, numeroCartao, 0);

		Passageiro passageiro = new Passageiro(nome, cpf, cartao);

		passageiros.add(passageiro);

		System.out.println("Passageiro adicionado com sucesso.\n\n");

	}

	private static void editarPassageiro() {
		String alteracao;

		System.out.print("Infome o CPF do passageiro: ");
		String cpf = scanner.nextLine();

		System.out.println("1 - Alterar nome\n2 - Alterar CPF\n");
		System.out.print("Selecione: ");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		switch (opcao) {
		case 1: {
			System.out.print("Novo nome: ");
			alteracao = scanner.nextLine();
		}
			break;
		case 2: {
			System.out.print("Novo CPF: ");
			alteracao = scanner.nextLine();
		}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcao);
		}

		for (Passageiro passageiro : passageiros) {
			if (passageiro.getCpf().equals(cpf)) {
				if (opcao == 1) {
					passageiro.setNome(alteracao);
					System.out.println("Nome alterado com sucesso.\n");
					return;
				} else {
					passageiro.setCpf(alteracao);
					System.out.println("CPF alterado com sucesso\n");
					return;
				}
			}
		}
	}

	private static void excluirPassageiro() {
		System.out.print("Infome o CPF do passageiro: ");
		String cpf = scanner.nextLine();

		for (Passageiro passageiro : passageiros) {
			if (passageiro.getCpf().equals(cpf)) {
				passageiros.remove(passageiro);
				System.out.println("Passageiro excluído.\n");
				return;
			}
		}

		System.out.println("Passageiro não encontrado.\n");
	}

	private static void listarPassageiros() {
		for (Passageiro passageiro : passageiros) {
			passageiro.exibirInformacoes();
		}
		System.out.println("Listando passageiros...");
	}

	private static void menuPontosDeParada() {
		int option;

		do {
			System.out.println("----- Menu Pontos de Parada -----");
			System.out.println("1. Cadastrar Ponto de Parada");
			System.out.println("2. Editar Ponto de Parada");
			System.out.println("3. Excluir Ponto de Parada");
			System.out.println("4. Listar Pontos de Parada");
			System.out.println("5. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				cadastrarPontoDeParada();
				break;
			case 2:
				editarPontoDeParada();
				break;
			case 3:
				excluirPontoDeParada();
				break;
			case 4:
				listarPontosDeParada();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void cadastrarPontoDeParada() {
		System.out.println("Código: ");
		String codigo = scanner.nextLine();

		System.out.print("Localização(Rua): ");
		String localizacao = scanner.nextLine();

		System.out.print("Referência: ");
		String referencia = scanner.nextLine();

		PontoParada ponto = new PontoParada(codigo, localizacao, referencia);

		pontos.add(ponto);
	}

	private static void editarPontoDeParada() {
		System.out.println("Código do ponto: ");
		String codigo = scanner.nextLine();

		for (PontoParada ponto : pontos) {
			if (ponto.getCodigo().equals(codigo)) {
				System.out.println("Localização: ");
				String localizacao = scanner.nextLine();

				System.out.println("Referência: ");
				String referencia = scanner.nextLine();

				ponto.setLocalizacao(localizacao);
				ponto.setReferencias(referencia);

				System.out.println("Ponto de parada editado com sucesso.\n");
				return;
			}
		}

		System.out.println("Ponto de parada não encontrado.\n");

	}

	private static void excluirPontoDeParada() {
		System.out.println("Código do ponto: ");
		String codigo = scanner.nextLine();

		for (PontoParada ponto : pontos) {
			if (ponto.getCodigo().equals(codigo)) {
				pontos.remove(ponto);

				System.out.println("Ponto de parada removido com sucesso.\n");
				return;
			}
		}
		System.out.println("Ponto de parada não encontrado.\n");

	}

	private static void listarPontosDeParada() {
		System.out.println("----- Pontos de Parada -----");
		for (PontoParada ponto : pontos) {
			ponto.exibirInformacoes();
		}
	}

	private static void menuTrechos() {
		int option;

		do {
			System.out.println("----- Menu Trechos -----");
			System.out.println("1. Cadastrar Trecho");
			System.out.println("2. Editar Trecho");
			System.out.println("3. Excluir Trecho");
			System.out.println("4. Listar Trechos");
			System.out.println("5. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				cadastrarTrecho();
				break;
			case 2:
				editarTrecho();
				break;
			case 3:
				excluirTrecho();
				break;
			case 4:
				listarTrechos();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void cadastrarTrecho() {
		listarPontosDeParada();
		System.out.print("Indique o ponto de origem(codigo): ");
		String codigoOrigem = scanner.nextLine();

		System.out.print("Indique o ponto de destino(codigo): ");
		String codigoDestino = scanner.nextLine();

		System.out.print("Insira intervalo estimado(minutos): ");
		int intervaloEstimado = scanner.nextInt();

		scanner.nextLine();

		System.out.print("Código de identificação do trecho: ");
		String codigo = scanner.nextLine();

		PontoParada pontoOrigem = null;
		PontoParada pontoDestino = null;

		for (PontoParada ponto : pontos) {
			if (ponto.getCodigo().equals(codigoOrigem)) {
				pontoOrigem = ponto;
			}

			if (ponto.getCodigo().equals(codigoDestino)) {
				pontoDestino = ponto;
			}
		}

		Trecho trecho = new Trecho(codigo, pontoOrigem, pontoDestino, intervaloEstimado);

		trechos.add(trecho);

		System.out.println("Trecho cadastrado com sucesso.\n");

	}

	private static void editarTrecho() {
		listarTrechos();
		System.out.println("Código do trecho: ");
		String codigo = scanner.nextLine();

		for (Trecho trecho : trechos) {
			if (trecho.getCodigo().equals(codigo)) {
				System.out.print("Indique o ponto de origem(codigo): ");
				String codigoOrigem = scanner.nextLine();

				System.out.print("Indique o ponto de destino(codigo): ");
				String codigoDestino = scanner.nextLine();

				System.out.print("Insira intervalo estimado(minutos): ");
				int intervaloEstimado = scanner.nextInt();

				PontoParada pontoOrigem = null;
				PontoParada pontoDestino = null;

				for (PontoParada ponto : pontos) {
					if (ponto.getCodigo().equals(codigoOrigem)) {
						pontoOrigem = ponto;
					}

					if (ponto.getCodigo().equals(codigoDestino)) {
						pontoDestino = ponto;
					}
				}

				trecho.setPontoDeParadaOrigem(pontoOrigem);
				trecho.setPontoDeParadaDestino(pontoDestino);
				trecho.setIntervaloEstimado(intervaloEstimado);

				System.out.println("Trecho editado com sucesso.\n");
				return;
			}
		}

		System.out.println("Trecho não encontrado.\n");
	}

	private static void excluirTrecho() {
		System.out.println("Código do trecho: ");
		String codigo = scanner.nextLine();

		Iterator<Trecho> iterator = trechos.iterator();
		while (iterator.hasNext()) {
			Trecho trecho = iterator.next();
			if (trecho.getCodigo().equals(codigo)) {
				iterator.remove();
				System.out.println("Trecho removido com sucesso.\n");
				return;
			}
		}
		System.out.println("Trecho não encontrado.\n");
	}

	private static void listarTrechos() {
		System.out.println("----- Trechos ----- \n");
		for (Trecho trecho : trechos) {
			trecho.exibirInformacoes();
		}
	}

	private static void menuTrajetos() {
		int option;

		do {
			System.out.println("----- Menu Trajetos -----");
			System.out.println("1. Cadastrar Trajeto");
			System.out.println("2. Editar Trajeto");
			System.out.println("3. Excluir Trajeto");
			System.out.println("4. Listar Trajetos");
			System.out.println("5. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				cadastrarTrajeto();
				break;
			case 2:
				editarTrajeto();
				break;
			case 3:
				excluirTrajeto();
				break;
			case 4:
				listarTrajetos();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void cadastrarTrajeto() {
		
	}

	private static void editarTrajeto() {
		
	}

	private static void excluirTrajeto() {

	}

	private static void listarTrajetos() {

	}

	private static void menuJornadas() {
		int option;

		do {
			System.out.println("----- Menu Jornadas -----");
			System.out.println("1. Iniciar Jornada");
			System.out.println("2. Encerrar Jornada");
			System.out.println("3. Visualizar Jornadas");
			System.out.println("5. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				iniciarJornada();
				break;
			case 2:
				encerrarJornada();
				break;
			case 3:
				visualizarJornadas();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void iniciarJornada() {

	}

	private static void encerrarJornada() {

	}

	private static void visualizarJornadas() {

	}

	private static void menuCartoesDePassageiro() {
		int option;

		do {
			System.out.println("----- Menu Cartões de Passageiro -----");
			System.out.println("1. Cadastrar Cartão de Passageiro");
			System.out.println("2. Recarregar Cartão de Passageiro");
			System.out.println("3. Utilizar Cartão de Passageiro");
			System.out.println("4. Listar Cartões de Passageiro");
			System.out.println("5. Voltar ao Menu Principal");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				cadastrarCartaoDePassageiro();
				break;
			case 2:
				recarregarCartaoDePassageiro();
				break;
			case 3:
				utilizarCartaoDePassageiro();
				break;
			case 4:
				listarCartoesDePassageiro();
				break;
			case 5:
				System.out.println("Voltando ao Menu Principal.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 5);
	}

	private static void cadastrarCartaoDePassageiro() {

	}

	private static void recarregarCartaoDePassageiro() {

	}

	private static void utilizarCartaoDePassageiro() {

	}

	private static void listarCartoesDePassageiro() {

	}

}
