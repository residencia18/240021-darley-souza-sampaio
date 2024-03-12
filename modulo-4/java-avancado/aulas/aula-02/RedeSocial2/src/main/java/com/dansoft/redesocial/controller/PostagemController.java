package com.dansoft.redesocial.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.dansoft.redesocial.controller.Form.PostagemForm;
import com.dansoft.redesocial.controller.dto.PostagemDTO;
import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens/")
public class PostagemController {
	@Autowired
	private PostagemRepository postagemRepository;

	@GetMapping
	public List<PostagemDTO> listaPostagensNome(@RequestParam(required = false) String codigo) {
		List<Postagem> listaPostagens;
		if (codigo != null && !codigo.isEmpty()) {
			listaPostagens = postagemRepository.findByCodigo(codigo);
		} else {
			listaPostagens = (List<Postagem>) postagemRepository.findAll();
		}

		List<PostagemDTO> lista = new ArrayList<>();
		for (Postagem postagem: listaPostagens) {
			PostagemDTO postagemDTO = new PostagemDTO(postagem);
			lista.add(postagemDTO);
		}
		return lista;
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostagemDTO> listaPostagem(@PathVariable Integer id, UriComponentsBuilder uriBuilder) {
		try {
			Postagem postagem = postagemRepository.getReferenceById(id);
			PostagemDTO postagemDTO = new PostagemDTO(postagem);
			uriBuilder.path("/postagens/{id}");
			return ResponseEntity.ok(postagemDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<PostagemDTO> inserirPostagem(@RequestBody PostagemForm postagemForm,
			UriComponentsBuilder uriBuilder) {
		Postagem postagem = postagemForm.toPostagem();
		postagemRepository.save(postagem);
		PostagemDTO postagemDTO = new PostagemDTO(postagem);
		uriBuilder.path("/postagens/{id}");
		URI uri = uriBuilder.buildAndExpand(postagem.getId()).toUri();
		return ResponseEntity.created(uri).body(postagemDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostagemDTO> alterarPostagem(@PathVariable Integer id, @RequestBody PostagemForm postagemForm,
			UriComponentsBuilder uriBuilder) {
		try {
			Postagem postagem = postagemRepository.getReferenceById(id);
			postagem.setTexto(postagemForm.getTexto());
			postagemRepository.save(postagem);
			PostagemDTO postagemDTO = new PostagemDTO(postagem);
			return ResponseEntity.ok(postagemDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PostagemDTO> deletarPostagem(@PathVariable Integer id, @RequestBody PostagemForm postagemForm,
			UriComponentsBuilder uriBuilder) {
		try {

			Postagem postagem = postagemRepository.getReferenceById(id);
			postagemRepository.delete(postagem);
			PostagemDTO postagemDTO = new PostagemDTO(postagem);
			return ResponseEntity.ok(postagemDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
