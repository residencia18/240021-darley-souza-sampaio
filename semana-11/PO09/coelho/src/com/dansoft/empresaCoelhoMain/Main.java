package com.dansoft.empresaCoelhoMain;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import com.dansoft.empresaCoelhoDao.DAO;

public class Main {
	private static Scanner scanner = new Scanner(System.in);

	public static Connection conn = DAO.getConnection();

	public static void init(String[] args) {

		System.out.println();
		String opcao;
		try {

			do {
				System.out.println("----- Menu Principal -----\n" + "1. Gerenciar Clientes\n" + "2. Gerenciar Imóveis\n"
						+ "3. Gerenciar Faturas\n" + "4. Menu Pagamentos\n" + "5. Sair\n");

				System.out.print("Escolha uma opção: ");
				opcao = scanner.nextLine();
				System.out.println();

				if (isInt(opcao)) {
					switch (opcao) {
					case "1":
						MCliente.menuCliente();
						break;
					case "2":
						MImovel.menuImovel();
						break;
					case "3":
						MFatura.menuFatura();
						break;
					case "4":
						MPagamento.menuPagamento();
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
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}

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

}
