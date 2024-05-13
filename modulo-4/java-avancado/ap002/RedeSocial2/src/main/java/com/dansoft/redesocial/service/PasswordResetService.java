package com.dansoft.redesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.model.UsuarioLogin;
import com.dansoft.redesocial.repository.UsuarioLoginRepository;
import com.dansoft.redesocial.services.security.TokenService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PasswordResetService {

	@Autowired
	private UsuarioLoginRepository usuarioLoginRepository;

	@Autowired
	private TokenService tokenService;

	public ResponseEntity<?> updatePassword(String token, String novaSenha) {
		
		String login = tokenService.validacaoToken(token);

	    if (login.isEmpty() || tokenService.isTokenInvalid(token)) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou expirado.");
	    }

	    UserDetails userDetails = usuarioLoginRepository.findByLogin(login);
	    
	    if (userDetails == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
	    }
	    
		UsuarioLogin usuario = (UsuarioLogin) userDetails;

		String senhaCriptografada = new BCryptPasswordEncoder().encode(novaSenha);

		usuario.setSenha(senhaCriptografada);

		usuarioLoginRepository.saveAndFlush(usuario);

		tokenService.invalidateToken(token);
		log.info("Senha do usuario " + usuario.getLogin() + "foi alterada com sucesso.");
		
		return new ResponseEntity<>("Senha redefinida com sucesso.", HttpStatus.OK);
	}
}
