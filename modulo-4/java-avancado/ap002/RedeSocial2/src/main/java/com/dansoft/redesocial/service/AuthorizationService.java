package com.dansoft.redesocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.model.UsuarioLogin;
import com.dansoft.redesocial.repository.UsuarioLoginRepository;
import com.dansoft.redesocial.services.security.TokenService;

@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	UsuarioLoginRepository usuarioLoginRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TokenService tokenService; 

	@Value("${redeSocial.base-url}")
	private String baseUrl;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		return usuarioLoginRepository.findByLogin(login);
	}

	public ResponseEntity<?> resetPassword(String email) {
		UsuarioLogin usuario = usuarioLoginRepository.findByEmail(email);
		
		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}

		String token = tokenService.geradorToken(usuario);

		String resetPasswordLink = baseUrl + "/reset-password?token=" + token;

		String emailBody = "Olá " + usuario.getLogin() + ",\n\n"
				+ "Você solicitou a redefinição da sua senha. Por favor, clique no link abaixo para redefinir sua senha:\n\n"
				+ resetPasswordLink + "\n\n"
				+ "Se você não solicitou esta redefinição, por favor ignore este email.\n\n" + "Atenciosamente,\n"
				+ "Equipe da Rede Social";

		emailService.sendEmail(usuario.getEmail(), "Redefinição de Senha", emailBody);

		return new ResponseEntity<>("Um email com instruções para redefinir a senha foi enviado para " + email, HttpStatus.OK);
	}

}
