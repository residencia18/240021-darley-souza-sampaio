package com.dansoft.redesocial.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dansoft.redesocial.controller.Form.PostagemForm;
import com.dansoft.redesocial.controller.dto.PostagemDTO;
import com.dansoft.redesocial.model.Postagem;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.service.PostagemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@WebMvcTest(PostagemController.class)
class PostagemControllerTest {

	@MockBean
	private PostagemService postagemService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

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

		usuario.setId(faker.number().randomNumber());
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setSenha(faker.internet().password(8, 16, true, true, true));

		return usuario;
	}

	private PostagemForm geradorPostagemFormFaker() {
		PostagemForm postagemForm = new PostagemForm();
		postagemForm.setTexto(faker.lorem().toString());
		postagemForm.setUsuario(geradorUsuarioFaker());
		return postagemForm;
	}

	@Test
	void inserirPostagem_deveSalvarPostagemEretornarCreated() throws Exception {
		PostagemForm postagemForm = geradorPostagemFormFaker();

		Postagem postagemSalva = postagemForm.toPostagem();
		postagemSalva.setId(1L);

		when(postagemService.savePost(any(Postagem.class))).thenReturn(postagemSalva);

		mockMvc.perform(post("/postagens/").content(objectMapper.writeValueAsString(postagemForm))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(new PostagemDTO(postagemSalva))));
	}

	@Test
	void inserirPostagem_deveRetornarBadRequestCasoExcecaoLancada() throws Exception {
	    when(postagemService.savePost(any(Postagem.class))).thenThrow(RuntimeException.class);
	    
	    mockMvc.perform(post("/postagens/")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(new PostagemForm())))
	            .andExpect(status().isBadRequest());
	}

	@Test
	void listarPostagem_deveRetornarTodaListaDeUsuariosAtivos() throws Exception {
		List<Postagem> listaPostagens = new ArrayList<>();
		listaPostagens.add(new Postagem());
		listaPostagens.add(new Postagem());

		when(postagemService.findAll(any(Integer.class))).thenReturn(listaPostagens);

		mockMvc.perform(get("/postagens/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(listaPostagens)));
	}

	@Test
	void listarPostagem_deveRetornarNotFoundCasoListaVazia() throws Exception {
		when(postagemService.findAll(any(Integer.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/postagens/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}

	@Test
	void alterarPostagem_deveAlterarPostagemERetornarOk() throws Exception {
		PostagemForm postagemForm = geradorPostagemFormFaker();

		Postagem postagemSalva = postagemForm.toPostagem();
		postagemSalva.setId(1L);

		when(postagemService.updatePost(any(Integer.class), any(PostagemForm.class))).thenReturn(postagemSalva);

		mockMvc.perform(put("/postagens/{id}", 1L).content(objectMapper.writeValueAsString(postagemForm))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(new PostagemDTO(postagemSalva))));
	}

	@Test
    void alterarPostagem_deveRetornarNotFoundQuandoPostagemNaoEncontrada() throws Exception {
        when(postagemService.updatePost(any(Integer.class), any(PostagemForm.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/postagens/{id}", 1)
                .content("{\"texto\": \"Novo texto da postagem\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	@Test
    void deletarPostagem_deveRetornarOkQuandoPostagemDeletada() throws Exception {
        Postagem postagem = new Postagem();
        when(postagemService.findPost(any(Integer.class))).thenReturn(postagem);

        mockMvc.perform(delete("/postagens/{id}", 1))
                .andExpect(status().isOk());
    }
	
	 @Test
	    void deletarPostagem_deveRetornarNotFoundQuandoPostagemNaoEncontrada() throws Exception {
	        when(postagemService.findPost(any(Integer.class))).thenThrow(NotFoundException.class);

	        mockMvc.perform(delete("/postagens/{id}", 1))
	                .andExpect(status().isNotFound());
	    }

}
