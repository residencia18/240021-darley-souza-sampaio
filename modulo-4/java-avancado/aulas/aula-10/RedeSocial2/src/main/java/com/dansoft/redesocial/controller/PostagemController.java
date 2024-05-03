package com.dansoft.redesocial.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.dansoft.redesocial.controller.Form.PostagemForm;
import com.dansoft.redesocial.controller.dto.PostagemDTO;
import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.service.PostagemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/postagens/")
@Slf4j
public class PostagemController {
	@Autowired
	private PostagemService postagemService;

	@GetMapping
	public List<PostagemDTO> listaPostagensNome(@RequestParam(required = false) String codigo) {
		List<Postagem> listaPostagens;
		if (codigo != null && !codigo.isEmpty())
			listaPostagens = postagemService.findByCode(codigo);
		else
			listaPostagens = (List<Postagem>) postagemService.findAll();

		List<PostagemDTO> lista = new ArrayList<>();
		
		for (Postagem postagem : listaPostagens) {
			PostagemDTO postagemDTO = new PostagemDTO(postagem);
			lista.add(postagemDTO);
		}
		
		return lista;
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostagemDTO> listaPostagem(@PathVariable Integer id, UriComponentsBuilder uriBuilder) {
		try {
			Postagem postagem = postagemService.findPost(id);

			PostagemDTO postagemDTO = new PostagemDTO(postagem);

			uriBuilder.path("/postagens/{id}");

			log.info("Postagem com id {} retornada para consulta.", id);

			return ResponseEntity.ok(postagemDTO);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> inserirPostagem(@RequestBody PostagemForm postagemForm, UriComponentsBuilder uriBuilder)
			throws Exception {
		try {

			Postagem postagem = postagemForm.toPostagem();

			postagemService.savePost(postagem);

			PostagemDTO postagemDTO = new PostagemDTO(postagem);

			uriBuilder.path("/postagens/{id}");

			URI uri = uriBuilder.buildAndExpand(postagem.getId()).toUri();

			log.info("Postagem com id {} inserida com sucesso no banco.", postagem.getId());

			return ResponseEntity.created(uri).body(postagemDTO);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostagemDTO> alterarPostagem(@PathVariable Integer id, @RequestBody PostagemForm postagemForm,
			UriComponentsBuilder uriBuilder) {
		try {
			Postagem postagem = postagemService.findPost(id);

			postagem.setTexto(postagemForm.getTexto());

			postagemService.savePost(postagem);

			PostagemDTO postagemDTO = new PostagemDTO(postagem);

			log.info("Alterações na postagem com id {} realizadas com sucesso.", postagem.getId());

			return ResponseEntity.ok(postagemDTO);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PostagemDTO> deletarPostagem(@PathVariable Integer id, @RequestBody PostagemForm postagemForm,
			UriComponentsBuilder uriBuilder) {
		try {
			Postagem postagem = postagemService.findPost(id);

			postagemService.deletePost(postagem);

			PostagemDTO postagemDTO = new PostagemDTO(postagem);

			log.info("Postagem com id {} removida com sucesso.", id);

			return ResponseEntity.ok(postagemDTO);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}
}
