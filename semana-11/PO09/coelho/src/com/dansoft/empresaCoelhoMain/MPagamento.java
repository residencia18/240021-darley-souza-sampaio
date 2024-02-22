package com.dansoft.empresaCoelhoMain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.dansoft.empresaCoelhoClasses.Fatura;
import com.dansoft.empresaCoelhoClasses.Imovel;
import com.dansoft.empresaCoelhoClasses.Pagamento;
import com.dansoft.empresaCoelhoClasses.Reembolso;
import com.dansoft.empresaCoelhoDao.FaturaDao;
import com.dansoft.empresaCoelhoDao.ImovelDao;
import com.dansoft.empresaCoelhoDao.PagamentoDao;
import com.dansoft.empresaCoelhoDao.ReembolsoDao;

public class MPagamento {
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

			imovel = ImovelDao.readUnique(matriculaImovel, Main.conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Matrícula da Fatura: ");
			String matriculaFatura = scanner.nextLine();

			fatura = FaturaDao.readUnique(matriculaFatura, Main.conn);

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

			List<Pagamento> pagamentos = new ArrayList<Pagamento>();
			pagamentos = PagamentoDao.readAll(fatura, Main.conn);

			if (pagamentos.isEmpty()) {
				if (valorPagamento >= fatura.getValor()) {
					if (valorPagamento == fatura.getValor()) {
						PagamentoDao.create(pagamento, fatura, true, Main.conn);
						System.out.println("\nValor do pagamento atual: R$" + valorPagamento);
						System.out.println("\nPagamento realizado com sucesso. Fatura quitada.\n");
					}
					if (valorPagamento > fatura.getValor()) {
						PagamentoDao.create(pagamento, fatura, true, Main.conn);
						pagamento = PagamentoDao.readUnique(pagamento, Main.conn);
						Reembolso reembolso = new Reembolso();
						reembolso.setValor(valorPagamento - fatura.getValor());
						reembolso.setData(data);
						ReembolsoDao.create(reembolso, pagamento, Main.conn);
						System.out.println("\nValor do pagamento atual: R$" + valorPagamento);
						System.out.println("\nPagamento realizado com sucesso. Fatura quitada.\n"
								+ "Houve um reembolso de R$" + reembolso.getValor() + ".\n");
					}
				} else {
					PagamentoDao.create(pagamento, fatura, false, Main.conn);
					System.out.println("\nValor do pagamento atual: R$" + valorPagamento);
					System.out.println("Valor da fatura: R$" + fatura.getValor());
					System.out.println("\nPagamento realizado com sucesso.");
				}
			} else {
				double valorJaPago = 0;
				for (Pagamento pagamentoExistente : pagamentos) {
					valorJaPago = valorJaPago + pagamentoExistente.getValor();
				}
				System.out.println("\nValor já pago: R$" + valorJaPago);
				System.out.println("Valor da fatura: R$" + fatura.getValor());
				System.out.println("Valor do pagamento atual: R$" + valorPagamento);
				if (valorPagamento >= fatura.getValor() - valorJaPago) {
					if (valorPagamento == fatura.getValor() - valorJaPago) {
						PagamentoDao.create(pagamento, fatura, true, Main.conn);
						System.out.println("\nPagamento realizado com sucesso. Fatura quitada.\n");
					}
					if (valorPagamento > fatura.getValor() - valorJaPago) {
						PagamentoDao.create(pagamento, fatura, true, Main.conn);
						pagamento = PagamentoDao.readUnique(pagamento, Main.conn);
						Reembolso reembolso = new Reembolso();
						reembolso.setValor((valorPagamento) - (fatura.getValor() - valorJaPago));

						reembolso.setData(data);
						ReembolsoDao.create(reembolso, pagamento, Main.conn);
						System.out.println("\nPagamento realizado com sucesso. Fatura quitada.\n"
								+ "Houve um reembolso de R$" + reembolso.getValor() + ".\n");
					}
				} else {
					PagamentoDao.create(pagamento, fatura, false, Main.conn);
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

			imovel = ImovelDao.readUnique(matriculaImovel, Main.conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			System.out.print("Matrícula da Fatura: ");
			String matriculaFatura = scanner.nextLine();

			fatura = FaturaDao.readUnique(matriculaFatura, Main.conn);

			if (fatura == null) {
				System.out.println("Fatura não encontrada.\n");
				return;
			}

			pagamentos = PagamentoDao.readAll(fatura, Main.conn);

			if (pagamentos.isEmpty()) {
				System.out.println("Essa fatura não possui pagamentos.");
				return;
			}

			System.out.println("----------------- Todos os Pagamentos -----------------\n");
			fatura.exibirInformacoes();
			for (Pagamento pagamento : pagamentos) {
				pagamento.exibirInformacoes();
				Reembolso reembolso = null;
				reembolso = ReembolsoDao.read(pagamento, Main.conn);
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
