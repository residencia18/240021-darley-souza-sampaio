package com.dansoft.redesocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dansoft.redesocial.model.Postagem;


public interface PostagemRepository extends JpaRepository<Postagem, Integer>{

	List<Postagem> findByCodigo(String codigo);
}
