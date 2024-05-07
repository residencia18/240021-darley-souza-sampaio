package com.dansoft.redesocial.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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

import com.dansoft.redesocial.controller.Form.UsuarioForm;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.UsuarioRepository;
import com.github.javafaker.Faker;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;

	@Mock
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Faker faker = new Faker(new Locale("pt-BR"));

	private Usuario usuario;

	@BeforeEach
	void setUp() {
		usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setSenha(faker.internet().password(8, 16, true, true, true));
	}

	@Test
	void findAll_RetornaListaDeUsuariosAtivos() {
		List<Usuario> usuarios = new ArrayList<>();

		usuarios.add(usuario);

		when(usuarioRepository.findAll()).thenReturn(usuarios);

		List<Usuario> usuariosAtivos = usuarioService.findAll();

		verify(usuarioRepository).findAll();
		verifyNoMoreInteractions(usuarioRepository);

		assertThat(usuariosAtivos).isNotNull();
		assertThat(usuariosAtivos).contains(usuario);
	}

	@Test
	void findAll_RetornaListaNaoVazia() {
		List<Usuario> usuarios = new ArrayList<>();

		usuarios.add(usuario);

		when(usuarioRepository.findAll()).thenReturn(usuarios);

		List<Usuario> usuariosAtivos = usuarioService.findAll();

		verify(usuarioRepository).findAll();
		verifyNoMoreInteractions(usuarioRepository);

		assertFalse(usuariosAtivos.isEmpty());
	}

	@Test
    void findUser_ComIdValidoRetornaUsuario() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        assertDoesNotThrow(() -> usuarioService.findUser(1));

        verify(usuarioRepository).findById(any(Integer.class));
        verifyNoMoreInteractions(usuarioRepository);
    }

	@Test
    void findUser_ComIdInvalidoLancaNotFoundException() {
        when(usuarioRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usuarioService.findUser(2));
    
        verify(usuarioRepository).findById(any(Integer.class));
        verifyNoMoreInteractions(usuarioRepository);
    }

	@Test
    void saveUser_RetornaUsuarioSalvo() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario usuarioSalvo = usuarioService.saveUser(usuario);
        
        verify(usuarioRepository).save(any(Usuario.class));
        verifyNoMoreInteractions(usuarioRepository);

        assertEquals(usuario, usuarioSalvo);
    }

	@Test
    void deleteUser_ComIdValidoMarcaUsuarioComoExcluido() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        assertDoesNotThrow(() -> usuarioService.deleteUser(1));

        verify(usuarioRepository).save(any(Usuario.class));
        verifyNoMoreInteractions(usuarioRepository);

        assertTrue(usuario.getDeleted());
    }

	@Test
    void deleteUser_ComIdInvalidoLancaNotFoundException() {
        when(usuarioRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usuarioService.deleteUser(2));

        verify(usuarioRepository, never()).save(any(Usuario.class));
        verifyNoMoreInteractions(usuarioRepository);
    }

	@Test
	void userUpdate_ComIdValidoEFormularioValidoAtualizaUsuario() {
		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm.setNome(faker.name().fullName());
		usuarioForm.setEmail(faker.internet().emailAddress());
		usuarioForm.setSenha(faker.internet().password(8, 16, true, true, true));

		when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

		assertDoesNotThrow(() -> usuarioService.userUpdate(1, usuarioForm));

		verify(usuarioRepository).save(any(Usuario.class));
		verifyNoMoreInteractions(usuarioRepository);

		assertEquals(usuarioForm.getNome(), usuario.getNome());
		assertEquals(usuarioForm.getEmail(), usuario.getEmail());
		assertEquals(usuarioForm.getSenha(), usuario.getSenha());
	}

	@Test
	void userUpdate_ComIdInvalidoLancaNotFoundException() {
		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm.setNome(faker.name().fullName());
		usuarioForm.setEmail(faker.internet().emailAddress());
		usuarioForm.setSenha(faker.internet().password(8, 16, true, true, true));

		when(usuarioRepository.findById(2)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> usuarioService.userUpdate(2, usuarioForm));

		verify(usuarioRepository).findById(any(Integer.class));
		verifyNoMoreInteractions(usuarioRepository);
	}

	@Test
    void listFriends_ComIdValidoRetornaListaDeAmigos() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        assertDoesNotThrow(() -> usuarioService.listFriends(1));
        
        verify(usuarioRepository).findById(any(Integer.class));
        verifyNoMoreInteractions(usuarioRepository);
    }

	@Test
    void listFriends_ComIdInvalidoLancaNotFoundException() {
        when(usuarioRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usuarioService.listFriends(2));
        
        verify(usuarioRepository).findById(any(Integer.class));
        verifyNoMoreInteractions(usuarioRepository);
    }

	@Test
	void findByName_ComNomeValidoRetornaListaComUsuario() {
		String nome = "João da Silva";
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(usuario);
		when(usuarioRepository.findByNome(nome)).thenReturn(usuarios);

		List<Usuario> usuariosEncontrados = usuarioService.findByName(nome);

		assertThat(usuariosEncontrados).contains(usuario);

		verify(usuarioRepository).findByNome(any(String.class));
		verifyNoMoreInteractions(usuarioRepository);
	}

	@Test
	void findByName_ComNomeInvalidoRetornaListaVazia() {
		String nome = "Pedro";
		when(usuarioRepository.findByNome(nome)).thenReturn(new ArrayList<>());

		List<Usuario> usuariosEncontrados = usuarioService.findByName(nome);

		assertTrue(usuariosEncontrados.isEmpty());

		verify(usuarioRepository).findByNome(any(String.class));
		verifyNoMoreInteractions(usuarioRepository);
	}

	@Test
	void addFriend_ComIdsValidosAdicionaAmigoComSucesso() {
		Usuario amigo = new Usuario();
		amigo.setId(2L);
		amigo.setNome(faker.name().fullName());
		amigo.setEmail(faker.internet().emailAddress());
		amigo.setSenha(faker.internet().password(8, 16, true, true, true));

		when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
		when(usuarioRepository.findById(2)).thenReturn(Optional.of(amigo));

		assertDoesNotThrow(() -> usuarioService.addFriend(1, 2));

		assertTrue(usuario.getAmigos().contains(amigo));
		assertTrue(amigo.getAmigos().contains(usuario));

		verify(usuarioRepository).findById(1);
		verify(usuarioRepository).findById(2);
		verify(usuarioRepository).save(usuario);
		verify(usuarioRepository).save(amigo);
		verifyNoMoreInteractions(usuarioRepository);
	}

	@Test
    void addFriend_ComIdsInvalidosLancaNotFoundException() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usuarioService.addFriend(1, 2));
        
		verify(usuarioRepository).findById(any(Integer.class));
		verifyNoMoreInteractions(usuarioRepository);
    }

	@Test
	void addFriend_ComAmigoJaAdicionadoLancaExcecao() {
		Usuario amigo = new Usuario();
		amigo.setId(2L);
		amigo.setNome(faker.name().fullName());
		amigo.setEmail(faker.internet().emailAddress());
		amigo.setSenha(faker.internet().password(8, 16, true, true, true));

		when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
		when(usuarioRepository.findById(2)).thenReturn(Optional.of(amigo));

		usuario.setAmigos(new ArrayList<>());
		usuario.getAmigos().add(amigo);

		assertThrows(Exception.class, () -> usuarioService.addFriend(1, 2));

		verify(usuarioRepository).findById(1);
		verify(usuarioRepository).findById(2);
		verifyNoMoreInteractions(usuarioRepository);
	}

	@Test
	void removeFriend_ComIdsValidosRemoveAmigoComSucesso() {
		Usuario amigo = new Usuario();
		amigo.setId(2L);
		amigo.setNome(faker.name().fullName());
		amigo.setEmail(faker.internet().emailAddress());
		amigo.setSenha(faker.internet().password(8, 16, true, true, true));

		when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
		when(usuarioRepository.findById(2)).thenReturn(Optional.of(amigo));

		usuario.setAmigos(new ArrayList<>());
		usuario.getAmigos().add(amigo);

		assertDoesNotThrow(() -> usuarioService.removeFriend(1, 2));

		assertFalse(usuario.getAmigos().contains(amigo));
		assertFalse(amigo.getAmigos().contains(usuario));

		verify(usuarioRepository).findById(1);
		verify(usuarioRepository).findById(2);
		verify(usuarioRepository).save(usuario);
		verify(usuarioRepository).save(amigo);
		verifyNoMoreInteractions(usuarioRepository);
	}

	@Test
    void removeFriend_ComIdsInvalidosLancaNotFoundException() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usuarioService.removeFriend(1, 2));

		verify(usuarioRepository).findById(1);
		verifyNoMoreInteractions(usuarioRepository);
    }

	@Test
	void removeFriend_ComAmigoNaoExistenteLancaNotFoundException() {
		Usuario amigo = new Usuario();
		amigo.setId(2L);
		amigo.setNome(faker.name().fullName());
		amigo.setEmail(faker.internet().emailAddress());
		amigo.setSenha(faker.internet().password(8, 16, true, true, true));

		when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
		when(usuarioRepository.findById(2)).thenReturn(Optional.of(amigo));

		assertThrows(NotFoundException.class, () -> usuarioService.removeFriend(1, 2));
		
		verify(usuarioRepository).findById(1);
		verify(usuarioRepository).findById(2);
		verifyNoMoreInteractions(usuarioRepository);
	
	}
}
