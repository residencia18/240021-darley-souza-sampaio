package com.dansoft.redesocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dansoft.redesocial.controller.Form.AuthenticationForm;
import com.dansoft.redesocial.controller.Form.RegisterForm;
import com.dansoft.redesocial.controller.dto.LoginResponseDTO;
import com.dansoft.redesocial.model.UsuarioLogin;
import com.dansoft.redesocial.repository.UsuarioLoginRepository;
import com.dansoft.redesocial.services.security.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/auth/", produces = { "application/json" })
@Tag(name = "Rede Social")
@Slf4j
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UsuarioLoginRepository usuarioLoginRepository;

	@Autowired
	private TokenService tokenService;

	@Operation(summary = "login", method = "POST", description = "Método para login na plataforma, retorna um token para utilização dos endpoints", tags = "Authentication Controller")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
			@ApiResponse(responseCode = "403", description = "Login ou senha errado"),
			@ApiResponse(responseCode = "400", description = "Falha na requisição, algum dado faltando") })
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationForm usuarioForm) {
		var usuarioSenha = new UsernamePasswordAuthenticationToken(usuarioForm.login(), usuarioForm.senha());
		var auth = this.authManager.authenticate(usuarioSenha);

		var token = tokenService.geradorToken((UsuarioLogin) auth.getPrincipal());
		log.info("Atenticação do usuario " + usuarioForm.login() + " realizada com sucesso!");

		return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
	}

	@Operation(summary = "register", method = "POST", description = "Método para registro na plataforma", tags = "Authentication Controller Register")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cadastro do usuário realizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Usuário com mesmo login já cadastrado"),
			@ApiResponse(responseCode = "403", description = "Usuário com mesmo email já cadastrado") })
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody @Valid RegisterForm usuarioRegister) {
		try {

			if (this.usuarioLoginRepository.findByLogin(usuarioRegister.login()) != null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			String senhaCriptogragada = new BCryptPasswordEncoder().encode(usuarioRegister.senha());
			UsuarioLogin novoUsuario = new UsuarioLogin(usuarioRegister.login(), usuarioRegister.email(),
					senhaCriptogragada, usuarioRegister.role());

			this.usuarioLoginRepository.save(novoUsuario);
			log.info("Registro do usuario " + usuarioRegister.login() + " realizado com sucesso!!");
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
