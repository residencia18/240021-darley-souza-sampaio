package po_02;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exitProgram = false;

		while (!exitProgram) {
			System.out.println("1. Conversor de Moedas");
			System.out.println("2. Jogo de Adivinhação");
			System.out.println("3. Operações com Arrays");
			System.out.println("4. Sair\n");
			System.out.print("Escolha uma opção: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.println("---- Conversor de Moedas ----!");
				System.out.println("1. Real");
				System.out.println("2. Dolar");
				System.out.println("3. Euro");
				System.out.print("Selecione a moeda de entrada: ");

				int sourceCurrency = scanner.nextInt();
				scanner.nextLine();

				System.out.print("Valor à converter: ");
				double value = scanner.nextDouble();
				scanner.nextLine();

				System.out.println();

				System.out.println("1. Real");
				System.out.println("2. Dolar");
				System.out.println("3. Euro");
				System.out.print("Selecione para qual moeda converter: ");

				int targetCurrency = scanner.nextInt();
				scanner.nextLine();

				double result;

				switch (sourceCurrency) {
				case 1:
					if (targetCurrency == 2) {
						result = CurrencyConverter.convertRealToDollar(value);
						System.out.println("Valor em Dolar: " + result);
					} else if (targetCurrency == 3) {
						result = CurrencyConverter.convertRealToEuro(value);
						System.out.println("Valor em Euro: " + result);
					} else {
						System.out.println("Moeda inválida.");
					}
					scanner.nextLine();
					break;
				case 2:
					if (targetCurrency == 1) {
						result = CurrencyConverter.convertDollarToReal(value);
						System.out.println("Valor em Real: " + result);
					} else if (targetCurrency == 3) {
						result = CurrencyConverter.convertDollarToEuro(value);
						System.out.println("Valor em Euro: " + result);
					} else {
						System.out.println("Moeda inválida.");
					}
					scanner.nextLine();
					break;
				case 3:
					if (targetCurrency == 1) {
						result = CurrencyConverter.convertEuroToReal(value);
						System.out.println("Valor em Real: " + result);
					} else if (targetCurrency == 2) {
						result = CurrencyConverter.convertEuroToDollar(value);
						System.out.println("Valor em Dolar: " + result);
					} else {
						System.out.println("Moeda inválida.");
					}
					scanner.nextLine();
					break;
				default:
					System.out.println("Moeda inválida.");
				}
				break;

			case 2:
				GuessingGame game = new GuessingGame();
				game.playGame();
				scanner.nextLine();
				break;
			case 3:
				int[] userArray = ArrayHandler.createArrayFromUserInput();
				int[] randomArray = ArrayHandler.createRandomArray();

				System.out.println("Array do Usuário: " + java.util.Arrays.toString(userArray));
				System.out.println("Array Randômico: " + java.util.Arrays.toString(randomArray));

				int sumUserArray = ArrayHandler.calculateSum(userArray);
				int sumRandomArray = ArrayHandler.calculateSum(randomArray);

				System.out.println("Soma Usuário: " + sumUserArray);
				System.out.println("Soma Randômico: " + sumRandomArray);

				int maxUserArray = ArrayHandler.findMax(userArray);
				int minUserArray = ArrayHandler.findMin(userArray);

				System.out.println("Max Usuário: " + maxUserArray);
				System.out.println("Min Usuário: " + minUserArray);

				scanner.nextLine();
				break;

			case 4:
				exitProgram = true;
				System.out.println("Programa encerrado.");
				break;

			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}

		scanner.close();
	}
}
