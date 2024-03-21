package com.dansoft.redesocial.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.dansoft.redesocial.Redesocial2Application;
import com.dansoft.redesocial.controller.Form.UsuarioForm;
import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios/")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	private static Logger log = LoggerFactory.getLogger(UsuarioController.class);

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
			
			log.info("Usuário com id {} retornado para consulta", id);

			return ResponseEntity.ok(usuarioDTO);
		} catch (Exception e) {
	        log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> inserirUsuario(@RequestBody UsuarioForm usuarioForm,
			UriComponentsBuilder uriBuilder) {
		try {
			Usuario usuario = usuarioForm.toUsuario();
			
			usuarioRepository.save(usuario);
			
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

			Usuario usuario = usuarioRepository.getReferenceById(id);
			usuario.setNome(usuarioForm.getNome());
			usuario.setEmail(usuarioForm.getEmail());
			usuario.setSenha(usuarioForm.getSenha());

			usuarioRepository.save(usuario);
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

			Usuario usuario = usuarioRepository.getReferenceById(id);
			usuarioRepository.delete(usuario);
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
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
            List<UsuarioDTO> amigosDTO = usuario.getAmigos().stream()
                    .map(UsuarioDTO::new)
                    .collect(Collectors.toList());
            
			log.info("Lista de amigos do usuário com id {} retornada com sucesso para consulta.", id);
            return ResponseEntity.ok(amigosDTO);
        } catch (Exception e) {
	        log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

	@PostMapping("/{id}/amigos/{amigoId}")
	public ResponseEntity<?> adicionarAmigo(@PathVariable Integer id, @PathVariable Integer amigoId, UriComponentsBuilder uriBuilder) {
	    try {
	        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
	        Usuario amigo = usuarioRepository.findById(amigoId).orElseThrow(() -> new Exception("Amigo não encontrado"));
	       
	        usuario.getAmigos().add(amigo);
	        usuarioRepository.save(usuario);

	        amigo.getAmigos().add(usuario);
	        usuarioRepository.save(amigo);
	        
	        URI uri = uriBuilder.path("/{id}/amigos/{amigoId}").buildAndExpand(id, amigoId).toUri();
	        
	        log.info("Usuário com id {} adicionado na lista de amigos do usuário com id {}.", amigoId, id);
	        
	        return ResponseEntity.created(uri).build();
	    } catch (Exception e) {
	        log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

	@DeleteMapping("/{id}/amigos/{amigoId}")
	public ResponseEntity<?> removerAmigo(@PathVariable Integer id, @PathVariable Integer amigoId, UriComponentsBuilder uriBuilder) {
	    try {
	        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
	        Usuario amigo = usuarioRepository.findById(amigoId).orElseThrow(() -> new Exception("Amigo não encontrado"));
	        
	        usuario.getAmigos().remove(amigo);
	        usuarioRepository.save(usuario);
	        
	        amigo.getAmigos().remove(usuario);
	        usuarioRepository.save(amigo);
	        
	        URI uri = uriBuilder.path("/{id}/amigos/{amigoId}").buildAndExpand(id, amigoId).toUri();
	        
	        log.info("Usuário com id {} removido da lista de amigos do usuário com id {}", amigoId, id);
	        return ResponseEntity.created(uri).build();
	    } catch (Exception e) {
	        log.error("Ocorreu um erro ao tentar realizar a operação: ", e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
}