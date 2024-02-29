package com.dansoft.formula1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.dansoft.formula1.controller.dto.Formula1DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Formula1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pais;
	private String piloto;
	private int vitorias;

	public Formula1(String pais, String piloto, int vitorias) {
		this.pais = pais;
		this.piloto = piloto;
		this.vitorias = vitorias;
	}

	public Formula1() {
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPiloto() {
		return piloto;
	}

	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	
	public static ArrayList<Formula1DTO> getVencedores(int caso, ArrayList<Formula1DTO> listaVencedores) {
		ArrayList<Formula1DTO> vencedoresCategoria = new ArrayList<Formula1DTO>();

		switch (caso) {
		case 1: {
			vencedoresCategoria = (ArrayList<Formula1DTO>) listaVencedores.stream().filter(vencedor -> "Brasil".equals(vencedor.getPais()))
					.collect(Collectors.toList());
		}
			break;
		case 2: {
			Collections.sort(listaVencedores, Comparator.comparingInt(Formula1DTO::getVitorias).reversed());
			vencedoresCategoria.addAll(listaVencedores.subList(0, Math.min(5, listaVencedores.size())));
			break;
		}
		case 3: {
			Collections.sort(listaVencedores, Comparator.comparingInt(Formula1DTO::getVitorias).reversed());
			vencedoresCategoria.addAll(listaVencedores.subList(0, Math.min(10, listaVencedores.size())));
		}
			break;
		default:
			vencedoresCategoria = null;

		}
		return vencedoresCategoria;
	}

}
