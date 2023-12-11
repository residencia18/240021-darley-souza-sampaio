package project;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int key = 0;
		Calculadora calculadora = new Calculadora();
		Scanner scanner = new Scanner(System.in);

		do {

			System.out.println("1. Soma\n2. Subtração\n3. Multiplicação\n4. Divisão\n5. Resto\n6. Sair\n\n");

			System.out.print("Selecione a opção: ");
			key = scanner.nextInt();

			switch (key) {
			case 1: {
				double a = getValue(scanner);
				double b = getValue(scanner);
				calculadora.soma(a, b);
			}
				break;
			case 2: {
				double a = getValue(scanner);
				double b = getValue(scanner);
				calculadora.subtracao(a, b);
			}
				break;
			case 3: {
				double a = getValue(scanner);
				double b = getValue(scanner);
				calculadora.multiplicacao(a, b);
			}
				break;
			case 4: {
				double a = getValue(scanner);
				double b = getValue(scanner);
				calculadora.divisao(a, b);
			}
				break;
			case 5: {
				double a = getValue(scanner);
				double b = getValue(scanner);
				calculadora.resto(a, b);
			}
				break;
			case 6:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção Inválida. Tente Novamente!\n\n");
			}
		} while (key != 6);

		scanner.close();
	}
	
	private static double getValue(Scanner scanner) {

		System.out.print("Digite o valor: ");
		double value = scanner.nextDouble();
		
		return value;
	}

}
