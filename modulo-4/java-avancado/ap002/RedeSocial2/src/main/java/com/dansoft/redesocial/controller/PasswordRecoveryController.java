package com.dansoft.redesocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dansoft.redesocial.service.AuthorizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/recovery", produces = {"application/json"})
@Tag(name = "Rede Social")
public class PasswordRecoveryController {

	@Autowired
	private AuthorizationService authorizationService;

	@Operation(summary = "recoveryPassword", method= "PUT", description = "Método para solicitar troca de senha", tags = "Authentication Controller")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Email para recuperação enviado com sucesso"),
			@ApiResponse(responseCode = "403", description = "Nenhum parâmetro passado"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	})
	@PutMapping(value = "/{email}")
	public ResponseEntity<?> recoveryPassword(@PathVariable String email) {
		ResponseEntity<?> response = authorizationService.resetPassword(email);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
	
}
