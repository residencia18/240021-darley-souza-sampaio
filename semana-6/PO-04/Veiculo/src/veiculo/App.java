package veiculo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int escolha;

		do {
			System.out.println("\nMenu:");
			System.out.println("1. Testar Veículo Genérico");
			System.out.println("2. Testar Carro");
			System.out.println("3. Testar Moto");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");

			try {
				escolha = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, insira um número.");
				scanner.nextLine(); 
				escolha = -1;
			}

			switch (escolha) {
			case 1:
				Veiculo veiculo = criarVeiculo(scanner);
				testarVeiculo(veiculo);
				break;
			case 2:
				Carro carro = criarCarro(scanner);
				testarCarro(carro);
				break;
			case 3:
				Moto moto = criarMoto(scanner);
				testarMoto(moto);
				break;
			case 0:
				System.out.println("Saindo do programa. Até logo!");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}

		} while (escolha != 0);

		scanner.close();
	}

	private static Veiculo criarVeiculo(Scanner scanner) {
		System.out.println("\nDigite os dados do Veículo Genérico:");
		System.out.print("Modelo: ");
		String modelo = scanner.nextLine();
		System.out.print("Cor: ");
		String cor = scanner.nextLine();
		System.out.print("Ano: ");
		int ano = scanner.nextInt();
		return new Veiculo(modelo, cor, ano);
	}

	private static Carro criarCarro(Scanner scanner) {
		System.out.println("\nDigite os dados do Carro:");
		System.out.print("Modelo: ");
		String modelo = scanner.nextLine();
		System.out.print("Cor: ");
		String cor = scanner.nextLine();
		System.out.print("Ano: ");
		int ano = scanner.nextInt();
		return new Carro(modelo, cor, ano);
	}

	private static Moto criarMoto(Scanner scanner) {
		System.out.println("\nDigite os dados da Moto:");
		System.out.print("Modelo: ");
		String modelo = scanner.nextLine();
		System.out.print("Cor: ");
		String cor = scanner.nextLine();
		System.out.print("Ano: ");
		int ano = scanner.nextInt();
		return new Moto(modelo, cor, ano);
	}

	private static void testarVeiculo(Veiculo veiculo) {
		System.out.println("\nVeiculo: " + veiculo.getModelo() + " Cor: " + veiculo.getCor() + " Ano: " + veiculo.getAno());	
		veiculo.ligar();
		veiculo.acelerar();
		veiculo.parar();
	}

	private static void testarCarro(Carro carro) {
		System.out.println("\nCarro: " + carro.getModelo() + " Cor: " + carro.getCor() + " Ano: " + carro.getAno());	
		carro.ligar();
		carro.acelerar();
		carro.parar();
		carro.abrirPorta();
	}

	private static void testarMoto(Moto moto) {
		System.out.println("\nMoto: " + moto.getModelo() + " Cor: " + moto.getCor() + " Ano: " + moto.getAno());
		moto.ligar();
		moto.acelerar();
		moto.parar();
		moto.empinar();
	}
	
}
