package calculadoras;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	static Calculadora calculadora = new Calculadora();
	static CalculadoraArray calculadoraArray = new CalculadoraArray();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao = 0;
		do {

			System.out.println("1. Calculadora Símples\n2. Calculadora Array\n2. Sair\n\n");

			System.out.print("Selecione a opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1: {
				calculadoraMenu(scanner);
			}
				break;
			case 2: {
				calculadoraArrayMenu(scanner);
			}
				break;
			case 3:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção Inválida. Tente Novamente!\n\n");
			}
		} while (opcao != 3);

		scanner.close();
	}

	private static void calculadoraMenu(Scanner scanner) {
		int opcao = 0;
		do {
			System.out.println("1. Soma\n2. Subtração\n3. Multiplicação\n4. Divisão\n5. Resto\n6. Sair\n\n");

			System.out.print("Selecione a opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1: {
				Number a = getValue(scanner);
				Number b = getValue(scanner);
				Number resultado = calculadora.soma(a, b);
				printResult(resultado);
			}
				break;
			case 2: {
				Number a = getValue(scanner);
				Number b = getValue(scanner);
				Number resultado = calculadora.subtracao(a, b);
				printResult(resultado);
			}
				break;
			case 3: {
				Number a = getValue(scanner);
				Number b = getValue(scanner);
				Number resultado = calculadora.multiplicacao(a, b);
				printResult(resultado);
			}
				break;
			case 4: {
				Number a = getValue(scanner);
				Number b = getValue(scanner);
				Number resultado = calculadora.divisao(a, b);
				printResult(resultado);
			}
				break;
			case 5: {
				Number a = getValue(scanner);
				Number b = getValue(scanner);
				Number resultado = calculadora.resto(a, b);
				printResult(resultado);
			}
				break;
			case 6:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção Inválida. Tente Novamente!\n\n");
			}
		} while (opcao != 6);
	}

	private static void calculadoraArrayMenu(Scanner scanner) {
		int opcao = 0;
		

		do {
			List<Number> listaValores = new ArrayList<>();
			System.out.println("1. Soma\n2. Subtração\n3. Multiplicação\n4. Divisão\n5. Resto\n6. Sair\n\n");

			System.out.print("Selecione a opção: ");			
			opcao = scanner.nextInt();
			
			System.out.print("Quantos valores deseja realizar a operação: ");
			int quantValores = scanner.nextInt();
			
			switch (opcao) {
			case 1: {
				while(quantValores != 0) {
					listaValores.add(getValue(scanner));
					quantValores--;
				}
				Number resultado = calculadoraArray.somaLista(listaValores);
				printResult(resultado);
			}
				break;
			case 2: {
				while(quantValores != 0) {
					listaValores.add(getValue(scanner));
					quantValores--;
				}
				Number resultado = calculadoraArray.subtracaoLista(listaValores);
				printResult(resultado);
			}
				break;
			case 3: {
				while(quantValores != 0) {
					listaValores.add(getValue(scanner));
					quantValores--;
				}
				Number resultado = calculadoraArray.multiplicacaoLista(listaValores);
				printResult(resultado);
			}
				break;
			case 4: {
				while(quantValores != 0) {
					listaValores.add(getValue(scanner));
					quantValores--;
				}
				Number resultado = calculadoraArray.divisaoLista(listaValores);
				printResult(resultado);
			}
				break;
			case 5: {
				while(quantValores != 0) {
					listaValores.add(getValue(scanner));
					quantValores--;
				}
				Number resultado = calculadoraArray.restoLista(listaValores);
				printResult(resultado);
			}
				break;
			case 6:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção Inválida. Tente Novamente!\n\n");
			}
		} while (opcao != 6);
	}

	private static Number getValue(Scanner scanner) {
		System.out.print("Digite o valor: ");

		if (scanner.hasNextInt()) {
			return scanner.nextInt();
		} else if (scanner.hasNextDouble()) {
			return scanner.nextDouble();
		} else {
			System.out.println("Entrada inválida. Por favor, insira um número.");
			scanner.next();
			return null;
		}
	}

	private static void printResult(Number value) {
		System.out.println("\nResultado: " + value + "\n");
	}

}
