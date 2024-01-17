package empresaTransporte;

import exceptionsTransporte.*;
import validateTransporte.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class App {

	private static Scanner scanner = new Scanner(System.in);
	private static List<Veiculo> veiculos = new ArrayList<>();
	private static List<Passageiro> passageiros = new ArrayList<>();
	private static List<Motorista> motoristas = new ArrayList<>();
	private static List<Cobrador> cobradores = new ArrayList<>();
	private static List<PontoParada> pontos = new ArrayList<>();
	private static List<Trecho> trechos = new ArrayList<>();
	private static List<Trajeto> trajetos = new ArrayList<>();
	private static List<Jornada> jornadas = new ArrayList<>();

	public static void main(String[] args) {
		String opcao = null;

		do {
			try {
				System.out.println("----- Menu Principal -----\n" + "1. Gerenciar Veículos\n"
						+ "2. Gerenciar Motoristas\n" + "3. Gerenciar Cobradores\n" + "4. Gerenciar Passageiros\n"
						+ "5. Gerenciar Pontos de Parada\n" + "6. Gerenciar Trechos\n" + "7. Gerenciar Trajetos\n"
						+ "8. Gerenciar Jornadas\n" + "9. Gerenciar Cartões de Passageiro\n" + "10. Sair\n");
				System.out.print("Escolha uma opção: ");

				opcao = scanner.nextLine();
				System.out.println();

				if (isInt(opcao)) {
					switch (opcao) {
					case "1":
						menuVeiculos();
						break;
					case "2":
						menuMotoristas();
						break;
					case "3":
						menuCobradores();
						break;
					case "4":
						menuPassageiros();
						break;
					case "5":
						menuPontosDeParada();
						break;
					case "6":
						menuTrechos();
						break;
					case "7":
						menuTrajetos();
						break;
					case "8":
						menuJornadas();
						break;
					case "9":
						menuCartoesDePassageiro();
						break;
					case "10":
						System.out.println("Saindo do programa. Até logo!");
						break;
					default:
						System.out.println("Opção Inválida.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!opcao.equals("10"));
	}

	public static String gerarCodigoAleatorio() {
		StringBuilder codigo = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 3; i++) {
			char letra = (char) (random.nextInt(26) + 'A');
			codigo.append(letra);
		}

		for (int i = 0; i < 4; i++) {
			int numero = random.nextInt(10);
			codigo.append(numero);
		}

		return codigo.toString();
	}

	public static boolean isInt(String valor) {
		try {
			Integer.parseInt(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static void menuVeiculos() {
		String option = null;

		do {
			try {
				System.out.println("\n----- Menu Veículos -----\n" + "1. Cadastrar Veículo\n" + "2. Editar Veículo\n"
						+ "3. Excluir Veículo\n" + "4. Listar Veículos\n" + "5. Voltar ao Menu Principal\n");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1":
						cadastrarVeiculo();
						break;
					case "2":
						editarVeiculo();
						break;
					case "3":
						excluirVeiculo();
						break;
					case "4":
						listarVeiculos();
						break;
					case "5":
						System.out.println("Voltando ao Menu Principal.\n");
						break;
					default:
						System.out.println("Opção Inválida. Tente Novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("5"));
	}

	private static void cadastrarVeiculo() {
		VeiculoValidator validator = new VeiculoValidator();

		try {
			String codigo = gerarCodigoAleatorio();

			System.out.print("Informe o tipo do veículo (BÁSICO, PADRON, ARTICULADO, BIARTICULADO): ");
			String tipo = scanner.nextLine().toUpperCase();

			if (!validator.isValidCar(tipo))
				throw new Exception(
						"Tipo de veículo inválido.\nVeículos válidos: (Básico, Padron, Articulado, Biarticulado).");

			System.out.print("Informe a placa do veículo (AAA-1111): ");
			String placa = scanner.nextLine().toUpperCase();

			if (!validator.isValidPlaca(placa))
				throw new Exception("Placa inválida. Formato correto (AAA-111).");
			else {
				Optional<Veiculo> veiculoEncontradoPlacaIgual = veiculos.stream()
						.filter(veiculo -> veiculo.getPlaca().equals(placa)).findFirst();

				if (!veiculoEncontradoPlacaIgual.isEmpty())
					throw new Exception("Veículo com placa já existente.\n");

			}

			Veiculo veiculo = new Veiculo(tipo, codigo, placa);
			veiculos.add(veiculo);

			System.out.println("Veículo cadastrado com sucesso.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void editarVeiculo() {
		String option = null;
		do {

			try {

				System.out.println("1 - Tipo\n2 - Placa\n3 - Sair");
				System.out.print("Selecione a opção que deseja editar: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1": {
						alteracaoVeiculo(option);
					}
						break;
					case "2": {
						alteracaoVeiculo(option);
					}
						break;
					case "3": {
						System.out.println("Voltando ao Menu de Veículos.\n");
					}
						break;
					default:
						System.out.println("Opção Inválida. Tente Novamente.");
					}
				} else {
					throw new WrongInputException();
				}

			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("3"));
	}

	private static void alteracaoVeiculo(String tipoAlteracao) {
		VeiculoValidator validacao = new VeiculoValidator();

		try {
			System.out.print("Digite o código do veículo: ");
			String codigo = scanner.nextLine();

			Optional<Veiculo> veiculoEncontrado = veiculos.stream()
					.filter(veiculo -> veiculo.getCodigo().equals(codigo)).findFirst();

			if (veiculoEncontrado.isEmpty())
				throw new Exception("Carro não encontrado.\n");
			else {
				if (tipoAlteracao.equals("1")) {
					System.out.print("Digite o novo tipo: ");
					String alteracao = scanner.nextLine().toUpperCase();
					if (validacao.isValidCar(alteracao)) {
						veiculoEncontrado.ifPresent(veiculo -> {
							veiculo.setTipo(alteracao);
						});
					} else {
						throw new Exception("Tipo inválido. Verifique os tipos válidos e volte.\n");
					}
				} else {
					System.out.print("Digite a nova placa: ");
					String alteracao = scanner.nextLine().toUpperCase();

					if (validacao.isValidPlaca(alteracao)) {
						Optional<Veiculo> veiculoEncontradoPlacaIgual = veiculos.stream()
								.filter(veiculo -> veiculo.getPlaca().equals(alteracao)).findFirst();
						if (veiculoEncontradoPlacaIgual.isEmpty())
							veiculoEncontrado.ifPresent(veiculo -> {
								veiculo.setPlaca(alteracao);
							});
						else {
							throw new Exception("Veículo com placa já existente.\n");
						}
					} else {
						throw new Exception("Placa Inválida. Verifique a forma correta e volte.\n");
					}
				}
				System.out.println("Veículo editado com sucesso.\n");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirVeiculo() {
		try {
			System.out.print("Digite o código do veículo: ");
			String codigo = scanner.nextLine();

			Optional<Veiculo> veiculoEncontrado = veiculos.stream()
					.filter(veiculo -> veiculo.getCodigo().equals(codigo)).findFirst();

			if (veiculoEncontrado.isEmpty())
				throw new Exception("Carro não encontrado.\n");
			else
				veiculoEncontrado.ifPresent(veiculo -> {
					veiculos.remove(veiculo);
				});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarVeiculos() {
		System.out.println("--------------- Todos Veículos ---------------\n\n");
		for (Veiculo veiculo : veiculos) {
			veiculo.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuMotoristas() {
		String option = null;

		do {
			try {

				System.out
						.println("\n----- Menu Motorista -----\n" + "1. Cadastrar Motorista\n" + "2. Editar Motorista\n"
								+ "3. Excluir Motorista\n" + "4. Listar Motorista\n" + "5. Voltar ao Menu Principal\n");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1":
						cadastrarMotorista();
						break;
					case "2":
						editarMotorista();
						break;
					case "3":
						excluirMotorista();
						break;
					case "4":
						listarMotoristas();
						break;
					case "5":
						System.out.println("Voltando ao Menu Principal.\n");
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("5"));
	}

	private static void cadastrarMotorista() {
		CpfValidator cpfValidator = new CpfValidator();

		try {

			System.out.print("Nome: ");
			String nome = scanner.nextLine();

			System.out.print("CPF (XXX.XXX.XXX-XX): ");
			String cpf = scanner.nextLine();

			if (!cpfValidator.isValidCpf(cpf)) {
				throw new Exception("- !!!! Erro !!!! -\nVerifique se o CPF está no padrão ou se está correto\n");
			} else {
				Optional<Motorista> motoristaEncontradoMesmoCpf = motoristas.stream()
						.filter(motorista -> motorista.getCpf().equals(cpf)).findFirst();
				Optional<Cobrador> cobradorEncontradoMesmoCpf = cobradores.stream()
						.filter(cobrador -> cobrador.getCpf().equals(cpf)).findFirst();

				if (!motoristaEncontradoMesmoCpf.isEmpty())
					throw new Exception("Motorista com mesmo CPF já cadastrado.\n");
				else if (!cobradorEncontradoMesmoCpf.isEmpty())
					throw new Exception("Cobrador com mesmo CPF já cadastrado.\n");

			}

			String numeroIdentificacao = gerarCodigoAleatorio();

			Motorista motorista = new Motorista(nome, cpf, numeroIdentificacao);

			motoristas.add(motorista);

			System.out.println("Motorista cadastrado com sucesso.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void editarMotorista() {
		String option = null;
		do {

			try {
				System.out.println("1 - Alterar nome\n2 - Alterar CPF\n3 - Sair");
				System.out.print("Selecione: ");

				option = scanner.nextLine();

				if (isInt(option)) {
					switch (option) {
					case "1": {
						alteracaoMotorista(option);
					}
						break;
					case "2": {
						alteracaoMotorista(option);
					}
						break;
					case "3":
						System.out.print("Voltando ao menu de Motorista.\n");
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("3"));

	}

	private static void alteracaoMotorista(String tipoAlteracao) {
		CpfValidator cpfValidator = new CpfValidator();

		try {
			System.out.print("Infome a identificação: ");
			String identificacao = scanner.nextLine();

			Optional<Motorista> motoristaEncontrado = motoristas.stream()
					.filter(motorista -> motorista.getIdentificacao().equals(identificacao)).findFirst();

			if (motoristaEncontrado.isEmpty())
				throw new Exception("Motorista não encontrado.\n");
			else {
				if (tipoAlteracao.equals("1")) {
					System.out.print("Digite novo nome: ");
					String alteracao = scanner.nextLine();
					motoristaEncontrado.ifPresent(motorista -> {
						motorista.setNome(alteracao);
					});

				} else {
					System.out.print("Digite o novo CPF(XXX.XXX.XXX-XX): ");
					String alteracao = scanner.nextLine();
					if (cpfValidator.isValidCpf(alteracao)) {
						Optional<Motorista> motoristaEncontradoMesmoCpf = motoristas.stream()
								.filter(motorista -> motorista.getCpf().equals(alteracao)).findFirst();
						if (motoristaEncontradoMesmoCpf.isEmpty()) {
							motoristaEncontrado.ifPresent(motorista -> {
								motorista.setCpf(alteracao);
							});
						} else {
							throw new Exception("Motorista com mesmo CPF já cadastrado.\n");
						}
					} else {
						throw new Exception("CPF inválido. Tente novamente.\n");
					}
				}
				System.out.println("Motorista editado com sucesso.\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirMotorista() {
		try {
			System.out.print("Identificação do motorista: ");
			String identificaco = scanner.nextLine();

			Optional<Motorista> motoristaEncontrado = motoristas.stream()
					.filter(motorista -> motorista.getIdentificacao().equals(identificaco)).findFirst();

			if (motoristaEncontrado.isEmpty())
				throw new Exception("Motorista não encontrado.\n");
			else
				motoristaEncontrado.ifPresent(motorista -> {
					motoristas.remove(motorista);
				});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarMotoristas() {
		System.out.println("------------- Todos os Motoristas -------------\n");
		for (Motorista motorista : motoristas) {
			motorista.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuCobradores() {
		String option = null;

		do {
			try {

				System.out
						.println("\n----- Menu Cobradores -----\n" + "1. Cadastrar Cobrador\n" + "2. Editar Cobrador\n"
								+ "3. Excluir Cobrador\n" + "4. Listar Cobradores\n" + "5. Voltar ao Menu Principal");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {
					switch (option) {
					case "1":
						cadastrarCobrador();
						break;
					case "2":
						editarCobrador();
						break;
					case "3":
						excluirCobrador();
						break;
					case "4":
						listarCobradores();
						break;
					case "5":
						System.out.println("Voltando ao Menu Principal.\n");
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("5"));
	}

	private static void cadastrarCobrador() {
		CpfValidator cpfValidator = new CpfValidator();

		try {

			System.out.print("Nome: ");
			String nome = scanner.nextLine();

			System.out.print("CPF (XXX.XXX.XXX-XX): ");
			String cpf = scanner.nextLine();

			if (!cpfValidator.isValidCpf(cpf)) {
				throw new Exception("- !!!! Erro !!!! -\nVerifique se o CPF está no padrão ou se está correto\n");
			} else {
				Optional<Motorista> motoristaEncontradoMesmoCpf = motoristas.stream()
						.filter(motorista -> motorista.getCpf().equals(cpf)).findFirst();
				Optional<Cobrador> cobradorEncontradoMesmoCpf = cobradores.stream()
						.filter(cobrador -> cobrador.getCpf().equals(cpf)).findFirst();

				if (!motoristaEncontradoMesmoCpf.isEmpty())
					throw new Exception("Motorista com mesmo CPF já cadastrado.\n");
				else if (!cobradorEncontradoMesmoCpf.isEmpty())
					throw new Exception("Cobrador com mesmo CPF já cadastrado.\n");
			}

			String numeroIdentificacao = gerarCodigoAleatorio();

			Cobrador cobrador = new Cobrador(nome, cpf, numeroIdentificacao);

			cobradores.add(cobrador);

			System.out.println("Cobrador cadastrado com sucesso.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void editarCobrador() {
		String option = null;
		do {

			try {
				System.out.println("1 - Alterar nome\n2 - Alterar CPF\n3 - Sair");
				System.out.print("Selecione: ");

				option = scanner.nextLine();

				if (isInt(option)) {
					switch (option) {
					case "1": {
						alteracaoCobrador(option);
					}
						break;
					case "2": {
						alteracaoCobrador(option);
					}
						break;
					case "3":
						System.out.print("Voltando ao menu de cobradores.\n");
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("3"));

	}

	private static void alteracaoCobrador(String tipoAlteracao) {
		CpfValidator cpfValidator = new CpfValidator();

		try {
			System.out.print("Infome a identificação: ");
			String identificacao = scanner.nextLine();

			Optional<Cobrador> cobradorEncontrado = cobradores.stream()
					.filter(cobrador -> cobrador.getIdentificacao().equals(identificacao)).findFirst();

			if (cobradorEncontrado.isEmpty())
				throw new Exception("Motorista não encontrado.\n");
			else {
				if (tipoAlteracao.equals("1")) {
					System.out.print("Digite novo nome: ");
					String alteracao = scanner.nextLine();
					cobradorEncontrado.ifPresent(cobrador -> {
						cobrador.setNome(alteracao);
					});

				} else {
					System.out.print("Digite o novo CPF(XXX.XXX.XXX-XX): ");
					String alteracao = scanner.nextLine();
					if (cpfValidator.isValidCpf(alteracao)) {
						Optional<Cobrador> cobradorEncontradoMesmoCpf = cobradores.stream()
								.filter(cobrador -> cobrador.getCpf().equals(alteracao)).findFirst();
						if (cobradorEncontradoMesmoCpf.isEmpty()) {
							cobradorEncontrado.ifPresent(cobrador -> {
								cobrador.setCpf(alteracao);
							});
						} else {
							throw new Exception("Cobrador com mesmo CPF já cadastrado.\n");
						}
					} else {
						throw new Exception("CPF inválido. Tente novamente.\n");
					}

				}
				System.out.println("Cobrador editado com sucesso.\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirCobrador() {
		try {
			System.out.print("Identificação do cobrador: ");
			String identificaco = scanner.nextLine();

			Optional<Cobrador> cobradorEncontrado = cobradores.stream()
					.filter(cobrador -> cobrador.getIdentificacao().equals(identificaco)).findFirst();

			if (cobradorEncontrado.isEmpty())
				throw new Exception("Cobrador não encontrado.\n");
			else
				cobradorEncontrado.ifPresent(cobrador -> {
					cobradores.remove(cobrador);
				});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarCobradores() {
		System.out.println("------------- Todos os Motoristas -------------\n");
		for (Cobrador cobrador : cobradores) {
			cobrador.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuPassageiros() {
		String option = null;

		do {
			try {

				System.out.println("\n----- Menu Passageiros -----\n" + "1. Cadastrar Passageiro\n"
						+ "2. Editar Passageiro\n" + "3. Excluir Passageiro\n" + "4. Listar Passageiros\n"
						+ "5. Voltar ao Menu Principal");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1":
						cadastrarPassageiro();
						break;
					case "2":
						editarPassageiro();
						break;
					case "3":
						excluirPassageiro();
						break;
					case "4":
						listarPassageiros();
						break;
					case "5":
						System.out.println("Voltando ao Menu Principal.\n");
						break;
					default:
						System.out.println("Opção Inválida. Tente Novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("5"));
	}

	private static void cadastrarPassageiro() {
		CpfValidator cpfValidator = new CpfValidator();

		try {

			System.out.print("Nome: ");
			String nome = scanner.nextLine();

			System.out.print("CPF (XXX.XXX.XXX-XX): ");
			String cpf = scanner.nextLine();

			if (!cpfValidator.isValidCpf(cpf)) {
				throw new Exception("- !!!! Erro !!!! -\nVerifique se o CPF está no padrão ou se está correto\n");
			} else {
				Optional<Passageiro> passageiroEncontradoMesmoCpf = passageiros.stream()
						.filter(passageiro -> passageiro.getCpf().equals(cpf)).findFirst();

				if (!passageiroEncontradoMesmoCpf.isEmpty())
					throw new Exception("Passageiro com mesmo CPF já cadastrado.\n");

			}

			Passageiro passageiro = new Passageiro(nome, cpf);

			passageiros.add(passageiro);

			System.out.println("Passageiro cadastrado com sucesso.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void editarPassageiro() {
		String option = null;
		do {

			try {
				System.out.println("1 - Alterar nome\n2 - Alterar CPF\n3 - Sair");
				System.out.print("Selecione: ");

				option = scanner.nextLine();

				if (isInt(option)) {
					switch (option) {
					case "1": {
						alteracaoPassageiro(option);
					}
						break;
					case "2": {
						alteracaoPassageiro(option);
					}
						break;
					case "3":
						System.out.print("Voltando ao menu de passageiros.\n");
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("3"));
	}

	public static void alteracaoPassageiro(String tipoAlteracao) {
		CpfValidator cpfValidator = new CpfValidator();

		try {
			System.out.print("Infome o CPF: ");
			String cpf = scanner.nextLine();

			Optional<Passageiro> passageiroEncontrado = passageiros.stream()
					.filter(cobrador -> cobrador.getCpf().equals(cpf)).findFirst();

			if (passageiroEncontrado.isEmpty())
				throw new Exception("Motorista não encontrado.\n");
			else {
				if (tipoAlteracao.equals("1")) {
					System.out.print("Digite novo nome: ");
					String alteracao = scanner.nextLine();
					passageiroEncontrado.ifPresent(cobrador -> {
						cobrador.setNome(alteracao);
					});

				} else {
					System.out.print("Digite o novo CPF(XXX.XXX.XXX-XX): ");
					String alteracao = scanner.nextLine();
					if (cpfValidator.isValidCpf(alteracao)) {
						Optional<Passageiro> passageiroEncontradoMesmoCpf = passageiros.stream()
								.filter(cobrador -> cobrador.getCpf().equals(alteracao)).findFirst();
						if (passageiroEncontradoMesmoCpf.isEmpty()) {
							passageiroEncontrado.ifPresent(passageiro -> {
								passageiro.setCpf(alteracao);
							});
						} else {
							throw new Exception("Passageiro com mesmo CPF já cadastrado.\n");
						}
					} else {
						throw new Exception("CPF inválido. Tente novamente.\n");
					}

				}
				System.out.println("Cobrador editado com sucesso.\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirPassageiro() {
		try {
			System.out.print("CPF do passageiro: ");
			String cpf = scanner.nextLine();

			Optional<Passageiro> passageiroEncontrado = passageiros.stream()
					.filter(passageiro -> passageiro.getCpf().equals(cpf)).findFirst();

			if (passageiroEncontrado.isEmpty())
				throw new Exception("Passageiro não encontrado.\n");
			else
				passageiroEncontrado.ifPresent(passageiro -> {
					passageiros.remove(passageiro);
				});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarPassageiros() {
		System.out.println("------------- Todos Passageiros ---------------");
		for (Passageiro passageiro : passageiros) {
			passageiro.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuPontosDeParada() {
		String option = null;

		do {
			try {
				System.out.println("\n----- Menu Pontos de Parada -----\n" + "1. Cadastrar Ponto de Parada\n"
						+ "2. Editar Ponto de Parada\n" + "3. Excluir Ponto de Parada\n"
						+ "4. Listar Pontos de Parada\n" + "5. Voltar ao Menu Principal");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {
					switch (option) {
					case "1":
						cadastrarPontoDeParada();
						break;
					case "2":
						editarPontoDeParada();
						break;
					case "3":
						excluirPontoDeParada();
						break;
					case "4":
						listarPontosDeParada();
						break;
					case "5":
						System.out.println("Voltando ao Menu Principal.");
						break;
					default:
						System.out.println("Opção Inválida. Tente Novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("5"));
	}

	private static void cadastrarPontoDeParada() {

		String codigo = gerarCodigoAleatorio();

		System.out.print("Localização(Rua): ");
		String localizacao = scanner.nextLine();

		System.out.print("Referência: ");
		String referencia = scanner.nextLine();

		PontoParada ponto = new PontoParada(codigo, localizacao, referencia);

		pontos.add(ponto);
		System.out.println("Ponto cadastrado com sucesso.\n");
	}

	private static void editarPontoDeParada() {
		System.out.print("Código do ponto: ");
		String codigo = scanner.nextLine();

		Optional<PontoParada> pontoEncontrado = pontos.stream().filter(ponto -> ponto.getCodigo().equals(codigo))
				.findFirst();

		if (pontoEncontrado.isPresent()) {
			System.out.print("Localização: ");
			String localizacao = scanner.nextLine();

			System.out.print("Referência: ");
			String referencia = scanner.nextLine();

			pontoEncontrado.ifPresent(ponto -> {
				ponto.setLocalizacao(localizacao);
				ponto.setReferencias(referencia);
			});

			System.out.println("Ponto de parada editado com sucesso.\n");
			return;
		}

		System.out.println("Ponto de parada não encontrado.\n");

	}

	private static void excluirPontoDeParada() {
		try {
			System.out.print("Código do ponto: ");
			String codigo = scanner.nextLine();

			Optional<PontoParada> pontoEncontrado = pontos.stream().filter(ponto -> ponto.getCodigo().equals(codigo))
					.findFirst();

			if (pontoEncontrado.isEmpty())
				throw new Exception("Ponto de parada não encontrado.\n");
			else
				pontoEncontrado.ifPresent(ponto -> {
					pontos.remove(ponto);
				});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void listarPontosDeParada() {
		System.out.println("-------------- Pontos de Parada ---------------");
		for (PontoParada ponto : pontos) {
			ponto.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuTrechos() {
		String option = null;

		do {
			try {

				System.out.println("\n----- Menu Trechos -----\n" + "1. Cadastrar Trecho\n" + "2. Editar Trecho\n"
						+ "3. Excluir Trecho\n" + "4. Listar Trechos\n" + "5. Voltar ao Menu Principal");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1":
						cadastrarTrecho();
						break;
					case "2":
						editarTrecho();
						break;
					case "3":
						excluirTrecho();
						break;
					case "4":
						listarTrechos();
						break;
					case "5":
						System.out.println("Voltando ao Menu Principal.");
						break;
					default:
						System.out.println("Opção Inválida. Tente Novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("5"));
	}

	private static void cadastrarTrecho() {

		try {

			PontoParada pontoOrigem = null;
			PontoParada pontoDestino = null;

			String codigo = gerarCodigoAleatorio();

			System.out.print("Indique o ponto de origem(codigo): ");
			String codigoOrigem = scanner.nextLine();

			Optional<PontoParada> pontoOrigemEncontrado = pontos.stream()
					.filter(ponto -> ponto.getCodigo().equals(codigoOrigem)).findFirst();

			if (pontoOrigemEncontrado.isEmpty())
				throw new Exception("Ponto não encontrado tente novamente.\n");

			pontoOrigem = pontoOrigemEncontrado.get();

			System.out.print("Indique o ponto de destino(codigo): ");
			String codigoDestino = scanner.nextLine();

			Optional<PontoParada> pontoDestinoEncontrado = pontos.stream()
					.filter(ponto -> ponto.getCodigo().equals(codigoDestino)).findFirst();

			if (pontoDestinoEncontrado.isEmpty())
				throw new Exception("Ponto não encontrado tente novamente.\n");

			pontoDestino = pontoDestinoEncontrado.get();

			System.out.print("Insira intervalo estimado(minutos): ");
			String intervaloEstimado = scanner.nextLine();

			if (!isInt(intervaloEstimado))
				throw new WrongInputException();

			Trecho trecho = new Trecho(codigo, pontoOrigem, pontoDestino, Integer.parseInt(intervaloEstimado));

			trechos.add(trecho);

			System.out.println("Trecho cadastrado com sucesso.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} catch (WrongInputException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void editarTrecho() {
		try {

			System.out.print("Código do trecho: ");
			String codigoTrecho = scanner.nextLine();

			Optional<Trecho> trechoEncontrado = trechos.stream()
					.filter(trecho -> trecho.getCodigo().equals(codigoTrecho)).findFirst();

			if (trechoEncontrado.isEmpty())
				throw new Exception("Trecho não encontrado");

			System.out.print("Indique o novo ponto de origem de(codigo): ");
			String codigoOrigem = scanner.nextLine();

			Optional<PontoParada> pontoOrigemEncontrado = pontos.stream()
					.filter(ponto -> ponto.getCodigo().equals(codigoOrigem)).findFirst();

			if (pontoOrigemEncontrado.isEmpty())
				throw new Exception("Ponto não encontrado tente novamente.\n");

			System.out.print("Indique o novo ponto de destino(codigo): ");
			String codigoDestino = scanner.nextLine();

			Optional<PontoParada> pontoDestinoEncontrado = pontos.stream()
					.filter(ponto -> ponto.getCodigo().equals(codigoDestino)).findFirst();

			if (pontoDestinoEncontrado.isEmpty())
				throw new Exception("Ponto não encontrado tente novamente.\n");

			final PontoParada pontoOrigem = pontoOrigemEncontrado.get();
			final PontoParada pontoDestino = pontoDestinoEncontrado.get();

			System.out.print("Insira o novo intervalo estimado(minutos): ");
			String intervaloEstimado = scanner.nextLine();

			if (!isInt(intervaloEstimado))
				throw new WrongInputException();

			trechoEncontrado.ifPresent(trecho -> {
				trecho.setPontoDeParadaOrigem(pontoOrigem);
				trecho.setPontoDeParadaDestino(pontoDestino);
				trecho.setIntervaloEstimado(Integer.parseInt(intervaloEstimado));
			});

			System.out.println("Trecho Editado com sucesso.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} catch (WrongInputException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirTrecho() {
		try {

			System.out.print("Código do trecho: ");
			String codigoTrecho = scanner.nextLine();

			Optional<Trecho> trechoEncontrado = trechos.stream()
					.filter(trecho -> trecho.getCodigo().equals(codigoTrecho)).findFirst();

			if (trechoEncontrado.isEmpty())
				throw new Exception("Trecho não encontrado.\n");

			trechoEncontrado.ifPresent((trecho) -> {
				trechos.remove(trecho);
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarTrechos() {
		System.out.println("------------------- Trechos ------------------- \n");
		for (Trecho trecho : trechos) {
			trecho.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuTrajetos() {
		String option = null;

		do {
			try {

				System.out.println("\n----- Menu Trajetos -----\n" + "1. Cadastrar Trajeto\n" + "2. Editar Trajeto\n"
						+ "3. Excluir Trajeto\n" + "4. Listar Trajetos\n" + "5. Voltar ao Menu Principal");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1":
						cadastrarTrajeto();
						break;
					case "2":
						editarTrajeto();
						break;
					case "3":
						excluirTrajeto();
						break;
					case "4":
						listarTrajetos();
						break;
					case "5":
						System.out.println("Voltando ao Menu Principal.");
						break;
					default:
						System.out.println("Opção Inválida. Tente Novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("5"));
	}

	private static void cadastrarTrajeto() {
		try {

			System.out.print("Quantidade de trechos: ");
			String quantidadeTrechos = scanner.nextLine();

			if (!isInt(quantidadeTrechos))
				throw new WrongInputException();

			int quantidade = Integer.parseInt(quantidadeTrechos);
			List<Trecho> trechosTrajeto = new ArrayList<>();

			listarTrechos();

			while (quantidade != 0) {
				System.out.print("Indique o trecho (código): ");
				String codigoTrecho = scanner.nextLine();

				Optional<Trecho> trechoEncontrado = trechos.stream()
						.filter(trecho -> trecho.getCodigo().equals(codigoTrecho)).findFirst();

				if (trechoEncontrado.isEmpty())
					throw new Exception("Trecho não encontrado. Tente novamente.\n");
				else {
					trechosTrajeto.add(trechoEncontrado.get());
					// trechoEncontrado.ifPresent(trecho -> trechosTrajeto.add(trecho));
					quantidade--;
				}

			}

			System.out.println("Trechos cadastrados.\n");

			String codigoTrajeto = gerarCodigoAleatorio();

			Trajeto trajeto = new Trajeto(codigoTrajeto, trechosTrajeto);

			trajetos.add(trajeto);

			System.out.println("Trajeto cadastrado com sucesso.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} catch (WrongInputException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void editarTrajeto() {
		listarTrajetos();
		try {

			System.out.println("\n1 - Adicionar trecho\n2 - Excluir trecho\n3 - Sair");
			System.out.print("Selecione: ");
			String option = scanner.nextLine();

			if (isInt(option)) {

				switch (option) {
				case "1":
					adicionarTrechoAoTrajeto();
					break;
				case "2":
					excluirTrechoDoTrajeto();
					break;
				case "3":
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				throw new WrongInputException();
			}
		} catch (WrongInputException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void adicionarTrechoAoTrajeto() {
		try {

			System.out.print("Indique o trajeto: ");
			String codigoTrajeto = scanner.nextLine();

			Optional<Trajeto> trajetoEncontrado = trajetos.stream()
					.filter(trajeto -> trajeto.getCodigo().equals(codigoTrajeto)).findFirst();

			if (trajetoEncontrado.isEmpty())
				throw new Exception("Trajeto não encontrado.Tente novamente.\n");

			listarTrechos();
			System.out.print("Selecione o trecho: ");
			String codigoTrecho = scanner.nextLine();

			Optional<Trecho> trechoEncontrado = trechos.stream()
					.filter(trecho -> trecho.getCodigo().equals(codigoTrecho)).findFirst();

			if (trechoEncontrado.isEmpty())
				throw new Exception("Trecho não encontrado.Tente novamente.\n");

			if (trajetoEncontrado.isPresent() && trechoEncontrado.isPresent()) {
				trajetoEncontrado.get().adicionaTrecho(trechoEncontrado.get());
				System.out.println("Trecho adicionado com sucesso.\n");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirTrechoDoTrajeto() {
		try {
			System.out.print("Indique o trajeto: ");
			String codigoTrajeto = scanner.nextLine();

			Optional<Trajeto> trajetoEncontrado = trajetos.stream()
					.filter(trajeto -> trajeto.getCodigo().equals(codigoTrajeto)).findFirst();

			if (trajetoEncontrado.isPresent()) {
				trajetoEncontrado.get().exibirInformacoes();

				System.out.print("Selecione o trecho: ");
				String codigoTrecho = scanner.nextLine();

				Optional<Trecho> trechoEncontrado = trechos.stream()
						.filter(trecho -> trecho.getCodigo().equals(codigoTrecho)).findFirst();

				if (trechoEncontrado.isPresent()) {
					trajetoEncontrado.get().removeTrecho(codigoTrecho);
					System.out.println("Trecho removido com sucesso.\n");
				} else {
					throw new Exception("Trecho não encontrado. Tente novamente.\n");
				}
			} else {
				throw new Exception("Trajeto não encontrado. Tente novamente.\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirTrajeto() {
		try {
			System.out.print("Código do trajeto: ");
			String codigoTrajeto = scanner.nextLine();

			Optional<Trajeto> trajetoEncontrado = trajetos.stream()
					.filter(trajeto -> trajeto.getCodigo().equals(codigoTrajeto)).findFirst();

			if (trajetoEncontrado.isPresent()) {
				trajetos.remove(trajetoEncontrado.get());
				System.out.println("Trajeto removido com sucesso.\n");
			} else
				throw new Exception("Trajeto não encontrado.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	private static void listarTrajetos() {
		System.out.println("--------------- Todos Trajetos ----------------");
		for (Trajeto trajeto : trajetos) {
			trajeto.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuJornadas() {
		String option = null;

		do {
			try {

				System.out.println("\n----- Menu Jornadas -----\n" + "1. Cadastrar Jornada\n" + "2. Iniciar Jornada\n"
						+ "3. Encerrar Jornada\n" + "4. Visualizar Jornadas\n" + "5. Coordenar Jornada\n"
						+ "6. Voltar ao Menu Principal");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1":
						cadastrarJornada();
						break;
					case "2":
						iniciarJornada();
						break;
					case "3":
						encerrarJornada();
						break;
					case "4":
						visualizarJornadas();
						break;
					case "5":
						coordenarJornada();
						break;
					case "6":
						System.out.println("Voltando ao Menu Principal.");
						break;
					default:
						System.out.println("Opção Inválida. Tente Novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("6"));
	}

	private static void cadastrarJornada() {
		Veiculo veiculoSelecionado = null;
		Motorista motoristaSelecionado = null;
		Cobrador cobradorSelecionado = null;
		Trajeto trajetoSelecionado = null;

		String codigoJornada = gerarCodigoAleatorio();

		try {

			listarVeiculos();
			System.out.print("Selecione o veículo(código): ");
			String codigoVeiculo = scanner.nextLine();

			Optional<Veiculo> veiculoEncontrado = veiculos.stream()
					.filter(veiculo -> veiculo.getCodigo().equals(codigoVeiculo)).findFirst();

			if (veiculoEncontrado.isPresent()) {
				veiculoSelecionado = veiculoEncontrado.get();
				System.out.println("Veículo selecionado\n");
			} else
				throw new Exception("Veículo não encontrado.\n");

			listarMotoristas();
			System.out.print("Selecione o motorista(identificação): ");
			String identificacaoMotorista = scanner.nextLine();

			Optional<Motorista> motoristaEncontrado = motoristas.stream()
					.filter(motorista -> motorista.getIdentificacao().equals(identificacaoMotorista)).findFirst();

			if (motoristaEncontrado.isPresent()) {
				motoristaSelecionado = motoristaEncontrado.get();
				System.out.println("Motorista selecionado\n");
			} else
				throw new Exception("Motorista não encontrado.\n");

			listarCobradores();
			System.out.print("Selecione o cobrador(identificação): ");
			String identificacaoCobrador = scanner.nextLine();

			Optional<Cobrador> cobradorEncontrado = cobradores.stream()
					.filter(cobrador -> cobrador.getIdentificacao().equals(identificacaoCobrador)).findFirst();

			if (cobradorEncontrado.isPresent()) {
				cobradorSelecionado = cobradorEncontrado.get();
				System.out.println("Cobrador selecionado\n");
			} else
				throw new Exception("Cobrador não encontrado.\n");

			System.out.println();
			listarTrajetos();
			System.out.print("Selecione o trajeto(código): ");
			String codigoTrajeto = scanner.nextLine();

			Optional<Trajeto> trajetoEncontrado = trajetos.stream()
					.filter(trajeto -> trajeto.getCodigo().equals(codigoTrajeto)).findFirst();

			if (trajetoEncontrado.isPresent()) {
				trajetoSelecionado = trajetoEncontrado.get();
				System.out.println("Trajeto selecionado\n");
			} else
				throw new Exception("Trajeto não encontrado.\n");

			Jornada jornada = new Jornada(codigoJornada, veiculoSelecionado, motoristaSelecionado, cobradorSelecionado,
					trajetoSelecionado);
			jornadas.add(jornada);

			System.out.println("Jornada cadastrada com sucesso.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void iniciarJornada() {
		Date dataHoraInicio = null;

		try {

			System.out.print("Código da Jornada: ");
			String codigoJornada = scanner.nextLine();

			Optional<Jornada> jornadaEncontrada = jornadas.stream()
					.filter(jornada -> jornada.getCodigo().equals(codigoJornada)).findFirst();

			if (jornadaEncontrada.isEmpty())
				throw new Exception("Jornada não encontrada.\n");

			System.out.print("Digite a data e hora de início (dd/MM/yyyy HH:mm:ss): ");
			String entradaInicio = scanner.nextLine();

			SimpleDateFormat formatInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			try {
				dataHoraInicio = formatInicio.parse(entradaInicio);
			} catch (ParseException e) {
				System.out.println(
						"Formato de data e hora inválido. Certifique-se de usar o formato dd/MM/yyyy HH:mm:ss.");
				return;
			}

			for (Jornada jornada : jornadas) {
				if (jornada.getCodigo().equals(codigoJornada)) {
					if (jornada.getJornadaIniciada()) {
						throw new Exception("Esta jornada já foi iniciada");
					} else {
						jornada.setJornadaIniciada(true);
						jornada.setDataHoraInicio(dataHoraInicio);
						System.out.println("Jornada Iniciada.\n");
						return;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void encerrarJornada() {
		Date dataHoraTermino = null;

		try {

			System.out.print("Código da Jornada: ");
			String codigoJornada = scanner.nextLine();

			Optional<Jornada> jornadaEncontrada = jornadas.stream()
					.filter(jornada -> jornada.getCodigo().equals(codigoJornada)).findFirst();

			if (jornadaEncontrada.isEmpty())
				throw new Exception("Jornada não encontrada.\n");

			System.out.print("Digite a data e hora de início (dd/MM/yyyy HH:mm:ss): ");
			String entradaInicio = scanner.nextLine();

			SimpleDateFormat formatInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			try {
				dataHoraTermino = formatInicio.parse(entradaInicio);
			} catch (ParseException e) {
				System.out.println(
						"Formato de data e hora inválido. Certifique-se de usar o formato dd/MM/yyyy HH:mm:ss.");
				return;
			}

			for (Jornada jornada : jornadas) {
				if (jornada.getCodigo().equals(codigoJornada)) {
					if (!jornada.getJornadaIniciada()) {
						throw new Exception("Essa jornada já foi encerrada.\n");
					} else {
						jornada.setJornadaIniciada(false);
						jornada.setDataHoraTermino(dataHoraTermino);
						System.out.println("Jornada Encerrada.\n");
						return;
					}
				}
			}
		} catch (Exception e) {
			System.out.println();
		}
	}

	private static void visualizarJornadas() {
		System.out.println("------------------- Jornadas ------------------");
		for (Jornada jornada : jornadas) {
			jornada.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void coordenarJornada() {
		String option = null;

		do {
			try {

				System.out.println("\n1. Embarcar Passageiro\n2. Desembarcar Passageiro\n3 - Sair");
				System.out.print("Selecione: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1":
						embarcarPassageiro();
						break;
					case "2":
						desembarcarPassageiro();
						break;
					case "3":
						break;
					default:
						System.out.println("Opção Inválida. Tente Novamente.\n");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
				return;
			}

		} while (!option.equals("3"));

	}

	private static void embarcarPassageiro() {
		CpfValidator cpfValidator = new CpfValidator();

		try {
			System.out.print("Indique a jornada(código): ");
			String codigoJornada = scanner.nextLine();

			Optional<Jornada> jornadaEncontrada = jornadas.stream()
					.filter(jornada -> jornada.getCodigo().equals(codigoJornada)).findFirst();

			if (jornadaEncontrada.isEmpty())
				throw new Exception("Jornada não encontrada.\n");
			else {
				if(!jornadaEncontrada.get().getJornadaIniciada())
					throw new Exception("Jornada não iniciada.\n");
			}

			listarPassageiros();
			System.out.print("Indique o passageiro (CPF: XXX.XXX.XXX-XX): ");
			String cpfPassageiro = scanner.nextLine();

			if (!cpfValidator.isValidCpf(cpfPassageiro))
				throw new WrongCpfException();

			Optional<Passageiro> passageiroEncontrado = passageiros.stream()
					.filter(passageiro -> passageiro.getCpf().equals(cpfPassageiro)).findFirst();

			if (passageiroEncontrado.isEmpty())
				throw new Exception("Passageiro não encontrado.\n");

			else {
				if (passageiroEncontrado.get().getCartao() == null) {
					throw new Exception("Passageiro não possui cartão cadastrado. Vai ter que ir a pé.\n");
				}

			}

			System.out.println("\n\nFinja que nesse processo o passageiro passou o cartão.\n\n");

			if (jornadaEncontrada.isPresent() && passageiroEncontrado.isPresent()) {
				double valorPassagem = 4.8;

				if (passageiroEncontrado.get().getCartao().getSaldo() >= valorPassagem) {
					System.out.println("Passagem cobrada");
					passageiroEncontrado.get().getCartao().utilizaCartao(valorPassagem);
					System.out.println("Valor cobrado = R$ " + valorPassagem + "\n" + "Valor Restante no Cartão = "
							+ passageiroEncontrado.get().getCartao().getSaldo() + "\n");

					passageiroEncontrado.get().getCartao().utilizaCartao(valorPassagem);
					jornadaEncontrada.get().embarcarPassageiro();

					System.out.println(
							"Quantidade de Passageiros: " + jornadaEncontrada.get().getQuantidadePassageiros());
				} else {
					throw new Exception("Saldo do cartão insuficiente.\n");
				}
			}
		} catch (WrongCpfException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void desembarcarPassageiro() {
		try {
			System.out.print("Indique a jornada(código): ");
			String codigoJornada = scanner.nextLine();

			Optional<Jornada> jornadaEncontrada = jornadas.stream()
					.filter(jornada -> jornada.getCodigo().equals(codigoJornada)).findFirst();

			if (jornadaEncontrada.isEmpty())
				throw new Exception("Jornada não encontrada.\n");
			else {
				jornadaEncontrada.get().desembarcarPassageiro();
				System.out.println("Quantidade de Passageiros: " + jornadaEncontrada.get().getQuantidadePassageiros());

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void menuCartoesDePassageiro() {
		String option = null;

		do {
			try {

				System.out.println("\n----- Menu Cartões de Passageiro -----\n" + "1. Cadastrar Cartão de Passageiro\n"
						+ "2. Recarregar Cartão de Passageiro\n" + "3. Voltar ao Menu Principal");
				System.out.print("Escolha uma opção: ");

				option = scanner.nextLine();

				if (isInt(option)) {

					switch (option) {
					case "1":
						cadastrarCartaoDePassageiro();
						break;
					case "2":
						recarregarCartaoDePassageiro();
						break;
					case "3":
						System.out.println("Voltando ao Menu Principal.");
						break;
					default:
						System.out.println("Opção Inválida. Tente novamente.");
					}
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.out.println(e.getMessage());
			}
		} while (!option.equals("3"));
	}

	private static void cadastrarCartaoDePassageiro() {
		listarPassageiros();
		CpfValidator cpfValidator = new CpfValidator();

		try {

			System.out.print("Indique o passageiro(CPF: XXX.XXX.XXX-XX): ");
			String cpfPassageiro = scanner.nextLine();

			if (!cpfValidator.isValidCpf(cpfPassageiro))
				throw new WrongCpfException();

			Optional<Passageiro> passageiroEncontrado = passageiros.stream()
					.filter(passageiro -> passageiro.getCpf().equals(cpfPassageiro)).findFirst();

			if (passageiroEncontrado.isEmpty())
				throw new Exception("Passageiro não encontrado.\n");
			else {

				System.out.print("Tipo de cartão (idoso, estudantil ou transporte): ");
				String tipoCartao = scanner.nextLine();

				String numeroCartao= gerarCodigoAleatorio();

				CartaoPassageiro cartao = new CartaoPassageiro(tipoCartao, numeroCartao, 0);

				passageiroEncontrado.get().setCartao(cartao);
				System.out.println("Cartão do passageiro criado com sucesso.\n");
			}

		} catch (WrongCpfException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void recarregarCartaoDePassageiro() {
		listarPassageiros();
		CpfValidator cpfValidator = new CpfValidator();

		try {
			System.out.print("CPF do passageiro (XXX.XXX.XXX-XX): ");
			String cpfPassageiro = scanner.nextLine();

			if (!cpfValidator.isValidCpf(cpfPassageiro))
				throw new WrongCpfException();

			Optional<Passageiro> passageiroEncontrado = passageiros.stream()
					.filter(passageiro -> passageiro.getCpf().equals(cpfPassageiro)).findFirst();

			if (passageiroEncontrado.isEmpty())
				throw new Exception("Passageiro não encontrado.\n");
			else {
				System.out.print("Valor: ");
				double valorRecarga = scanner.nextDouble();
				scanner.nextLine();

				passageiroEncontrado.get().getCartao().recarregarCartao(valorRecarga);

				System.out.println("Cartão recarregado.\n");
			}
		} catch (WrongCpfException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

}