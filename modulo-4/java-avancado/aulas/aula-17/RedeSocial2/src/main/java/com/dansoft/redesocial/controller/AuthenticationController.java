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
import com.dansoft.redesocial.model.UsuarioLogin;
import com.dansoft.redesocial.repository.UsuarioLoginRepository;

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

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationForm usuarioForm) {
		try {
			var usuarioSenha = new UsernamePasswordAuthenticationToken(usuarioForm.login(), usuarioForm.senha());
			var auth = this.authManager.authenticate(usuarioSenha);
			log.info("Atenticação do usuario " + usuarioForm.login() + " realizada com sucesso!");

			return new ResponseEntity<>(auth, HttpStatus.OK);
		} catch (Exception e) {
			log.info("Erro ao realizar a autenticação do usuario " + usuarioForm.login());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
