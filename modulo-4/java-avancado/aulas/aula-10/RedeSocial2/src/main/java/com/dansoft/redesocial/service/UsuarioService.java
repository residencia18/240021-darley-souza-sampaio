package com.dansoft.redesocial.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findUser(Integer id) throws NotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isEmpty())
			throw new NotFoundException();
		return usuarioOptional.get();

	}

	public Usuario saveUser(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public void deleteUser(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	public List<UsuarioDTO> listFriends(Integer id) throws Exception {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));

		List<UsuarioDTO> amigosDTO = usuario.getAmigos().stream().map(UsuarioDTO::new).collect(Collectors.toList());

		return amigosDTO;
	}
}
