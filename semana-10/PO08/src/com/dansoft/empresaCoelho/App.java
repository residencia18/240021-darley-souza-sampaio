package com.dansoft.empresaCoelho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import com.dansoft.persistencia.GerenciadorArquivos;
import com.dansoft.validations.Validations;

public class App {
	private static List<Cliente> clientes = new ArrayList<Cliente>();
	private static List<Imovel> imoveis = new ArrayList<Imovel>();
	private static List<Fatura> faturas = new ArrayList<Fatura>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		GerenciadorArquivos.CarregarArquivo(clientes, "clientes.dat");
		GerenciadorArquivos.CarregarArquivo(imoveis, "imoveis.dat");
		GerenciadorArquivos.CarregarArquivo(faturas, "faturas.dat");

		System.out.println();

		String opcao;

		do {
			System.out.println("----- Menu Principal -----\n" + "1. Gerenciar Clientes\n" + "2. Gerenciar Imóveis\n"
					+ "3. Gerenciar Faturas\n" + "4. Menu Pagamentos\n" + "5. Sair\n");

			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextLine();
			System.out.println();

			if (isInt(opcao)) {
				switch (opcao) {
				case "1":
					menuCliente();
					break;
				case "2":
					menuImovel();
					break;
				case "3":
					menuFatura();
					break;
				case "4":
					menuPagamento();
					break;
				case "5":
					System.out.println("Finalizando Programa......");
					break;
				default:
					System.out.println("Opção Inválida.\n");
				}
			} else {
				System.out.println("Somente números");
			}
		} while (!opcao.equals("5"));

