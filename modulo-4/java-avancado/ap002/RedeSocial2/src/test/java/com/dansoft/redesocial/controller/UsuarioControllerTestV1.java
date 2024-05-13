package com.dansoft.redesocial.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dansoft.redesocial.controller.Form.UsuarioForm;
import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.service.UsuarioServiceV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@WebMvcTest(UsuarioControllerV1.class)
public class UsuarioControllerTestV1 {

	@MockBean
	private UsuarioServiceV1 usuarioService;

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
		usuario.setNome(faker.name().fullName());
		return usuario;
	}

	private UsuarioForm geradorUsuarioFormFaker() {
		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm.setNome(faker.name().fullName());
		return usuarioForm;
	}

	@Test
	void inserirUsuario_deveSalvarUsuarioEretornarCreated() throws Exception {
		UsuarioForm usuarioForm = geradorUsuarioFormFaker();

		Usuario usuarioSalvo = usuarioForm.toUsuario();
		usuarioSalvo.setId(1L);

		when(usuarioService.saveUser(any(Usuario.class))).thenReturn(usuarioSalvo);

		mockMvc.perform(post("/usuarios/").content(objectMapper.writeValueAsString(usuarioForm))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(new UsuarioDTO(usuarioSalvo))));
	}

	@Test
	void inserirUsuario_deveRetornarBadRequestCasoDadosErrados() throws Exception {
		UsuarioForm usuarioForm = new UsuarioForm(); 

		Usuario usuarioSalvo = usuarioForm.toUsuario();

		when(usuarioService.saveUser(any(Usuario.class))).thenReturn(usuarioSalvo);

		mockMvc.perform(post("/usuarios/").content(objectMapper.writeValueAsString(usuarioForm))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void listarUsuario_deveRetornarTodaListaDeUsuariosAtivos() throws Exception {
		Usuario usuario = geradorUsuarioFaker();
		usuario.setId(faker.number().randomNumber());

		when(usuarioService.findAll()).thenReturn(Arrays.asList(usuario));

		mockMvc.perform(get("/usuarios/")).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(new UsuarioDTO(usuario)))));
	}

	@Test
	void listarUsuario_deveRetornarUmUsuario() throws Exception {
		Usuario usuario = geradorUsuarioFaker();
		usuario.setId(1L);

		when(usuarioService.findUser(1)).thenReturn(usuario);

		mockMvc.perform(get("/usuarios/{id}", 1))
				.andExpect(content().json(objectMapper.writeValueAsString(new UsuarioDTO(usuario))))
				.andExpect(status().isOk());
	}

	@Test
	void listarUsuario_deveRetornarNotFoundCasoNaoEncontrado() throws Exception {
		when(usuarioService.findUser(1)).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/usuarios/{id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void deletarUsuario_deveRetornarUsuarioDeletadoCasoEncontrado() throws Exception {
		Usuario usuario = geradorUsuarioFaker();
		usuario.setId(1L);

		when(usuarioService.deleteUser(1)).thenReturn(usuario);

		mockMvc.perform(delete("/usuarios/{id}", 1))
				.andExpect(content().json(objectMapper.writeValueAsString(new UsuarioDTO(usuario))))
				.andExpect(status().isOk());
	}

	@Test
	void deletarUsuario_deveRetornarNotFoundCasoNaoEncontrado() throws Exception {
		when(usuarioService.findUser(1)).thenThrow(NotFoundException.class);

		mockMvc.perform(delete("/usuarios/{id}", 1)).andExpect(status().isNotFound());
	}

	@Test
	void alterarUsuario_deveAlterarUsuarioEretornarOk() throws Exception {
		UsuarioForm usuarioForm = geradorUsuarioFormFaker();

		Usuario usuarioAtualizado = usuarioForm.toUsuario();

		usuarioAtualizado.setId(1L);
		usuarioAtualizado.setNome(faker.name().fullName());

		when(usuarioService.userUpdate(any(Integer.class), any(UsuarioForm.class))).thenReturn(usuarioAtualizado);

		mockMvc.perform(put("/usuarios/{id}", 1).content(objectMapper.writeValueAsString(usuarioForm))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(new UsuarioDTO(usuarioAtualizado))));
	}

	@Test
	void alterarUsuario_deveRetornarNotFoundSeNaoEncontrado() throws Exception {
		UsuarioForm usuarioForm = geradorUsuarioFormFaker();

		when(usuarioService.userUpdate(any(Integer.class), any(UsuarioForm.class))).thenThrow(NotFoundException.class);

		mockMvc.perform(put("/usuarios/{id}", 2).content(objectMapper.writeValueAsString(usuarioForm))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	void alterarUsuario_deveRetornarBadRequestCasoDadoErrado() throws Exception {
		UsuarioForm usuarioForm = geradorUsuarioFormFaker();

		Usuario usuarioAtualizado = usuarioForm.toUsuario();

		usuarioAtualizado.setId(1L);
		usuarioAtualizado.setNome(null);

		when(usuarioService.userUpdate(any(Integer.class), any(UsuarioForm.class)))
				.thenThrow(BadRequestException.class);

		mockMvc.perform(put("/usuarios/{id}", 1).content(objectMapper.writeValueAsString(usuarioForm))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void listarAmigos_deveRetornarListaAmigosCheiaOuVazia() throws Exception {
		Usuario usuario = geradorUsuarioFaker();
		usuario.setId(1L);

		when(usuarioService.listFriends(any(Integer.class))).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/usuarios/{id}/amigos/", 1)).andExpect(status().isOk()).andExpect(content().json("[]"));
	}

	@Test
	void listarAmigos_deveRetornarBadRequestCasoSolicitacaoErrada() throws Exception {
	    when(usuarioService.listFriends(2)).thenThrow(NotFoundException.class);

	    mockMvc.perform(get("/usuarios/{id}/amigos/", 2))
	            .andExpect(status().isNotFound());
	}

	@Test
	void adicionarAmigo_deveSalvarOAmigoEretornarOkQuandoValido() throws Exception {		
		mockMvc.perform(post("/usuarios/{id}/amigos/{amigoId}", 1, 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void adicionarAmigo_deveRetornarNotFoundQuandoUsuarioOuAmigoNaoEncontrado() throws Exception {		
	    doThrow(NotFoundException.class).when(usuarioService).addFriend(1, 2);
		
		mockMvc.perform(post("/usuarios/{id}/amigos/{amigoId}", 1, 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void adicionarAmigo_deveRetornarNotFoundQuandoAmigoJaPresente() throws Exception {		
	    doThrow(Exception.class).when(usuarioService).addFriend(1, 2);
		
		mockMvc.perform(post("/usuarios/{id}/amigos/{amigoId}", 1, 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void removerAmigo_deveSalvarOAmigoEretornarOkQuandoValido() throws Exception {		
		mockMvc.perform(delete("/usuarios/{id}/amigos/{amigoId}", 1, 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void removerAmigo_deveRetornarNotFoundQuandoUsuarioOuAmigoNaoEncontrado() throws Exception {		
	    doThrow(NotFoundException.class).when(usuarioService).removeFriend(1, 2);
		
		mockMvc.perform(delete("/usuarios/{id}/amigos/{amigoId}", 1, 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void removerAmigo_deveRetornarNotFoundQuandoAmigoJaPresente() throws Exception {		
	    doThrow(Exception.class).when(usuarioService).removeFriend(1, 2);
		
		mockMvc.perform(delete("/usuarios/{id}/amigos/{amigoId}", 1, 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	

}
