package com.dansoft.redesocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dansoft.redesocial.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
}
