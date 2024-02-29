package com.dansoft.formula1.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dansoft.formula1.controller.dto.Formula1DTO;
import com.dansoft.formula1.model.Formula1;
import com.dansoft.formula1.model.PaisMedia;
import com.dansoft.formula1.model.PaisTotal;

@RestController
public class Formula1Controller {

	private static ArrayList<Formula1DTO> listaVencedores = new ArrayList<Formula1DTO>();

	public Formula1Controller() {
		listaVencedores = lerDadosFormula1("pilotos.csv");
	}

	public static ArrayList<Formula1DTO> lerDadosFormula1(String nomeArquivo) {
		ArrayList<Formula1DTO> listaVencedores = new ArrayList<Formula1DTO>();

		try {
			BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
			String linha;
			boolean primeiraLinha = true;

			while ((linha = leitor.readLine()) != null) {
				if (primeiraLinha) {
					primeiraLinha = false;
					continue;
				}

				String[] partes = linha.split(";");

				String nome = partes[0];
				String pais = partes[1];
				int vitorias = Integer.parseInt(partes[2]);

				listaVencedores.add(new Formula1DTO(new Formula1(nome, pais, vitorias)));

			}

			leitor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return listaVencedores;
	}

	@GetMapping("/todos")
	public ArrayList<Formula1DTO> listarTodosVencedores() {
		return listaVencedores;
	}

	@GetMapping("/brasileiros")
	public ArrayList<Formula1DTO> listarVencedoresBrasileiros() {
		ArrayList<Formula1DTO> vencedoresBrasileiros = new ArrayList<Formula1DTO>();

		vencedoresBrasileiros = Formula1.getVencedores(1, listaVencedores);

		return vencedoresBrasileiros;
	}

	@GetMapping("/top5")
	public ArrayList<Formula1DTO> listarVencedoresTop5() {
		ArrayList<Formula1DTO> top5 = new ArrayList<Formula1DTO>();

		top5 = Formula1.getVencedores(2, listaVencedores);

		return top5;
	}

	@GetMapping("/top10")
	public ArrayList<Formula1DTO> listarVencedoresTop10() {
		ArrayList<Formula1DTO> top10 = new ArrayList<Formula1DTO>();

		top10 = Formula1.getVencedores(3, listaVencedores);

		return top10;
	}

	@GetMapping("/porpais")
	public ArrayList<PaisTotal> listarVencedoresPorPais() {
		ArrayList<PaisTotal> vitoriasPorPais = new ArrayList<>();

		vitoriasPorPais = PaisTotal.getVitoriasPorPais(listaVencedores);

		return vitoriasPorPais;
	}

	@GetMapping("/mediaporpais")
	public ArrayList<PaisMedia> listarMediaVencedoresPorPais() {
		ArrayList<PaisMedia> mediaVitorias = new ArrayList<>();

		mediaVitorias = PaisMedia.getMediaVitoriasPorPais(listaVencedores);

		return mediaVitorias;
	}
}
