package com.dansoft.leilao.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.dansoft.leilao.controller.Form.LanceForm;
import com.dansoft.leilao.controller.dto.LanceDTO;
import com.dansoft.leilao.model.Concorrente;
import com.dansoft.leilao.model.Lance;
import com.dansoft.leilao.model.Leilao;
import com.dansoft.leilao.repository.ConcorrenteRepository;
import com.dansoft.leilao.repository.LanceRepository;
import com.dansoft.leilao.repository.LeilaoRepository;

@RestController
@RequestMapping("/lances/")
public class LanceController {
	@Autowired
	private LanceRepository lanceRepository;

	@Autowired
	private ConcorrenteRepository concorrenteRepository;

	@Autowired
	private LeilaoRepository leilaoRepository;

	@GetMapping
	public List<LanceDTO> listaPostagensNome(@RequestParam(required = false) Long id) {
		List<Lance> listaPostagens;
		if (id != null) {
			listaPostagens = lanceRepository.findById(id);
		} else {
			listaPostagens = (List<Lance>) lanceRepository.findAll();
		}

		List<LanceDTO> lista = new ArrayList<>();
		for (Lance postagem : listaPostagens) {
			LanceDTO postagemDTO = new LanceDTO(postagem);
			lista.add(postagemDTO);
		}
		return lista;
	}

	@GetMapping("/{id}")
	public ResponseEntity<LanceDTO> listaLance(@PathVariable Integer id, UriComponentsBuilder uriBuilder) {
		try {
			Lance lance = lanceRepository.getReferenceById(id);
			LanceDTO lanceDTO = new LanceDTO(lance);
			uriBuilder.path("/lances/{id}");
			return ResponseEntity.ok(lanceDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/leilao={id}")
	public ResponseEntity<List<LanceDTO>> listaLancesPorLeilao(@PathVariable Long id) {
		try {
			List<Lance> lancesDoLeilao = lanceRepository.findByLeilaoId(id);

			if (lancesDoLeilao.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			List<LanceDTO> listaDeLancesDTO = new ArrayList<>();
			for (Lance lance : lancesDoLeilao) {
				listaDeLancesDTO.add(new LanceDTO(lance));
			}

			return ResponseEntity.ok(listaDeLancesDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/concorrente={id}")
	public ResponseEntity<List<LanceDTO>> listaLancesPorConcorrente(@PathVariable Long id) {
		try {
			List<Lance> lancesDoConcorrente = lanceRepository.findByConcorrenteId(id);

			if (lancesDoConcorrente.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			List<LanceDTO> listaDeLancesDTO = new ArrayList<>();
			for (Lance lance : lancesDoConcorrente) {
				listaDeLancesDTO.add(new LanceDTO(lance));
			}

			return ResponseEntity.ok(listaDeLancesDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<LanceDTO> inserirLance(@RequestBody LanceForm lanceForm, UriComponentsBuilder uriBuilder) {
		Concorrente concorrente = concorrenteRepository.findById(lanceForm.getConcorrente().getId()).orElse(null);
		if (concorrente == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		Leilao leilao = leilaoRepository.findById(lanceForm.getLeilao().getId()).orElse(null);
		if (leilao == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		if (leilao.getStatus() == 1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Lance lance = lanceForm.toLance();

		lanceRepository.save(lance);

		LanceDTO lanceDTO = new LanceDTO(lance);

		uriBuilder.path("/lances/{id}");
		URI uri = uriBuilder.buildAndExpand(lance.getId()).toUri();

		return ResponseEntity.created(uri).body(lanceDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LanceDTO> alterarLance(@PathVariable Integer id, @RequestBody LanceForm lanceForm,
			UriComponentsBuilder uriBuilder) {
		try {
			Lance lance = lanceRepository.findById(id).orElse(null);

			if (lance == null) {
				return ResponseEntity.notFound().build();
			}

			Leilao leilao = lance.getLeilao();
			if (leilao.getStatus() == 1) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}

			lance.setValor(lanceForm.getValor());
			lance.setLeilao(lanceForm.getLeilao());
			lance.setConcorrente(lanceForm.getConcorrente());
			lanceRepository.save(lance);

			LanceDTO lanceDTO = new LanceDTO(lance);

			return ResponseEntity.ok(lanceDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<LanceDTO> deletarLance(@PathVariable Integer id, UriComponentsBuilder uriBuilder) {
		try {
			Lance lance = lanceRepository.getReferenceById(id);
			lanceRepository.delete(lance);
			LanceDTO lanceDTO = new LanceDTO(lance);
			return ResponseEntity.ok(lanceDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
