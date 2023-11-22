package teste.project;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String nome;
		String cpf;
		int idade;
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite seu nome: ");
		nome = scanner.nextLine();

		System.out.print("Digite seu cpf: ");
		cpf = scanner.nextLine();

		System.out.print("Digite sua idade: ");
		idade = scanner.nextInt();

		Cliente newCliente = new Cliente(nome, cpf, idade);

		System.out.println("Nome: " + newCliente.getNome() + '\n' + "CPF: " + newCliente.getCpf() + '\n' + "Idade: "
				+ newCliente.getIdade());

		scanner.close();
	}
}
