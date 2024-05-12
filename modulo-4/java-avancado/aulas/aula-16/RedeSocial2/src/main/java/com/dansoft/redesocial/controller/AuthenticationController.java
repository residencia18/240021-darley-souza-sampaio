package com.dansoft.redesocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UsuarioLoginRepository usuarioLoginRepository;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationForm usuarioForm) {
			var usuarioSenha = new UsernamePasswordAuthenticationToken(usuarioForm.login(), usuarioForm.senha());
			var auth = this.authManager.authenticate(usuarioSenha);
			
			var token = tokenService.geradorToken((UsuarioLogin) auth.getPrincipal());
			log.info("Atenticação do usuario " + usuarioForm.login() + " realizada com sucesso!");

			return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterForm usuarioRegister) {
		try {
			
		if (this.usuarioLoginRepository.findByLogin(usuarioRegister.login()) != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		String senhaCriptogragada = new BCryptPasswordEncoder().encode(usuarioRegister.senha());
		UsuarioLogin novoUsuario = new UsuarioLogin(usuarioRegister.login(), senhaCriptogragada, usuarioRegister.role());
		
		this.usuarioLoginRepository.save(novoUsuario);
		log.info("Registro do usuario " + usuarioRegister.login() + " realizado com sucesso!!");
		return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Erro ao realizar o registro com login " + usuarioRegister.login());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
