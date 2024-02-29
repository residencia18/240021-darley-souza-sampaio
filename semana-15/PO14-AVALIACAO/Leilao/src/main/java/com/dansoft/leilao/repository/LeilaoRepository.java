package com.dansoft.leilao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dansoft.leilao.model.Leilao;

public interface LeilaoRepository extends JpaRepository<Leilao, Integer> {

	List<Leilao> findByDescricao(String descricao);

	Optional<Leilao> findById(Long id);

}
