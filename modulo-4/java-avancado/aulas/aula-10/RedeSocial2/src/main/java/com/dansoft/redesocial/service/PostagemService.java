package com.dansoft.redesocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	public List<Postagem> findAll() {
		return postagemRepository.findAll();
	}

	
	public Postagem findPost(Integer id) throws NotFoundException {
		Optional<Postagem> postagemOptional = postagemRepository.findById(id);

		if (postagemOptional.isEmpty())
			throw new NotFoundException();
		return postagemOptional.get();
	}

	public List<Postagem> findByCode(String code) {
		return postagemRepository.findByCodigo(code);
	}

	public Postagem savePost(Postagem post) {
		return postagemRepository.save(post);
	}

	public void deletePost(Postagem post) {
		postagemRepository.delete(post);
	}

}