		scanner.close();
	}

	public static boolean isInt(String valor) {
		try {
			Integer.parseInt(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static Date leituraData(String data) throws Exception {
		SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataFormatada = formatData.parse(data);
			return dataFormatada;
		} catch (ParseException e) {
			throw new Exception("Formato de data inválido. Utilize o formato DD/MM/AAAA.");
		}
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

	private static void menuCliente() {
		String option = null;

		do {

			System.out.println("\n----- Menu Cliente -----\n" + "1. Cadastrar Cliente\n" + "2. Editar Cliente\n"
					+ "3. Excluir Cliente\n" + "4. Listar Cliente\n" + "5. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextLine();

			if (isInt(option)) {
				switch (option) {
				case "1":
					cadastrarCliente();
					break;
				case "2":
					editarCliente();
					break;
				case "3":
					excluirCliente();
					break;
				case "4":
					listarClientes();
					break;
				case "5":
					System.out.println("Voltando ao Menu Principal.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}

		} while (!option.equals("5"));
	}

	private static void cadastrarCliente() {
		try {
			Cliente clienteAdd = new Cliente();

			System.out.print("Nome: ");
			String nome = scanner.nextLine();

			clienteAdd.setNome(nome);

			System.out.print("CPF (XXX.XXX.XXX-XX): ");
			String cpf = scanner.nextLine();

			Optional<Cliente> clienteEncontradoMesmoCpf = clientes.stream()
					.filter(cliente -> cliente.getCpf().equals(cpf)).findFirst();

			if (!clienteEncontradoMesmoCpf.isEmpty()) {
				System.out.println("Cliente com mesmo CPF já cadastrado.\n");
				return;
			}

			clienteAdd.setCpf(cpf);

			clientes.add(clienteAdd);

			System.out.println("Cliente cadastrado com sucesso.\n");
			GerenciadorArquivos.SalvarArquivo(clientes, "clientes.dat");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}

	}

	private static void editarCliente() {
		String option = null;
		do {
			System.out.println("1 - Alterar nome\n2 - Alterar CPF\n3 - Sair");
			System.out.print("Selecione: ");

			option = scanner.nextLine();

			if (isInt(option)) {
				switch (option) {
				case "1": {
					alteracaoCliente(option);
				}
					break;
				case "2": {
					alteracaoCliente(option);
				}
					break;
				case "3":
					System.out.print("Voltando ao menu de clientes.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}
		} while (!option.equals("3"));

	}

	private static void alteracaoCliente(String tipoAlteracao) {
		try {
			System.out.print("Infome o CPF: ");
			String cpf = scanner.nextLine();

			Optional<Cliente> clienteEncontrado = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpf))
					.findFirst();

			if (clienteEncontrado.isEmpty())
				throw new Exception("Cliente não encontrado.\n");
			else {
				if (tipoAlteracao.equals("1")) {
					System.out.print("Digite novo nome: ");
					String alteracao = scanner.nextLine();

					clienteEncontrado.ifPresent(cliente -> {
						try {
							cliente.setNome(alteracao);
							System.out.println("Cliente editado com sucesso.\n");
						} catch (Exception e) {
							System.out.println("\n" + e.getMessage() + "\n");
						}
					});

				} else {
					System.out.print("Digite o novo CPF(XXX.XXX.XXX-XX): ");
					String alteracao = scanner.nextLine();
					Optional<Cliente> clienteEncontradoMesmoCpf = clientes.stream()
							.filter(cliente -> cliente.getCpf().equals(alteracao)).findFirst();
					if (clienteEncontradoMesmoCpf.isEmpty()) {
						clienteEncontrado.ifPresent(cliente -> {
							try {
								cliente.setCpf(alteracao);
								System.out.println("Cliente editado com sucesso.\n");
							} catch (Exception e) {
								System.out.println("\n" + e.getMessage() + "\n");
							}
						});
					} else {
						throw new Exception("Cliente com mesmo CPF já cadastrado.\n");
					}

				}
				GerenciadorArquivos.SalvarArquivo(clientes, "clientes.dat");
			}
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	}

	private static void excluirCliente() {
		Validations validationCPF = new Validations();
		try {
			System.out.print("CPF do cliente: ");
			String cpf = scanner.nextLine();

			if (!validationCPF.isValidCpf(cpf)) {
				System.out.println("CPF inválido. Formato correto é XXX.XXX.XXX-XX.\n");
				return;
			}

			Optional<Cliente> clienteEncontrado = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpf))
					.findFirst();

			if (clienteEncontrado.isEmpty()) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			clienteEncontrado.ifPresent(cliente -> {
				clientes.remove(cliente);
				System.out.println("Cliente removido com sucesso.\n");
				GerenciadorArquivos.SalvarArquivo(clientes, "clientes.dat");
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarClientes() {
		System.out.println("------------- Todos os Clientes -------------\n");
		for (Cliente cliente : clientes) {
			cliente.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuImovel() {
		String option = null;

		do {

			System.out.println("\n----- Menu Imóvel -----\n" + "1. Cadastrar Imóvel\n" + "2. Editar Imóvel\n"
					+ "3. Excluir Imóvel\n" + "4. Listar Imóveis\n" + "5. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextLine();

			if (isInt(option)) {
				switch (option) {
				case "1":
					cadastrarImovel();
					break;
				case "2":
					editarImovel();
					break;
				case "3":
					excluirImovel();
					break;
				case "4":
					listarImoveis();
					break;
				case "5":
					System.out.println("Voltando ao Menu Principal.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}

		} while (!option.equals("5"));
	}

	private static void cadastrarImovel() {
		try {
			Imovel imovelAdd = new Imovel();

			String matricula = gerarCodigoAleatorio();

			Optional<Imovel> imovelEncontradoMesmaMatricula = imoveis.stream()
					.filter(imovel -> imovel.getMatricula().equals(matricula)).findFirst();

			if (!imovelEncontradoMesmaMatricula.isEmpty()) {
				System.out.println("Imóvel com mesma matrícula já cadastrado.\n");
				return;
			}

			imovelAdd.setMatricula(matricula);

			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();

			imovelAdd.setEndereco(endereco);

			imovelAdd.setPenultimaLeitura("0");
			imovelAdd.setUltimaLeitura("0");

			System.out.print("CPF do Morador(XXX.XXX.XXX-XX): ");
			String cpf = scanner.nextLine();

			Optional<Cliente> clienteEncontrado = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpf))
					.findFirst();

			if (clienteEncontrado.isEmpty()) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			clienteEncontrado.ifPresent(cliente -> {
				try {
					imoveis.add(imovelAdd);
					cliente.adicionarImovel(imovelAdd);
				} catch (Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
			});

			System.out.println("Imóvel cadastrado com sucesso.\n");
			GerenciadorArquivos.SalvarArquivo(imoveis, "imoveis.dat");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}

	}

	private static void editarImovel() {
		String option = null;
		do {
			System.out.println("1 - Alterar Matrícula\n2 - Alterar Endereço\n3 - Sair\n");
			System.out.print("Selecione: ");

			option = scanner.nextLine();

			if (isInt(option)) {
				switch (option) {
				case "1": {
					alteracaoImovel(option);
				}
					break;
				case "2": {
					alteracaoImovel(option);
				}
					break;
				case "3":
					System.out.print("Voltando ao menu de imóveis.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}
		} while (!option.equals("3"));

	}

	private static void alteracaoImovel(String tipoAlteracao) {
		try {
			System.out.print("Infome a matricula: ");
			String matricula = scanner.nextLine();

			Optional<Imovel> imovelEncontrado = imoveis.stream()
					.filter(imovel -> imovel.getMatricula().equals(matricula)).findFirst();

			if (imovelEncontrado.isEmpty())
				throw new Exception("Imóvel não encontrado.\n");

			if (tipoAlteracao.equals("1")) {
				System.out.print("Digite a nova matrícula: ");
				String alteracao = scanner.nextLine();

				Optional<Imovel> imovelEncontradoMesmaMatricula = imoveis.stream()
						.filter(imovel -> imovel.getMatricula().equals(alteracao)).findFirst();

				if (!imovelEncontradoMesmaMatricula.isEmpty()) {
					imovelEncontrado.ifPresent(imovel -> {
						try {
							imovel.setMatricula(alteracao);
							System.out.println("Imóvel editado com sucesso.\n");
						} catch (Exception e) {
							System.out.println("\n" + e.getMessage() + "\n");
						}
					});
				} else {
					throw new Exception("Imóvem com mesma matrícula já cadastrado.\n");
				}

			} else {
				System.out.print("Digite o novo endereço: ");
				String alteracao = scanner.nextLine();

				imovelEncontrado.ifPresent(imovel -> {
					try {
						imovel.setEndereco(alteracao);
						System.out.println("Imóvel editado com sucesso.\n");
					} catch (Exception e) {
						System.out.println("\n" + e.getMessage() + "\n");
					}
				});

				GerenciadorArquivos.SalvarArquivo(imoveis, "imoveis.dat");
			}
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	}

	private static void excluirImovel() {
		try {
			System.out.print("Matrícula do imóvel: ");
			String matricula = scanner.nextLine();

			Optional<Imovel> imovelEncontrado = imoveis.stream()
					.filter(imovel -> imovel.getMatricula().equals(matricula)).findFirst();

			if (imovelEncontrado.isEmpty()) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			imovelEncontrado.ifPresent(imovel -> {
				imoveis.remove(imovel);
				System.out.println("Imóvel removido com sucesso.\n");
				GerenciadorArquivos.SalvarArquivo(imoveis, "imoveis.dat");
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarImoveis() {
		System.out.println("------------- Todos os Imóveis -------------\n");
		for (Imovel imovel : imoveis) {
			imovel.exibirInformacoes();
		}
		System.out.println("-----------------------------------------------");
	}

	private static void menuFatura() {
		String option = null;

		do {

			System.out.println("\n----- Menu Fatura -----\n" + "1. Cadastrar Fatura\n" + "2. Listar Todas Faturas\n"
					+ "3. Listar Faturas em Aberto\n" + "4. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextLine();

			if (isInt(option)) {
				switch (option) {
				case "1":
					cadastrarFatura();
					break;
				case "2":
					listarFaturas();
					break;
				case "3":
					listarFaturasEmAberto();
					break;
				case "4":
					System.out.println("Voltando ao Menu Principal.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}

		} while (!option.equals("4"));
	}

	private static void cadastrarFatura() {
		try {
			Fatura faturaAdd = new Fatura();

			System.out.print("Matrícula do imóvel: ");
			String matricula = scanner.nextLine();

			Optional<Imovel> imovelEncontrado = imoveis.stream()
					.filter(imovel -> imovel.getMatricula().equals(matricula)).findFirst();

			if (imovelEncontrado.isEmpty()) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			String codigoFatura = gerarCodigoAleatorio();
			faturaAdd.setCodigo(codigoFatura);

			System.out.print("Data da leitura (MM/DD/AAAA): ");
			String dataString = scanner.nextLine();
			Date data = leituraData(dataString);

			faturaAdd.setData(data);

			imovelEncontrado.ifPresent(imovel -> {
				try {
					imovel.setPenultimaLeitura(imovel.getUltimaLeitura());
					faturaAdd.setPenultimaLeitura(imovel.getUltimaLeitura());
				} catch (Exception e) {

					System.out.println("Erro: " + e.getMessage());
				}
			});

			System.out.print("Última Leitura: ");
			String ultimaLeitura = scanner.nextLine();

			int ultimaLeituraInt = Integer.parseInt(ultimaLeitura);
			int penultimaLeituraInt = Integer.parseInt(imovelEncontrado.get().getPenultimaLeitura());

			if (ultimaLeituraInt <= penultimaLeituraInt) {
				System.out.println("A última leitura não pode ser menor ou igual à penúltima.");
				return;
			}

			faturaAdd.setQuitado(false);

			faturaAdd.setUltimaLeiutra(ultimaLeitura);

			imovelEncontrado.ifPresent(imovel -> {
				try {
					imovel.setUltimaLeitura(ultimaLeitura);
					faturaAdd.calculaValor(Integer.parseInt(ultimaLeitura),
							Integer.parseInt(imovel.getPenultimaLeitura()));
					imovel.adicionarFatura(faturaAdd);
				} catch (Exception e) {

					System.out.println("Erro: " + e.getMessage());
				}
			});

			System.out.println("Fatura cadastrada com sucesso.\n");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
		GerenciadorArquivos.SalvarArquivo(imoveis, "imoveis.dat");

	}

	private static void listarFaturas() {
		System.out.print("Matrícula do Imóvel: ");
		String matricula = scanner.nextLine();

		System.out.println("------------- Todos as Faturas -------------\n");

		Optional<Imovel> imovelEncontrado = imoveis.stream().filter(imovel -> imovel.getMatricula().equals(matricula))
				.findFirst();

		if (imovelEncontrado.isEmpty()) {
			System.out.println("Imóvel não encontrado cadastrado.\n");
			return;
		}

		imovelEncontrado.ifPresent(imovel -> {
			imovel.listarFaturas();
		});

		System.out.println("-----------------------------------------------");
	}

	private static void listarFaturasEmAberto() {
		System.out.print("Matrícula do Imóvel: ");
		String matricula = scanner.nextLine();

		System.out.println("------------- Todos as Faturas em Aberto -------------\n");

		Optional<Imovel> imovelEncontrado = imoveis.stream().filter(imovel -> imovel.getMatricula().equals(matricula))
				.findFirst();

		if (imovelEncontrado.isEmpty()) {
			System.out.println("Imóvel não encontrado cadastrado.\n");
			return;
		}

		imovelEncontrado.ifPresent(imovel -> {
			imovel.listarFaturasEmAberto();
		});

		System.out.println("---------------------------------------------------------");
	}

	private static void menuPagamento() {
		String option = null;

		do {

			System.out.println("\n----- Menu Pagamento -----\n" + "1. Pagar Fatura\n" + "2. Listar Pagamentos\n"
					+ "3. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextLine();

			if (isInt(option)) {
				switch (option) {
				case "1":
					pagarFatura();
					break;
				case "2":
					listarPagamentos();
					break;
				case "3":
					System.out.println("Voltando ao Menu Principal.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}

		} while (!option.equals("3"));
	}

	private static void pagarFatura() {
		try {
			Pagamento pagamento = new Pagamento();

			System.out.print("Matrícula do imóvel: ");
			String matricula = scanner.nextLine();

			Optional<Imovel> imovelEncontrado = imoveis.stream()
					.filter(imovel -> imovel.getMatricula().equals(matricula)).findFirst();

			if (imovelEncontrado.isEmpty()) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Código da fatura: ");
			String codigoFatura = scanner.nextLine();

			imovelEncontrado.ifPresent(imovel -> {

				Optional<Fatura> faturaEncontrada = imovel.getFaturas().stream()
						.filter(fatura -> fatura.getCodigo().equals(codigoFatura)).findFirst();

				if (faturaEncontrada.isEmpty()) {
					System.out.println("Fatura não encontrada.\n");
					return;
				}
				
				if(faturaEncontrada.get().getQuitado()) {
					System.out.println("Essa fatura já foi paga.\n");
					return;
				}

				try {
					System.out.print("Data do pagamento (MM/DD/AAAA): ");
					String dataString = scanner.nextLine();
					Date data = leituraData(dataString);

					pagamento.setData(data);

					System.out.print("Insira o valor que irá pagar: ");
					double valor = scanner.nextDouble();
					scanner.nextLine();

					pagamento.setValor(valor);

					faturaEncontrada.ifPresent(fatura -> {
						try {
							if (fatura.getPagamentos().isEmpty()) {
								fatura.realizarPagamento(valor, pagamento);
								if (valor > fatura.getValor())
									pagamento.aplicarReembolso(valor, data);
								System.out.println("Pagamento realizado.\n\n");
							} else {
								double valorAPagar = fatura.getValor() - fatura.calculaTotalPagamentos();
								fatura.realizarPagamento(valor, pagamento);
								if (valor > valorAPagar)
									pagamento.aplicarReembolso((valor - valorAPagar), data);
								System.out.println("Pagamento realizado.\n\n");
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					});

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			});

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
		GerenciadorArquivos.SalvarArquivo(imoveis, "imoveis.dat");
	}

	private static void listarPagamentos() {
		try {
			System.out.print("Matrícula do imóvel: ");
			String matricula = scanner.nextLine();

			Optional<Imovel> imovelEncontrado = imoveis.stream()
					.filter(imovel -> imovel.getMatricula().equals(matricula)).findFirst();

			if (imovelEncontrado.isEmpty()) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Código da fatura: ");
			String codigoFatura = scanner.nextLine();

			imovelEncontrado.ifPresent(imovel -> {

				Optional<Fatura> faturaEncontrada = imovel.getFaturas().stream()
						.filter(fatura -> fatura.getCodigo().equals(codigoFatura)).findFirst();

				if (faturaEncontrada.isEmpty()) {
					System.out.println("Fatura não encontrada.\n");
					return;
				}

				faturaEncontrada.ifPresent(fatura -> {
					fatura.exibirPagamentos();
				});
			});

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	}

}
