package com.dansoft.redesocial.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.dansoft.redesocial.controller.Form.UsuarioForm;
import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.service.UsuarioServiceV2;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v2/usuarios/")
@Slf4j
public class UsuarioControllerV2 {

	@Autowired
	@Qualifier("usuarioServiceV2")
	private UsuarioServiceV2 usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listaUsuarios() {

		List<Usuario> listaUsuarios = (List<Usuario>) usuarioService.findAll();

		List<UsuarioDTO> lista = new ArrayList<>();
		for (Usuario usuario : listaUsuarios) {
			UsuarioDTO userDTO = new UsuarioDTO(usuario);
			lista.add(userDTO);
		}

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/page/")
	public ResponseEntity<?> listaUsuariosPorPagina(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<Usuario> employeePage = usuarioService.findAllPageable(PageRequest.of(page, size));
		if (employeePage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(employeePage, HttpStatus.OK);
	}

	@GetMapping("/nome/{name}")
	public ResponseEntity<?> listaUsuarioName(@PathVariable String name) {
		try {
			Usuario usuario = new Usuario();
			if (name != null && !name.isEmpty())
				usuario = usuarioService.findByNome(name);

			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> listarUsuario(@PathVariable Integer id) {
		try {
			Usuario usuario = usuarioService.findUser(id);

			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			log.info("Usuário com id {} retornado para consulta", id);

			return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/ativos/")
	public ResponseEntity<?> listaUsuariosAtivos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		try {
			Map<String, Object> usuariosAtivos = usuarioService.findAllActives(page, size);

			return new ResponseEntity<>(usuariosAtivos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> inserirUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {
		try {

			Usuario usuario = usuarioService.saveUser(usuarioForm.toUsuario());

			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			log.info("Usuário com id {} inserido com sucesso no banco.", usuario.getId());
			return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarUsuario(@PathVariable Integer id, @RequestBody @Valid UsuarioForm usuarioForm) {
		try {

			Usuario usuario = usuarioService.userUpdate(id, usuarioForm);

			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			log.info("Dados do usuário com id {} foram alterados com sucesso.", usuario.getId());
			return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);

		} catch (NotFoundException e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {
		try {
			Usuario usuario = usuarioService.deleteUser(id);

			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

			log.info("Usuário com id {} removido com sucesso do banco.", usuario.getId());
			return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/amigos/")
	public ResponseEntity<?> listarAmigos(@PathVariable Integer id) {
		try {
			List<UsuarioDTO> amigosDTO = usuarioService.listFriends(id);
			log.info("Lista de amigos do usuário com id {} retornada com sucesso para consulta.", id);
			return new ResponseEntity<>(amigosDTO, HttpStatus.OK);
		} catch (NotFoundException e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/amigos/{amigoId}")
	public ResponseEntity<?> adicionarAmigo(@PathVariable Integer id, @PathVariable Integer amigoId) {
		try {
			usuarioService.addFriend(id, amigoId);

			log.info("Usuário com id {} adicionado na lista de amigos do usuário com id {}.", amigoId, id);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}/amigos/{amigoId}")
	public ResponseEntity<?> removerAmigo(@PathVariable Integer id, @PathVariable Integer amigoId) {
		try {
			usuarioService.removeFriend(id, amigoId);

			log.info("Usuário com id {} removido da lista de amigos do usuário com id {}", amigoId, id);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}