package com.dansoft.formula1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.dansoft.formula1.controller.dto.Formula1DTO;

public class PaisMedia {
	private String pais;
	private double media;

	public PaisMedia(String pais, double media) {
	        this.pais = pais;
	        this.media = media;
	    }

	public String getPais() {
		return pais;
	}

	public double getMedia() {
		return media;
	}
	
	public static ArrayList<PaisMedia> getMediaVitoriasPorPais(ArrayList<Formula1DTO> listaVencedores) {
        ArrayList<PaisMedia> medias = new ArrayList<>();

        Map<String, Integer> totalVitoriasPorPais = new HashMap<>();
        Map<String, Integer> totalPilotosPorPais = new HashMap<>();

        for (Formula1DTO vencedores : listaVencedores) {
            String pais = vencedores.getPais();
            int vitorias = vencedores.getVitorias();
            totalVitoriasPorPais.put(pais, totalVitoriasPorPais.getOrDefault(pais, 0) + vitorias);
            totalPilotosPorPais.put(pais, totalPilotosPorPais.getOrDefault(pais, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : totalVitoriasPorPais.entrySet()) {
            String pais = entry.getKey();
            int totalVitorias = entry.getValue();
            int totalPilotos = totalPilotosPorPais.get(pais);
            double media = (double) totalVitorias / totalPilotos;
            medias.add(new PaisMedia(pais, media));
        }
        

        Collections.sort(medias, Comparator.comparingDouble(PaisMedia::getMedia).reversed());

        return medias;
    }
}
