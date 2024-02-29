package com.dansoft.leilao.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.dansoft.leilao.controller.Form.ConcorrenteForm;
import com.dansoft.leilao.controller.dto.ConcorrenteDTO;
import com.dansoft.leilao.model.Concorrente;
import com.dansoft.leilao.repository.ConcorrenteRepository;

@RestController
@RequestMapping("/concorrentes/")
public class ConcorrenteController {
	@Autowired
	private ConcorrenteRepository concorrenteRepository;

	@GetMapping
	public List<ConcorrenteDTO> listaPostagensNome() {
		List<Concorrente> listaConcorrentes;

		listaConcorrentes = (List<Concorrente>) concorrenteRepository.findAll();

		List<ConcorrenteDTO> concorrentes = new ArrayList<>();
		for (Concorrente concorrente : listaConcorrentes) {
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			concorrentes.add(concorrenteDTO);
		}
		return concorrentes;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConcorrenteDTO> listaConcorrente(@PathVariable Integer id, UriComponentsBuilder uriBuilder) {
		try {
			Concorrente concorrente = concorrenteRepository.getReferenceById(id);
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			uriBuilder.path("/concorrentes/{id}");
			return ResponseEntity.ok(concorrenteDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ConcorrenteDTO> inserirConcorrente(@RequestBody ConcorrenteForm concorrenteForm, UriComponentsBuilder uriBuilder) {
		Concorrente concorrente = concorrenteForm.toConcorrente();
		concorrenteRepository.save(concorrente);
		ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
		uriBuilder.path("/concorrentes/{id}");
		URI uri = uriBuilder.buildAndExpand(concorrente.getId()).toUri();
		return ResponseEntity.created(uri).body(concorrenteDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ConcorrenteDTO> alterarConcorrente(@PathVariable Integer id, @RequestBody ConcorrenteForm concorrenteForm,
			UriComponentsBuilder uriBuilder) {
		try {
			Concorrente concorrente = concorrenteRepository.getReferenceById(id);
			concorrente.setNome(concorrenteForm.getNome());
			concorrente.setCpf(concorrenteForm.getCpf());
			concorrenteRepository.save(concorrente);
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			return ResponseEntity.ok(concorrenteDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ConcorrenteDTO> deletarConcorrente(@PathVariable Integer id,
			UriComponentsBuilder uriBuilder) {
		try {
			Concorrente concorrente = concorrenteRepository.getReferenceById(id);
			concorrenteRepository.delete(concorrente);
			ConcorrenteDTO concorrenteDTO = new ConcorrenteDTO(concorrente);
			return ResponseEntity.ok(concorrenteDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
