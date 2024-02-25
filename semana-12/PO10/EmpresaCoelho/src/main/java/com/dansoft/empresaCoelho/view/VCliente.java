package com.dansoft.empresaCoelho.view;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;
import com.dansoft.empresaCoelho.controller.CCliente;
import com.dansoft.empresaCoelho.model.Cliente;
import com.dansoft.validations.Validations;

public class VCliente {
	private static Scanner scanner = new Scanner(System.in);

	public static void menuCliente() throws SQLException {
		String option = null;

		do {

			System.out.println("\n----- Menu Cliente -----\n" + "1. Cadastrar Cliente\n" + "2. Editar Cliente\n"
					+ "3. Excluir Cliente\n" + "4. Listar Clientes\n" + "5. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextLine();

			if (Main.isInt(option)) {
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

			Cliente clienteExistente = null;
			clienteExistente = CCliente.readUnique(cpf);

			if (clienteExistente != null) {
				System.out.println("Cliente com o mesmo CPF já cadastrado.\n");
				return;
			}

			CCliente.create(clienteAdd);
			System.out.println("Cliente cadastrado com sucesso.\n");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private static void editarCliente() {
		String option = null;
		do {
			System.out.println("1 - Alterar nome\n2 - Alterar CPF\n3 - Sair");
			System.out.print("Selecione: ");

			option = scanner.nextLine();

			if (Main.isInt(option)) {
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
			Validations validationCpf = new Validations();
			System.out.print("Infome o CPF (XXX.XXX.XXX-XX): ");
			String cpf = scanner.nextLine();

			if (!validationCpf.isValidCpf(cpf)) {
				System.out.println("CPF inválido.\n");
				return;
			}

			Cliente cliente = null;
			cliente = CCliente.readUnique(cpf);

			if (cliente == null) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			if (tipoAlteracao.equals("1")) {
				System.out.print("Digite novo nome: ");
				String alteracao = scanner.nextLine();

				CCliente.update(cliente, alteracao, tipoAlteracao);
				System.out.println("Cliente editado com sucesso.\n");

			} else {
				System.out.print("Digite o novo CPF(XXX.XXX.XXX-XX): ");
				String alteracao = scanner.nextLine();

				Cliente clienteExistente = CCliente.readUnique(alteracao);

				if (clienteExistente != null) {
					System.out.println("Cliente com o mesmo CPF já cadastrado.\n");
					return;
				}

				CCliente.update(cliente, alteracao, tipoAlteracao);

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
		try {
			Validations validationCPF = new Validations();
			System.out.print("CPF do cliente: ");
			String cpf = scanner.nextLine();

			if (!validationCPF.isValidCpf(cpf)) {
				System.out.println("CPF inválido. Formato correto é XXX.XXX.XXX-XX.\n");
				return;
			}

			Cliente cliente = null;
			cliente = CCliente.readUnique(cpf);

			if (cliente == null) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			CCliente.delete(cliente);

			System.out.println("Cliente deletado com sucesso.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void listarClientes() {
		try {

			List<Cliente> clientes = null;
			clientes = CCliente.readAll();

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

}
