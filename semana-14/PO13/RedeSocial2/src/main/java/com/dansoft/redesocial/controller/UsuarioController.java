package com.dansoft.redesocial.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.dansoft.redesocial.controller.Form.UsuarioForm;
import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios/")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<UsuarioDTO> listaUsuarios(@RequestParam(required = false) String name) {
		List<Usuario> listaUsuarios;
		if (name != null && !name.isEmpty()) {
			listaUsuarios = usuarioRepository.findByNome(name);
		} else {
			listaUsuarios = (List<Usuario>) usuarioRepository.findAll();
		}

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
			Usuario usuario = usuarioRepository.getReferenceById(id);
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			uriBuilder.path("/usuarios/{id}");
			return ResponseEntity.ok(usuarioDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> inserirUsuario(@RequestBody UsuarioForm usuarioForm,
			UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioForm.toUsuario();
		usuarioRepository.save(usuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		uriBuilder.path("/usuarios/{id}");
		URI uri = uriBuilder.buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alterarUsuario(@PathVariable Integer id, @RequestBody UsuarioForm usuarioForm,
			UriComponentsBuilder uriBuilder) {
		try {

			Usuario usuario = usuarioRepository.getReferenceById(id);
			usuario.setNome(usuarioForm.getNome());
			usuario.setEmail(usuarioForm.getEmail());
			usuario.setSenha(usuarioForm.getSenha());

			usuarioRepository.save(usuario);
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioDTO> deletarUsuario(@PathVariable Integer id, @RequestBody UsuarioForm usuarioForm,
			UriComponentsBuilder uriBuilder) {
		try {

			Usuario usuario = usuarioRepository.getReferenceById(id);
			usuarioRepository.delete(usuario);
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
