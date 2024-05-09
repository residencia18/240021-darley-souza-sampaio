package com.dansoft.redesocial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.controller.Form.PostagemForm;
import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
    @Qualifier("usuarioServiceV1")
	private UsuarioServiceV1 usuarioService;

	public List<Postagem> findAll(Integer id) throws NotFoundException {
		Usuario usuario = usuarioService.findUser(id);

		if (usuario.getPostagens().isEmpty())
			throw new NotFoundException();

		return usuario.getPostagens();
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

	public Postagem updatePost(Integer id, PostagemForm postagemForm) throws NotFoundException {
		Postagem postagem = findPost(id);
		
		if(postagem == null)
			throw new NotFoundException();

		postagem.setTexto(postagemForm.getTexto());
		
		return savePost(postagem);
	}

	public void deletePost(Postagem post) {
		postagemRepository.delete(post);
	}

}
