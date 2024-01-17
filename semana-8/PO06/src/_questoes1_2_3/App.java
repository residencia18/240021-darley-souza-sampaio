package _questoes1_2_3;

import java.io.*;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		// lerArquivo();
		salvaArquivo();

	}

	private static void lerArquivo() {
		String nomeArquivo = "entrada.txt";

		try {
			System.out.println("---------------- Conteúdo do Arquivo ---------------");
			BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));

			String linha;

			while ((linha = leitor.readLine()) != null) {
				System.out.println(linha);
			}

			leitor.close();
			System.out.println("----------------------------------------------------\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void salvaArquivo() {
		String nomeArquivo = "saida.txt";

		try {

			BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo));

			System.out.println(
					"Digite o que quer salvar no arquivo, pode ser múltiplas linhas. CTRL + Z para finalizar\n\n");
			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				escritor.write(linha);
				escritor.newLine();
			}

			escritor.close();
			scanner.close();

			System.out.println("As linhas foram gravadas com sucesso no arquivo " + nomeArquivo);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
