package com.dansoft.leilao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dansoft.leilao.model.Concorrente;

public interface ConcorrenteRepository extends JpaRepository<Concorrente, Integer> {

	List<Concorrente> findByNome(String nome);

	Optional<Concorrente> findById(Long id);
	
}
