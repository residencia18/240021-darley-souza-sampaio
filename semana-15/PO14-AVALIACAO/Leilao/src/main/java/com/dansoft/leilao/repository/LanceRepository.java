package com.dansoft.leilao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dansoft.leilao.model.Lance;

public interface LanceRepository extends JpaRepository<Lance, Integer> {
	
	List<Lance> findById(Long id);

	List<Lance> findByLeilaoId(Long id);

	List<Lance> findByConcorrenteId(Long id);
}
