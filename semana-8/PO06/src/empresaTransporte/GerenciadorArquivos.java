package empresaTransporte;

import java.io.*;
import java.util.List;

public class GerenciadorArquivos {

	public static <T> void SalvarArquivo(List<T> lista, String nomeArquivo) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
			oos.writeObject(lista);
		} catch (IOException e) {
			System.out.println("Erro ao salvar os dados: " + e.getMessage());
		}
	}
}
