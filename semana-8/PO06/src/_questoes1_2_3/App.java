package _questoes1_2_3;

import java.io.*;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		//crie os arquivos de entrada e saída para utilizar
		//os arquivos são entrada.txt, saida.txt, origem.txt e destino.txt

		// lerArquivo();
		//salvaArquivo();
		//copiaArquivo();

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
	
	 private static void copiaArquivo() {
	        String nomeArquivoOrigem = "origem.txt";
	        String nomeArquivoDestino = "destino.txt";

	        try {
	            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivoOrigem));
	            BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivoDestino));

	            String linha;

	            while ((linha = leitor.readLine()) != null) {
	                escritor.write(linha);
	                escritor.newLine();
	            }

	            leitor.close();
	            escritor.close();

	            System.out.println("Conteúdo do arquivo copiado com sucesso de " + nomeArquivoOrigem + " para " + nomeArquivoDestino);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
}
