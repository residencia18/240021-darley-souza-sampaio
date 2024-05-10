package com.dansoft.redesocial.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.dansoft.redesocial.controller.Form.PostagemForm;
import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.PostagemRepository;
import com.github.javafaker.Faker;

@ExtendWith(MockitoExtension.class)
class PostagemServiceTest {

	@Mock
	private PostagemRepository postagemRepository;

	@Mock
	private UsuarioServiceV1 usuarioService;

	@InjectMocks
	private PostagemService postagemService;

	@Autowired
	private Faker faker = new Faker(new Locale("pt-BR"));

	private Usuario usuario;
	private Postagem postagem;

	@BeforeEach
	void setUp() {
		usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		
		postagem = new Postagem();
		postagem.setId(1L);
		postagem.setCodigo("ABC123");
		postagem.setTexto(faker.lorem().toString());
		postagem.setUsuario(usuario);
		postagem.setDataPostagem(faker.date().birthday());
	}

	@Test
	void findAll_ComIdExistenteRetornaListaDePostagens() throws NotFoundException {
		List<Postagem> postagens = new ArrayList<>();
		
		postagens.add(new Postagem());
		
		usuario.setPostagens(postagens);
		
		when(usuarioService.findUser(1)).thenReturn(usuario);

		assertDoesNotThrow(() -> postagemService.findAll(1));
		verify(usuarioService).findUser(any(Integer.class));
		verifyNoMoreInteractions(usuarioService);
	}

	@Test
    void findAll_ComIdInexistenteLancaNotFoundException() throws NotFoundException {
        when(usuarioService.findUser(1)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> postagemService.findAll(1));
        
		verify(usuarioService).findUser(any(Integer.class));
		verifyNoMoreInteractions(usuarioService);
    }

	@Test
	void findPost_ComIdExistenteRetornaPostagem() throws NotFoundException {
		Postagem postagem = new Postagem();
		
		postagem.setId(1L);
		
		when(postagemRepository.findById(1)).thenReturn(Optional.of(postagem));

		assertDoesNotThrow(() -> postagemService.findPost(1));
		
		verify(postagemRepository).findById(any(Integer.class));
		verifyNoMoreInteractions(postagemRepository);
	}

	@Test
    void findPost_ComIdInexistenteLancaNotFoundException() {
        when(postagemRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> postagemService.findPost(1));

		verify(postagemRepository).findById(any(Integer.class));
		verifyNoMoreInteractions(postagemRepository);
    }

	@Test
	void findByCode_ComCodigoExistenteRetornaListaComPostagem() {
		String codigo = "ABC123";
		
		List<Postagem> postagens = new ArrayList<>();
		
		postagens.add(new Postagem());
		
		when(postagemRepository.findByCodigo(codigo)).thenReturn(postagens);

		assertFalse(postagemService.findByCode(codigo).isEmpty());
		
		verify(postagemRepository).findByCodigo(any(String.class));
		verifyNoMoreInteractions(postagemRepository);
	}

	@Test
	void findByCode_ComCodigoInexistenteRetornaListaVazia() {
		String codigo = "XYZ789";
		
		when(postagemRepository.findByCodigo(codigo)).thenReturn(new ArrayList<>());

		assertTrue(postagemService.findByCode(codigo).isEmpty());
		
		verify(postagemRepository).findByCodigo(any(String.class));
		verifyNoMoreInteractions(postagemRepository);
	}

	@Test
	void savePost_RetornaPostagemSalva() {
		Postagem postagem = new Postagem();
		when(postagemRepository.save(postagem)).thenReturn(postagem);

		assertNotNull(postagemService.savePost(postagem));
		
		verify(postagemRepository).save(any(Postagem.class));
		verifyNoMoreInteractions(postagemRepository);
	}

	@Test
	void updatePost_ComIdExistenteRetornaPostagemAtualizada() throws NotFoundException {
		PostagemForm postagemForm = new PostagemForm();
		postagemForm.setTexto("Texto atualizado");
		
		when(postagemRepository.findById(1)).thenReturn(Optional.of(postagem));
		when(postagemRepository.save(postagem)).thenReturn(postagem);

		assertNotNull(postagemService.updatePost(1, postagemForm));

		verify(postagemRepository).findById(any(Integer.class));
		verify(postagemRepository).save(any(Postagem.class));
		verifyNoMoreInteractions(postagemRepository);
	}

	@Test
	void updatePost_ComIdInexistenteLancaNotFoundException() {
		PostagemForm postagemForm = new PostagemForm();
		postagemForm.setTexto("Texto atualizado");
		
		when(postagemRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> postagemService.updatePost(1, postagemForm));
		
		verify(postagemRepository).findById(any(Integer.class));
		verifyNoMoreInteractions(postagemRepository);
	}

	@Test
	void deletePost_RemovePostagemComSucesso() {
		Postagem postagem = new Postagem();
		postagemService.deletePost(postagem);

		verify(postagemRepository, times(1)).delete(postagem);
		
		verify(postagemRepository).delete(any(Postagem.class));
		verifyNoMoreInteractions(postagemRepository);
	}
}
