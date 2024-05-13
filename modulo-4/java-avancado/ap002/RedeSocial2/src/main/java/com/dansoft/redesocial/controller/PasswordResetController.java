package com.dansoft.redesocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dansoft.redesocial.service.PasswordResetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/reset-password", produces = {"application/json"})
@Tag(name = "Rede Social")
public class PasswordResetController {
	@Autowired
	private PasswordResetService usuarioLoginService;

	@Operation(summary = "resetPassword", method= "PUT", description = "Método para realizar a troca da senha", tags = "Authentication Controller")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Token expirado ou inválido"),
			@ApiResponse(responseCode = "403", description = "Não passou o token como parâmetro")
	})
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestBody @Valid String novaSenha) {
		try {			
			return usuarioLoginService.updatePassword(token, novaSenha);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	
}
