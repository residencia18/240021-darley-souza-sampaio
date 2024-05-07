package com.dansoft.redesocial.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.dansoft.redesocial.controller.Form.PostagemForm;
import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;
import com.github.javafaker.Faker;

@DataJpaTest
class PostagemRepositoryTest {

	@Autowired
	PostagemRepository postagemRepository;

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
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setSenha("123@Teste");
		return usuario;
	}
	
	Usuario usuarioSalvo = new Usuario();
	
	@BeforeEach
	void inserirDadosBanco() {
		Usuario usuario = geradorUsuarioFaker();
		usuarioSalvo = testEntityManager.persistFlushFind(usuario);
	}

	private PostagemForm geradorPostagemFaker() {
		PostagemForm postagem = new PostagemForm();
		postagem.setTexto(faker.lorem().toString());
		postagem.setUsuario(usuarioSalvo);
		return postagem;
	}
	
	@Test
	void savePostagem_DeveInserirERetornarAPostagem() throws Exception {
		PostagemForm postagemForm = geradorPostagemFaker();
		Postagem postagem = postagemForm.toPostagem();
		
		Postagem postagemSalva = postagemRepository.save(postagem);
		
		assertThat(postagemSalva).isNotNull();
		assertThat(postagemSalva.getCodigo()).isEqualTo(postagem.getCodigo());
		assertThat(postagemSalva.getTexto()).isEqualTo(postagem.getTexto());
		assertThat(postagemSalva.getUsuario().getNome()).isEqualTo(postagem.getUsuario().getNome());
	}
	
	@Test
	void savePostagem_PostagemMesmoCodigoNaoDeveInserirRetornarException() throws Exception {
		PostagemForm postagemForm = geradorPostagemFaker();
		Postagem postagem = postagemForm.toPostagem();
		
		testEntityManager.persistFlushFind(postagem);
		
		PostagemForm postagemForm2 = geradorPostagemFaker();
		Postagem postagem2 = postagemForm2.toPostagem();
		
		postagem2.setCodigo(postagem.getCodigo());
		
		assertThatThrownBy(() -> postagemRepository.save(postagem2)).isInstanceOf(Exception.class);
		
	}
	
	@Test
	void findByCodigo_DeveRetornarPostagemCasoPresenteNoBanco() {
		
	}

}
