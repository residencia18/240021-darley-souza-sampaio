package com.dansoft.persistencia;

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

	@SuppressWarnings("unchecked")
	public static <T> void CarregarArquivo(List<T> lista, String nomeArquivo) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
			lista.clear();
			List<?> dados = (List<?>) ois.readObject();
			lista.addAll((List<T>) dados);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado. Criando um novo arquivo " + nomeArquivo);
			SalvarArquivo(lista, nomeArquivo);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Erro ao carregar os dados: " + e.getMessage());
		}
	}
}