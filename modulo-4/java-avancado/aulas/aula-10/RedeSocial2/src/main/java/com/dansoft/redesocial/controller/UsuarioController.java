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

import com.dansoft.redesocial.controller.Form.UsuarioForm;
import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuarios/")
@Slf4j
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<UsuarioDTO> listaUsuarios(@RequestParam(required = false) String name) {
		List<Usuario> listaUsuarios;

		listaUsuarios = usuarioService.findAll();

		List<UsuarioDTO> lista = new ArrayList<>();
		for (Usuario usuario : listaUsuarios) {
			UsuarioDTO userDTO = new UsuarioDTO(usuario);
			lista.add(userDTO);
		}
		return lista;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> listarUsuario(@PathVariable Integer id, UriComponentsBuilder uriBuilder) {
		try {
			Usuario usuario = usuarioService.findUser(id);

			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			uriBuilder.path("/usuarios/{id}");

			log.info("Usuário com id {} retornado para consulta", id);

			return ResponseEntity.ok(usuarioDTO);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> inserirUsuario(@RequestBody UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
		try {
			Usuario usuario = usuarioForm.toUsuario();

			usuarioService.saveUser(usuario);

			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			uriBuilder.path("/usuarios/{id}");

			URI uri = uriBuilder.buildAndExpand(usuario.getId()).toUri();

			log.info("Usuário com id {} inserido com sucesso no banco.", usuario.getId());
			return ResponseEntity.created(uri).body(usuarioDTO);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alterarUsuario(@PathVariable Integer id, @RequestBody UsuarioForm usuarioForm,
			UriComponentsBuilder uriBuilder) {
		try {

			Usuario usuario = usuarioService.findUser(id);
			usuario.setNome(usuarioForm.getNome());
			usuario.setEmail(usuarioForm.getEmail());
			usuario.setSenha(usuarioForm.getSenha());

			usuarioService.saveUser(usuario);
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			log.info("Dados do usuário com id {} foram alterados com sucesso.", usuario.getId());
			return ResponseEntity.ok(usuarioDTO);

		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioDTO> deletarUsuario(@PathVariable Integer id, @RequestBody UsuarioForm usuarioForm,
			UriComponentsBuilder uriBuilder) {
		try {

			Usuario usuario = usuarioService.findUser(id);
			usuarioService.deleteUser(usuario);
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			log.info("Usuário com id {} removido com sucesso do banco.", usuario.getId());
			return ResponseEntity.ok(usuarioDTO);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/amigos/")
	public ResponseEntity<?> listarAmigos(@PathVariable Integer id) {
		try {
			List<UsuarioDTO> amigosDTO = usuarioService.listFriends(id);
			log.info("Lista de amigos do usuário com id {} retornada com sucesso-- para consulta.", id);
			return ResponseEntity.ok(amigosDTO);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/{id}/amigos/{amigoId}")
	public ResponseEntity<?> adicionarAmigo(@PathVariable Integer id, @PathVariable Integer amigoId,
			UriComponentsBuilder uriBuilder) {
		try {
			Usuario usuario = usuarioService.findUser(id);
			Usuario amigo = usuarioService.findUser(amigoId);

			usuario.getAmigos().add(amigo);
			usuarioService.saveUser(usuario);

			amigo.getAmigos().add(usuario);
			usuarioService.saveUser(amigo);

			URI uri = uriBuilder.path("/{id}/amigos/{amigoId}").buildAndExpand(id, amigoId).toUri();

			log.info("Usuário com id {} adicionado na lista de amigos do usuário com id {}.", amigoId, id);

			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}/amigos/{amigoId}")
	public ResponseEntity<?> removerAmigo(@PathVariable Integer id, @PathVariable Integer amigoId,
			UriComponentsBuilder uriBuilder) {
		try {
			Usuario usuario = usuarioService.findUser(id);
			Usuario amigo = usuarioService.findUser(amigoId);

			usuario.getAmigos().remove(amigo);
			usuarioService.saveUser(usuario);

			amigo.getAmigos().remove(usuario);
			usuarioService.saveUser(amigo);

			URI uri = uriBuilder.path("/{id}/amigos/{amigoId}").buildAndExpand(id, amigoId).toUri();

			log.info("Usuário com id {} removido da lista de amigos do usuário com id {}", amigoId, id);
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}