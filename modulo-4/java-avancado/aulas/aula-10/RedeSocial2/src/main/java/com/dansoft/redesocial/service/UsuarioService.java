package com.dansoft.redesocial.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findUser(Integer id) {
		return usuarioRepository.getReferenceById(id);
	}
	
	public Usuario saveUser(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deleteUser(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public List<UsuarioDTO> listarAmigos(Integer id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        List<UsuarioDTO> amigosDTO = usuario.getAmigos().stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());

        return amigosDTO;
    }
}
