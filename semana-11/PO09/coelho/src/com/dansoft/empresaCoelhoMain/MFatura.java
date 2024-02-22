package com.dansoft.empresaCoelhoMain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.dansoft.empresaCoelhoClasses.Fatura;
import com.dansoft.empresaCoelhoClasses.Imovel;
import com.dansoft.empresaCoelhoDao.FaturaDao;
import com.dansoft.empresaCoelhoDao.ImovelDao;

public class MFatura {
	private static Scanner scanner = new Scanner(System.in);

	public static void menuFatura() {
		String option = null;

		do {

			System.out.println("\n----- Menu Fatura -----\n" + "1. Cadastrar Fatura\n" + "2. Listar Todas Faturas\n"
					+ "3. Listar Faturas em Aberto\n" + "4. Editar Data Fatura" + "\n5. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextLine();

			if (Main.isInt(option)) {
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

			imovel = ImovelDao.readUnique(matricula, Main.conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			String matriculaFatura = Main.gerarCodigoAleatorio();
			faturaAdd.setMatricula(matriculaFatura);

			System.out.print("Data da leitura (MM/DD/AAAA): ");
			String dataString = scanner.nextLine();
			Date data = new Date();
			data = Main.leituraData(dataString);
			faturaAdd.setData(data);

			System.out.print("Leitura (KWh): ");
			String ultimaLeitura = scanner.nextLine();

			if (!Main.isInt(ultimaLeitura)) {
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

			FaturaDao.create(faturaAdd, imovel, Main.conn);
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

			imovel = ImovelDao.readUnique(matricula, Main.conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			faturas = FaturaDao.readAll(imovel, Main.conn);

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
			List<Fatura> faturas = new ArrayList<Fatura>();

			System.out.print("Matrícula do Imóvel: ");
			String matricula = scanner.nextLine();

			imovel = ImovelDao.readUnique(matricula, Main.conn);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			faturas = FaturaDao.readAll(imovel, Main.conn);
			
			System.out.println("------------- Todas as Faturas em Aberto -------------\n");
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

			System.out.print("Nova data de leitura (MM/DD/AAAA): ");
			String dataString = scanner.nextLine();
			Date data = Main.leituraData(dataString);
			fatura.setData(data);

			FaturaDao.update(fatura, Main.conn);
			System.out.println("Fatura cadastrada com sucesso.\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}

	}
}
