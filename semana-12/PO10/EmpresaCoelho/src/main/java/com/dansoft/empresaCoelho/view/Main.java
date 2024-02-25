package com.dansoft.empresaCoelho.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.dansoft.empresaCoelho.dao.EntityManagerFactoryManager;

public class Main {
	private static Scanner scanner = new Scanner(System.in);

	public static void init(String[] args) {
		EntityManagerFactoryManager.initConnection();
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
						VCliente.menuCliente();
						break;
					case "2":
						VImovel.menuImovel();
						break;
					case "3":
						VFatura.menuFatura();
						break;
					case "4":
						VPagamento.menuPagamento();
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
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}

		scanner.close();
		EntityManagerFactoryManager.closeEntityManagerFactory();
	}

	public static EntityManager getEntityManager() {
		EntityManagerFactory emf = EntityManagerFactoryManager.getEntityManagerFactory();
		return emf.createEntityManager();
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
			codigo.append(random.nextInt(10));
		}

		return codigo.toString();
	}

}
