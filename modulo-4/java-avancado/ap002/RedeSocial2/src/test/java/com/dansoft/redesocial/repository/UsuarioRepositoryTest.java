package com.dansoft.redesocial.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.dansoft.redesocial.model.Usuario;
import com.github.javafaker.Faker;

@DataJpaTest
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private Faker faker;

	@TestConfiguration
	static class FakerTestConfig {

		@Bean
		public Faker faker() {
			return new Faker(new Locale("pt-BR"));
		}
	}

	private Usuario geradorUsuarioFaker() {
		Usuario usuario = new Usuario();
		usuario.setNome(faker.name().fullName());
		return usuario;
	}

	@Test
	void saveUsuarioo_DeveInserirERetornarOUsuario() {
		Usuario usuario = geradorUsuarioFaker();

		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		assertThat(usuarioSalvo).isNotNull();
		assertThat(usuarioSalvo.getId()).isGreaterThan(0);
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuario.getNome());
	}

	@Test
	void saveUsuarioo_UsuarioMesmoEmailNaoDeveInserirRetornarException() {
		Usuario usuario1 = geradorUsuarioFaker();
		testEntityManager.persistFlushFind(usuario1);

		Usuario usuario2 = geradorUsuarioFaker();

		assertThatThrownBy(() -> usuarioRepository.save(usuario2)).isInstanceOf(Exception.class);
	}
	
	@Test
	void findById_RetornarUsuarioCasoIdPresenteNoBanco() {

        Usuario usuario = geradorUsuarioFaker();
        Usuario usuarioSalvo = testEntityManager.persistFlushFind(usuario);

        Integer idInteger = usuarioSalvo.getId().intValue();
        Optional<Usuario> usuarioEcontrado = (Optional<Usuario>) usuarioRepository.findById(idInteger);

        assertThat(usuarioEcontrado).isNotNull();
        assertThat(usuarioEcontrado.get().getId()).isEqualTo(usuarioSalvo.getId());
	}
	
	@Test
	void findAll_RetornarTodosUsuarios() {      
		Usuario usuario1 = geradorUsuarioFaker();
		Usuario usuarioSalvo1 = testEntityManager.persistFlushFind(usuario1);
		
		Usuario usuario2 = geradorUsuarioFaker();
		Usuario usuarioSalvo2 = testEntityManager.persistFlushFind(usuario2);
		
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		
		assertThat(listaUsuarios).isNotEmpty();
		assertThat(listaUsuarios).contains(usuarioSalvo1, usuarioSalvo2);
		
	}
	
	@Test
	void deleteUser_DeveDeletarUsuarioCasoExistenteNoBanco() {
		Usuario usuario = geradorUsuarioFaker();
		Usuario usuarioSalvo = testEntityManager.persistFlushFind(usuario);

        Integer idInteger = usuarioSalvo.getId().intValue();
		usuarioRepository.delete(usuarioSalvo);

        Usuario usuarioDeletado = testEntityManager.find(Usuario.class, idInteger);
        assertThat(usuarioDeletado).isNull();
	}
	
}
