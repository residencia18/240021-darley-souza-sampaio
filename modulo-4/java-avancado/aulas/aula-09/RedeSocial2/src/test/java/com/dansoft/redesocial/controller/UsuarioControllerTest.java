package com.dansoft.redesocial.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.dansoft.redesocial.controller.Form.UsuarioForm;
import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.UsuarioRepository;

public class UsuarioControllerTest {

	@InjectMocks
	private UsuarioController usuarioController;

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private UriComponentsBuilder uriBuilder;

	@Mock
	private UsuarioForm usuarioForm;

	private Usuario usuario1 = new Usuario();
	private Usuario usuario2 = new Usuario();

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@BeforeEach
	private void geradorDadosUsuario() {
		usuario1.setId((long) 1);
		usuario1.setNome("User1");
		usuario1.setEmail("User1@gmail.com");
		usuario1.setSenha("123@User1");

		usuario2.setId((long) 2);
		usuario2.setNome("User2");
		usuario2.setEmail("User2@gmail.com");
		usuario2.setSenha("123@User2");
	}

	@Test
	public void testListarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();

		usuarios.add(usuario1);
		usuarios.add(usuario2);

		when(usuarioRepository.findAll()).thenReturn(usuarios);

		List<UsuarioDTO> response = usuarioController.listaUsuarios(null);

		assertEquals(2, response.size());
		assertEquals("User1", response.get(0).getNome());
		assertEquals("User2", response.get(1).getNome());
	}

	@Test
    public void testListarUsuario() {
        when(usuarioRepository.getReferenceById(1)).thenReturn(usuario1);

        ResponseEntity<UsuarioDTO> response = usuarioController.listarUsuario(1, uriBuilder);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario1.getId(), response.getBody().getId());
        assertEquals(usuario1.getNome(), response.getBody().getNome());
        assertEquals(usuario1.getEmail(), response.getBody().getEmail());
    }

	@Test
	public void testInserirUsuario() {
		try {
			when(usuarioForm.toUsuario()).thenReturn(usuario1);

			ResponseEntity<?> response = usuarioController.inserirUsuario(usuarioForm, uriBuilder);

			verify(usuarioRepository).save(usuario1);

			assertEquals(HttpStatus.CREATED, response.getStatusCode());
			assertEquals(usuario1.getId(), ((UsuarioDTO) response.getBody()).getId());
			assertEquals(usuario1.getNome(), ((UsuarioDTO) response.getBody()).getNome());
			assertEquals(usuario1.getEmail(), ((UsuarioDTO) response.getBody()).getEmail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
