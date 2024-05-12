package com.dansoft.redesocial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dansoft.redesocial.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByNome(String nome);

	Page<Usuario> findAll(Pageable pageable);

	Page<Usuario> findByIsActive(boolean isActive, Pageable pageable);
}
