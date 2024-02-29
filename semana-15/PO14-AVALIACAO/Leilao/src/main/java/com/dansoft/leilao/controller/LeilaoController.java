package com.dansoft.leilao.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.dansoft.leilao.controller.Form.LeilaoForm;
import com.dansoft.leilao.controller.dto.LeilaoDTO;
import com.dansoft.leilao.model.Leilao;
import com.dansoft.leilao.repository.LeilaoRepository;

@RestController
@RequestMapping("/leilao/")
public class LeilaoController {
	@Autowired
	private LeilaoRepository leilaoRepository;

	@GetMapping
	public List<LeilaoDTO> listaItens(@RequestParam(required = false) String name) {
		List<Leilao> listaUsuarios;
		if (name != null && !name.isEmpty()) {
			listaUsuarios = leilaoRepository.findByDescricao(name);
		} else {
			listaUsuarios = (List<Leilao>) leilaoRepository.findAll();
		}

		List<LeilaoDTO> lista = new ArrayList<>();
		for (Leilao leilao : listaUsuarios) {
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			lista.add(leilaoDTO);
		}
		return lista;
	}

	@GetMapping("/{id}")
	public ResponseEntity<LeilaoDTO> listarItem(@PathVariable Integer id, UriComponentsBuilder uriBuilder) {
		try {
			Leilao leilao = leilaoRepository.getReferenceById(id);
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			uriBuilder.path("/leilao/{id}");
			return ResponseEntity.ok(leilaoDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<LeilaoDTO> inserirItem(@RequestBody LeilaoForm leilaoForm,
			UriComponentsBuilder uriBuilder) {
		Leilao leilao = leilaoForm.toLeilao();
		leilaoRepository.save(leilao);
		LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
		uriBuilder.path("/leilao/{id}");
		URI uri = uriBuilder.buildAndExpand(leilao.getId()).toUri();
		return ResponseEntity.created(uri).body(leilaoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LeilaoDTO> alterarItem(@PathVariable Integer id, @RequestBody LeilaoForm leilaoForm,
			UriComponentsBuilder uriBuilder) {
		try {

			Leilao leilao = leilaoRepository.getReferenceById(id);
			leilao.setDescricao(leilaoForm.getDescricao());
			leilao.setStatus(leilaoForm.getStatus());
			leilao.setValor_minimo(leilaoForm.getValor());

			leilaoRepository.save(leilao);
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			return ResponseEntity.ok(leilaoDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<LeilaoDTO> deletarItem(@PathVariable Integer id,
			UriComponentsBuilder uriBuilder) {
		try {

			Leilao leilao = leilaoRepository.getReferenceById(id);
			leilaoRepository.delete(leilao);
			LeilaoDTO leilaoDTO = new LeilaoDTO(leilao);
			return ResponseEntity.ok(leilaoDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
