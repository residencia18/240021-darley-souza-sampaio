package com.dansoft.redesocial.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.dansoft.redesocial.controller.Form.PostagemForm;
import com.dansoft.redesocial.controller.dto.PostagemDTO;
import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.PostagemRepository;

class PostagemControllerTest {

	@InjectMocks
	private PostagemController postagemController;

	@Mock
	private PostagemRepository postagemRepository;

	@Mock
	private UriComponentsBuilder uriBuilder;

	@Mock
	private PostagemForm postagemForm;

	private Postagem postagem1 = new Postagem();
	private Postagem postagem2 = new Postagem();
	
	private Usuario usuario1 = new Usuario();

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		usuario1.setId((long) 1);
		usuario1.setNome("User1");
		usuario1.setEmail("User1@gmail.com");
		usuario1.setSenha("123@User1");

		postagem1.setId((long) 1);
		postagem1.setTexto("Teste");
		postagem1.setCodigo("TESTE1");
		postagem1.setUsuario(usuario1);
		

		postagem2.setId((long) 2);
		postagem2.setTexto("Teste2");
		postagem2.setCodigo("TESTE2");
		postagem2.setUsuario(usuario1);
	}
	
	@Test
	public void testListarPostagens() {
		List<Postagem> postagens = new ArrayList<>();

		postagens.add(postagem1);
		postagens.add(postagem2);
		
		when(postagemRepository.findAll()).thenReturn(postagens);

		List<PostagemDTO> response = postagemController.listaPostagensNome(null);

		assertEquals(2, response.size());
		assertEquals("Teste", response.get(0).getTexto());
		assertEquals("Teste2", response.get(1).getTexto());
	}
	
	@Test
    public void testListarPostagem() {
        when(postagemRepository.getReferenceById(1)).thenReturn(postagem1);

        ResponseEntity<PostagemDTO> response = postagemController.listaPostagem(1, uriBuilder);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postagem1.getId(), response.getBody().getId());
        assertEquals(postagem1.getTexto(), response.getBody().getTexto());
        assertEquals(postagem1.getDataPostagem(), response.getBody().getDataPostagem());
        assertEquals(postagem1.getCodigo(), response.getBody().getCodigo());
        assertEquals(postagem1.getUsuario(), response.getBody().getUsuario());
    }
	
	@Test
    public void testInserirPostagemIncorreta() throws Exception {
        when(postagemRepository.save(any(Postagem.class))).thenReturn(postagem1);

        ResponseEntity<?> response = postagemController.inserirPostagem(postagemForm, uriBuilder);

        verify(postagemRepository).save(any());
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
    public void testInserirPostagemCorreta() throws Exception {
		postagemForm.setTexto("Teste");
		postagemForm.setUsuario(usuario1);
		
        when(postagemRepository.save(any(Postagem.class))).thenReturn(postagem1);

        ResponseEntity<?> response = postagemController.inserirPostagem(postagemForm, uriBuilder);
        
        System.out.println(response);

        verify(postagemRepository).save(any());
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

}
