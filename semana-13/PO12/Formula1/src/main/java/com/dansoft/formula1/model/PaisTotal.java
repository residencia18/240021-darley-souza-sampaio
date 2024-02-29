package com.dansoft.formula1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.dansoft.formula1.controller.dto.Formula1DTO;

public class PaisTotal {
	private String pais;
	private int vitorias;

	public PaisTotal(String pais, int vitorias) {
	        this.pais = pais;
	        this.vitorias = vitorias;
	    }

	public String getPais() {
		return pais;
	}

	public int getVitorias() {
		return vitorias;
	}
	
	public static ArrayList<PaisTotal> getVitoriasPorPais(ArrayList<Formula1DTO> listaVencedores) {
        ArrayList<PaisTotal> resultados = new ArrayList<>();
        
        Map<String, Integer> vitoriasPorPais = new HashMap<>();
        for (Formula1DTO vencedores : listaVencedores) {
            String pais = vencedores.getPais();
            int vitorias = vencedores.getVitorias();
            vitoriasPorPais.put(pais, vitoriasPorPais.getOrDefault(pais, 0) + vitorias);
        }

        for (Map.Entry<String, Integer> entry : vitoriasPorPais.entrySet()) {
            resultados.add(new PaisTotal(entry.getKey(), entry.getValue()));
        }
        
        Collections.sort(resultados, Comparator.comparingInt(PaisTotal::getVitorias).reversed());

        return resultados;
    }
}
