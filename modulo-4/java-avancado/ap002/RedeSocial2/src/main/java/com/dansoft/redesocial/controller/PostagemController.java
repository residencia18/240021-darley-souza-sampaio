package com.dansoft.redesocial.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.dansoft.redesocial.controller.Form.PostagemForm;
import com.dansoft.redesocial.controller.dto.PostagemDTO;
import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.service.PostagemService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/postagens/")
@Slf4j
public class PostagemController {
	@Autowired
	private PostagemService postagemService;

	@GetMapping("/{id}")
	public ResponseEntity<?> listaPostagens(@PathVariable Integer id) {
		try {

			List<Postagem> listaPostagens;

			listaPostagens = (List<Postagem>) postagemService.findAll(id);

			List<PostagemDTO> lista = new ArrayList<>();

			for (Postagem postagem : listaPostagens) {
				PostagemDTO postagemDTO = new PostagemDTO(postagem);
				lista.add(postagemDTO);
			}

			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@PostMapping
	public ResponseEntity<?> inserirPostagem(@RequestBody @Valid PostagemForm postagemForm) throws Exception {
		try {

			Postagem postagem = postagemForm.toPostagem();

			PostagemDTO postagemDTO = new PostagemDTO(postagemService.savePost(postagem));

			log.info("Postagem com id {} inserida com sucesso no banco.", postagem.getId());

			return new ResponseEntity<PostagemDTO>(postagemDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarPostagem(@PathVariable Integer id,
			@RequestBody PostagemForm postagemForm) {
		try {
			Postagem postagem = postagemService.updatePost(id, postagemForm);
			PostagemDTO postagemDTO = new PostagemDTO(postagem);

			log.info("Alterações na postagem com id {} realiza das com sucesso.", postagem.getId());

			return new ResponseEntity<>(postagemDTO, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPostagem(@PathVariable Integer id) {
		try {
			Postagem postagem = postagemService.findPost(id);

			postagemService.deletePost(postagem);

			log.info("Postagem com id {} removida com sucesso.", id);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
