package com.dansoft.empresaCoelho.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.dansoft.empresaCoelho.controller.CFatura;
import com.dansoft.empresaCoelho.controller.CImovel;
import com.dansoft.empresaCoelho.controller.CPagamento;
import com.dansoft.empresaCoelho.controller.CReembolso;
import com.dansoft.empresaCoelho.model.Fatura;
import com.dansoft.empresaCoelho.model.Imovel;
import com.dansoft.empresaCoelho.model.Pagamento;
import com.dansoft.empresaCoelho.model.Reembolso;

public class VPagamento {
	private static Scanner scanner = new Scanner(System.in);

	public static void menuPagamento() {
		String option = null;

		do {

			System.out.println("\n----- Menu Pagamento -----\n" + "1. Pagar Fatura\n" + "2. Listar Pagamentos\n"
					+ "3. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextLine();

			if (Main.isInt(option)) {
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

			imovel = CImovel.readUnique(matriculaImovel);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Matrícula da Fatura: ");
			String matriculaFatura = scanner.nextLine();

			fatura = CFatura.readUnique(matriculaFatura);

			if (fatura == null) {
				System.out.println("Fatura não encontrada.\n");
				return;
			}

			if (fatura.getQuitado()) {
				System.out.println("Essa fatura já foi paga.\n");
				return;
			}

			Pagamento pagamento = new Pagamento();

			pagamento.setMatricula(Main.gerarCodigoAleatorio());

			System.out.print("Data do pagamento (MM/DD/AAAA): ");
			String dataString = scanner.nextLine();
			Date data = Main.leituraData(dataString);

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
			pagamento.setFatura(fatura);

			List<Pagamento> pagamentos = new ArrayList<Pagamento>();
			pagamentos = CPagamento.readAll(fatura);

			if (pagamentos.isEmpty()) {
				if (valorPagamento >= fatura.getValor()) {
					fatura.setQuitado(true);
					CFatura.update(fatura);
					if (valorPagamento == fatura.getValor()) {
						CPagamento.create(pagamento);
						System.out.println("\nValor do pagamento atual: R$" + valorPagamento);
						System.out.println("\nPagamento realizado com sucesso. Fatura quitada.\n");
					}
					if (valorPagamento > fatura.getValor()) {
						System.out.println("Processando pagamento...\n");
						CPagamento.create(pagamento);
						pagamento = CPagamento.readUnique(pagamento.getMatricula());

						Reembolso reembolso = new Reembolso();
						reembolso.setValor(valorPagamento - fatura.getValor());
						reembolso.setData(data);
						reembolso.setPagamento(pagamento);
						CReembolso.create(reembolso);
						
						reembolso = CReembolso.read(pagamento);

						pagamento.setReembolso(reembolso);

						CPagamento.update(pagamento);

						System.out.println("\nValor do pagamento atual: R$" + valorPagamento);
						System.out.println("\nPagamento realizado com sucesso. Fatura quitada.\n"
								+ "Houve um reembolso de R$" + reembolso.getValor() + ".\n");
					}
				} else {
					CPagamento.create(pagamento);
					System.out.println("Valor da fatura: R$" + (fatura.getValor()));
					System.out.println("\nValor do pagamento atual: R$" + valorPagamento);
					System.out.println("\nPagamento realizado com sucesso.");
				}
			} else {
				double valorJaPago = 0;
				for (Pagamento pagamentoExistente : pagamentos) {
					valorJaPago = valorJaPago + pagamentoExistente.getValor();
				}
				System.out.println("Valor da fatura: R$" + fatura.getValor());
				System.out.println("\nValor já pago: R$" + valorJaPago);
				System.out.println("Valor do pagamento atual: R$" + valorPagamento);
				if (valorPagamento >= fatura.getValor() - valorJaPago) {
					fatura.setQuitado(true);
					CFatura.update(fatura);
					if (valorPagamento == fatura.getValor() - valorJaPago) {
						CPagamento.create(pagamento);
						System.out.println("\nPagamento realizado com sucesso. Fatura quitada.\n");
					}
					if (valorPagamento > fatura.getValor() - valorJaPago) {
						System.out.println("Processando pagamento...\n");
						CPagamento.create(pagamento);
						pagamento = CPagamento.readUnique(pagamento.getMatricula());

						Reembolso reembolso = new Reembolso();
						reembolso.setValor((valorPagamento) - fatura.getValor());
						reembolso.setData(data);
						reembolso.setPagamento(pagamento);

						CReembolso.create(reembolso);
						
						reembolso = CReembolso.read(pagamento);

						pagamento.setReembolso(reembolso);

						CPagamento.update(pagamento);
						
						System.out.println("\nPagamento realizado com sucesso. Fatura quitada.\n"
								+ "Houve um reembolso de R$" + reembolso.getValor() + ".\n");
					}
				} else {
					CPagamento.create(pagamento);
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

			imovel = CImovel.readUnique(matriculaImovel);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Matrícula da Fatura: ");
			String matriculaFatura = scanner.nextLine();

			fatura = CFatura.readUnique(matriculaFatura);

			if (fatura == null) {
				System.out.println("Fatura não encontrada.\n");
				return;
			}

			pagamentos = CPagamento.readAll(fatura);

			if (pagamentos.isEmpty()) {
				System.out.println("Essa fatura não possui pagamentos.");
				return;
			}

			System.out.println("----------------- Todos os Pagamentos -----------------\n");
			fatura.exibirInformacoes();
			for (Pagamento pagamento : pagamentos) {
				pagamento.exibirInformacoes();
				Reembolso reembolso = null;
				reembolso = CReembolso.read(pagamento);
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
