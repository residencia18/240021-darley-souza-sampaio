package com.dansoft.empresaCoelho;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.dansoft.dao.ClienteDao;
import com.dansoft.dao.DAO;
import com.dansoft.dao.FaturaDao;
import com.dansoft.dao.ImovelDao;
import com.dansoft.dao.PagamentoDao;
import com.dansoft.dao.ReembolsoDao;
import com.dansoft.validations.Validations;

public class App {
	private static Scanner scanner = new Scanner(System.in);

	public static Connection conn = DAO.getConnection();

	public static void main(String[] args) throws Exception {

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
					DAO.closeConnection();
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

		for (int i = 0; i < 15; i++) {
			int numero = random.nextInt(10);
			codigo.append(numero);
		}

		return codigo.toString();
	}

	private static void menuCliente() throws SQLException {
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

			clienteAdd.setCpf(cpf);

			ClienteDao.create(clienteAdd, conn);
			System.out.println("Cliente cadastrado com sucesso.\n");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("\nErro: Cliente com mesmo CPF já cadastrado.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
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
		Cliente cliente = null;
		Validations validationCpf = new Validations();
		try {
			System.out.print("Infome o CPF: ");
			String cpf = scanner.nextLine();

			if (!validationCpf.isValidCpf(cpf)) {
				System.out.println("CPF inválido.\n");
				return;
			}

			cliente = ClienteDao.readUnique(cpf, conn);

			if (cliente == null) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			if (tipoAlteracao.equals("1")) {
				System.out.print("Digite novo nome: ");
				String alteracao = scanner.nextLine();

				ClienteDao.update(cliente, alteracao, tipoAlteracao, conn);
				System.out.println("Cliente editado com sucesso.\n");

			} else {
				System.out.print("Digite o novo CPF(XXX.XXX.XXX-XX): ");
				String alteracao = scanner.nextLine();

				ClienteDao.update(cliente, alteracao, tipoAlteracao, conn);

				System.out.println("Cliente editado com sucesso.\n");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("\nErro: Cliente com mesmo CPF já cadastrado.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void excluirCliente() {
		Cliente cliente = null;
		Validations validationCPF = new Validations();
		try {
			System.out.print("CPF do cliente: ");
			String cpf = scanner.nextLine();

			if (!validationCPF.isValidCpf(cpf)) {
				System.out.println("CPF inválido. Formato correto é XXX.XXX.XXX-XX.\n");
				return;
			}

			cliente = ClienteDao.readUnique(cpf, conn);

			if (cliente == null) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			ClienteDao.delete(cliente, conn);

			System.out.println("Cliente deletado com sucesso.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void listarClientes() {
		try {

			ArrayList<Cliente> clientes = null;
			clientes = ClienteDao.readAll(conn);

			if (clientes == null) {
				System.out.println("Nenhum cliente cadastrado.\n");
				return;
			}

			System.out.println("------------- Todos os Clientes -------------\n");
			for (Cliente cliente : clientes) {
				cliente.exibirInformacoes();
			}
			System.out.println("-----------------------------------------------");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void menuImovel() throws Exception {
		String option = null;

		do {

			System.out.println("\n----- Menu Imóvel -----\n" + "1. Cadastrar Imóvel\n" + "2. Editar Imóvel\n"
					+ "3. Excluir Imóvel\n" + "4. Listar Imóveis\n" + "5. Listar Imóveis de um Cliente\n"
					+ "6. Voltar ao Menu Principal\n");
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
					listarImoveisPorCliente();
					break;
				case "6":
					System.out.println("Voltando ao Menu Principal.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}

		} while (!option.equals("6"));
	}

	private static void cadastrarImovel() {
		try {
			Validations validationCpf = new Validations();
			Cliente cliente = null;
			Imovel imovelAdd = new Imovel();

			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();

			imovelAdd.setEndereco(endereco);
			imovelAdd.setMatricula(gerarCodigoAleatorio());
			imovelAdd.setUltimaLeitura("0");
			imovelAdd.setPenultimaLeitura("0");

			System.out.print("CPF do morador(XXX.XXX.XXX-XX): ");
			String cpf = scanner.nextLine();

			if (!validationCpf.isValidCpf(cpf)) {
				System.out.println("CPF inválido.\n");
				return;
			}

			cliente = ClienteDao.readUnique(cpf, conn);

			if (cliente == null) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			ImovelDao.create(imovelAdd, cliente, conn);
			System.out.println("Imóvel cadastrado com sucesso.\n");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("\nErro: Imóvel com mesmo endereço já cadastrado.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}

	}

	private static void editarImovel() {
		String option = null;
		do {
			System.out.println("1 - Alterar Endereço\n2 - Alterar Morador\n3 - Sair");
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

	private static void alteracaoImovel(String tipoAlteracao) {
		try {
			Imovel imovel = null;
			Cliente cliente = null;
			Validations validationCpf = new Validations();

			System.out.print("Infome a matrícula: ");
			String matricula = scanner.nextLine();

			imovel = ImovelDao.readUnique(matricula, conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			if (tipoAlteracao.equals("1")) {
				System.out.print("Digite novo endereço: ");
				String alteracao = scanner.nextLine();

				ImovelDao.update(imovel, alteracao, tipoAlteracao, conn);
				System.out.println("Imóvel editado com sucesso.\n");

			} else {
				System.out.print("Infome o CPF (XXX.XXX.XXX-XX) do novo morador: ");
				String cpfNovoMorador = scanner.nextLine();

				if (!validationCpf.isValidCpf(cpfNovoMorador)) {
					System.out.println("CPF inválido.\n");
					return;
				}

				cliente = ClienteDao.readUnique(cpfNovoMorador, conn);

				if (cliente == null) {
					System.out.println("Cliente não encontrado.\n");
					return;
				}

				Integer idCliente = cliente.getId();
				String alteracao = idCliente.toString();

				ImovelDao.update(imovel, alteracao, tipoAlteracao, conn);

				System.out.println("Imóvel editado com sucesso.\n");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("\nErro: Imóvel com mesmo endereço já cadastrado.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void excluirImovel() {
		try {
			Imovel imovel = null;

			System.out.print("Matrícula do imóvel: ");
			String matricula = scanner.nextLine();

			imovel = ImovelDao.readUnique(matricula, conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			ImovelDao.delete(imovel, conn);

			System.out.println("Cliente deletado com sucesso.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void listarImoveis() {
		try {

			ArrayList<Imovel> imoveis = null;
			imoveis = ImovelDao.readAll(conn);

			if (imoveis.isEmpty()) {
				System.out.println("Nenhum imóvel cadastrado.\n");
				return;
			}

			System.out.println("------------- Todos os Imóveis -------------\n");
			for (Imovel imovel : imoveis) {
				imovel.exibirInformacoes();
			}
			System.out.println("-----------------------------------------------");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void listarImoveisPorCliente() {
		try {

			Validations validationCpf = new Validations();
			List<Imovel> imoveis = null;
			Cliente cliente = null;

			System.out.print("Infome o CPF do morador: ");
			String cpfMorador = scanner.nextLine();

			if (!validationCpf.isValidCpf(cpfMorador)) {
				System.out.println("CPF inválido.\n");
				return;
			}

			cliente = ClienteDao.readUnique(cpfMorador, conn);

			if (cliente == null) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			imoveis = ImovelDao.readAllPerClient(cliente, conn);

			if (imoveis == null) {
				System.out.println("Esse cliente não possui imóveis cadastrados.\n");
				return;
			}

			System.out.println("------------- Imóveis do Cliente -------------\n");
			cliente.exibirInformacoes();
			System.out.println();
			for (Imovel imovel : imoveis) {
				imovel.exibirInformacoes();
			}
			System.out.println("-----------------------------------------------");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void menuFatura() {
		String option = null;

		do {

			System.out.println("\n----- Menu Fatura -----\n" + "1. Cadastrar Fatura\n" + "2. Listar Todas Faturas\n"
					+ "3. Listar Faturas em Aberto\n" + "4. Editar Data Fatura" + "\n5. Voltar ao Menu Principal\n");
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
					editarFatura();
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

	private static void cadastrarFatura() {
		try {
			Fatura faturaAdd = new Fatura();
			Imovel imovel = null;

			System.out.print("Matrícula do imóvel: ");
			String matricula = scanner.nextLine();

			imovel = ImovelDao.readUnique(matricula, conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			String matriculaFatura = gerarCodigoAleatorio();
			faturaAdd.setMatricula(matriculaFatura);

			System.out.print("Data da leitura (MM/DD/AAAA): ");
			String dataString = scanner.nextLine();
			Date data = new Date();
			data = leituraData(dataString);
			faturaAdd.setData(data);

			System.out.print("Leitura: ");
			String ultimaLeitura = scanner.nextLine();

			if (!isInt(ultimaLeitura)) {
				System.out.println("A leitura deve conter somente números.\n");
				return;
			}

			int ultimaLeituraInt = Integer.parseInt(ultimaLeitura);
			int penultimaLeituraInt = Integer.parseInt(imovel.getUltimaLeitura());

			if (ultimaLeituraInt <= penultimaLeituraInt) {
				System.out.println("A última leitura não pode ser menor ou igual à penúltima.");
				return;
			}

			faturaAdd.setPenultimaLeitura(imovel.getUltimaLeitura());
			faturaAdd.setUltimaLeitura(ultimaLeitura);

			imovel.setPenultimaLeitura(imovel.getUltimaLeitura());
			imovel.setUltimaLeitura(ultimaLeitura);
			faturaAdd.setQuitado(false);

			faturaAdd.calculaValor(ultimaLeituraInt, penultimaLeituraInt);

			FaturaDao.create(faturaAdd, imovel, conn);
			System.out.println("Fatura cadastrada com sucesso.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}

	}

	private static void listarFaturas() {
		try {
			Imovel imovel = null;
			List<Fatura> faturas = null;

			System.out.print("Matrícula do Imóvel: ");
			String matricula = scanner.nextLine();

			imovel = ImovelDao.readUnique(matricula, conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			faturas = FaturaDao.readAll(imovel, conn);

			if (faturas.isEmpty()) {
				System.out.println("Este imóvel não possui faturas cadastradas.\n");
				return;
			}

			System.out.println("------------- Todos as Faturas -------------\n");
			for (Fatura fatura : faturas) {
				fatura.exibirInformacoes();
			}
			System.out.println("-----------------------------------------------");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void listarFaturasEmAberto() {
		try {
			Imovel imovel = null;
			List<Fatura> faturas = null;

			System.out.print("Matrícula do Imóvel: ");
			String matricula = scanner.nextLine();

			imovel = ImovelDao.readUnique(matricula, conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			faturas = FaturaDao.readAll(imovel, conn);

			if (faturas.isEmpty()) {
				System.out.println("Este imóvel não possui faturas cadastradas.\n");
				return;
			}
			if (faturas.isEmpty()) {
				System.out.println("Não existe faturas em aberto para esse imóvel.");
				return;
			}

			System.out.println("------------- Todos as Faturas em Aberto -------------\n");
			for (Fatura fatura : faturas) {
				if (!fatura.getQuitado())
					fatura.exibirInformacoes();
			}
			System.out.println("-----------------------------------------------------");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void editarFatura() {
		try {
			Fatura fatura = null;
			Imovel imovel = null;

			System.out.print("Matrícula do imóvel: ");
			String matriculaImovel = scanner.nextLine();

			imovel = ImovelDao.readUnique(matriculaImovel, conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Matrícula da Fatura: ");
			String matriculaFatura = scanner.nextLine();

			fatura = FaturaDao.readUnique(matriculaFatura, conn);

			if (fatura == null) {
				System.out.println("Fatura não encontrada.\n");
				return;
			}

			System.out.print("Nova data de leitura (MM/DD/AAAA): ");
			String dataString = scanner.nextLine();
			Date data = leituraData(dataString);
			fatura.setData(data);

			FaturaDao.update(fatura, conn);
			System.out.println("Fatura cadastrada com sucesso.\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}

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
			Fatura fatura = null;
			Imovel imovel = null;

			System.out.print("Matrícula do imóvel: ");
			String matriculaImovel = scanner.nextLine();

			imovel = ImovelDao.readUnique(matriculaImovel, conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Matrícula da Fatura: ");
			String matriculaFatura = scanner.nextLine();

			fatura = FaturaDao.readUnique(matriculaFatura, conn);

			if (fatura == null) {
				System.out.println("Fatura não encontrada.\n");
				return;
			}

			if (fatura.getQuitado()) {
				System.out.println("Essa fatura já foi paga.\n");
				return;
			}

			Pagamento pagamento = new Pagamento();

			pagamento.setMatricula(gerarCodigoAleatorio());

			System.out.print("Data do pagamento (MM/DD/AAAA): ");
			String dataString = scanner.nextLine();
			Date data = leituraData(dataString);

			pagamento.setData(data);

			double valorPagamento;
			while (true) {
				System.out.print("Valor a pagar: ");
				if (scanner.hasNextDouble()) {
					valorPagamento = scanner.nextDouble();
					scanner.nextLine();
					break;
				}
				System.out.println("Por favor, insira um valor numérico.");
			}

			pagamento.setValor(valorPagamento);

			List<Pagamento> pagamentos = new ArrayList<Pagamento>();
			pagamentos = PagamentoDao.readAll(fatura, conn);

			if (pagamentos.isEmpty()) {
				if (valorPagamento >= fatura.getValor()) {
					if (valorPagamento == fatura.getValor()) {
						PagamentoDao.create(pagamento, fatura, true, conn);
						System.out.println("Pagamento realizado com sucesso. Fatura quitada.\n");
					}
					if (valorPagamento > fatura.getValor()) {
						PagamentoDao.create(pagamento, fatura, true, conn);
						pagamento = PagamentoDao.readUnique(pagamento, conn);
						Reembolso reembolso = new Reembolso();
						reembolso.setValor(valorPagamento - fatura.getValor());
						reembolso.setData(data);
						ReembolsoDao.create(reembolso, pagamento, conn);
						System.out.println("Pagamento realizado com sucesso. Fatura quitada.\n"
								+ "Houve um reembolso de R$" + reembolso.getValor() + ".\n");
					}
				} else {
					PagamentoDao.create(pagamento, fatura, false, conn);
					System.out.println("Pagamento realizado com sucesso.\n");
				}
			} else {
				double valorJaPago = 0;
				for (Pagamento pagamento2 : pagamentos) {
					valorJaPago = valorJaPago + pagamento2.getValor();
					System.out.println(valorJaPago);
				}
				if (valorPagamento >= fatura.getValor() - valorJaPago) {
					if (valorPagamento == fatura.getValor() - valorJaPago) {
						PagamentoDao.create(pagamento, fatura, true, conn);
						System.out.println("Pagamento realizado com sucesso. Fatura quitada.\n");
					}
					if (valorPagamento > pagamento.getValor() - valorJaPago) {
						PagamentoDao.create(pagamento, fatura, true, conn);
						pagamento = PagamentoDao.readUnique(pagamento, conn);
						Reembolso reembolso = new Reembolso();
						reembolso.setValor(valorPagamento - (fatura.getValor() - valorJaPago));
						reembolso.setData(data);
						ReembolsoDao.create(reembolso, pagamento, conn);
						System.out.println("Pagamento realizado com sucesso. Fatura quitada.\n"
								+ "Houve um reembolso de R$" + reembolso.getValor() + ".\n");
					}
				} else {
					PagamentoDao.create(pagamento, fatura, false, conn);
					System.out.println("Pagamento realizado com sucesso.\n");
				}

			}
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void listarPagamentos() {
		try {
			Fatura fatura = null;
			Imovel imovel = null;
			List<Pagamento> pagamentos = null;

			System.out.print("Matrícula do imóvel: ");
			String matriculaImovel = scanner.nextLine();

			imovel = ImovelDao.readUnique(matriculaImovel, conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Matrícula da Fatura: ");
			String matriculaFatura = scanner.nextLine();

			fatura = FaturaDao.readUnique(matriculaFatura, conn);

			if (fatura == null) {
				System.out.println("Fatura não encontrada.\n");
				return;
			}

			pagamentos = PagamentoDao.readAll(fatura, conn);

			if (pagamentos.isEmpty()) {
				System.out.println("Essa fatura não possui pagamentos.");
				return;
			}

			System.out.println("----------------- Todos os Pagamentos -----------------\n");
			fatura.exibirInformacoes();
			for (Pagamento pagamento : pagamentos) {
				pagamento.exibirInformacoes();
				Reembolso reembolso = null;
				reembolso = ReembolsoDao.read(pagamento, conn);
				if (reembolso != null)
					reembolso.exibirInformacoes();
			}
			System.out.println("-----------------------------------------------------");

		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

}
