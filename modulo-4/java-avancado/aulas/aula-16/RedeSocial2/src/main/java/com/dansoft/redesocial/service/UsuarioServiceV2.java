package com.dansoft.redesocial.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.UsuarioRepository;

@Service
public class UsuarioServiceV2 extends UsuarioServiceV1 {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario findByNome(String nome) {
		return usuarioRepository.findByNome(nome);
	}
		
	public Page<Usuario> findAllPageable(Pageable pageable) {
		Page<Usuario> usuarios = usuarioRepository.findAll(pageable);

		return usuarios;
	}
	
	public Map<String, Object> findAllActives(int page, int size) {
        List<Usuario> usuarios = new ArrayList<>();
        
        Pageable paging = PageRequest.of(page, size);
        
        Page<Usuario> pageUsuarios = usuarioRepository.findByIsActive(true, paging);
        
        usuarios = pageUsuarios.getContent();

        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario))
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("usuariosAtivos", usuariosDTO);
        response.put("paginaAtual", pageUsuarios.getNumber());
        response.put("totalItens", pageUsuarios.getTotalElements());
        response.put("totalPaginas", pageUsuarios.getTotalPages());

        return response;
    }
	

}
