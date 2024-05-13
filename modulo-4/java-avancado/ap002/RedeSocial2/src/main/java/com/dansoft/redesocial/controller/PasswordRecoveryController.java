package com.dansoft.redesocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dansoft.redesocial.service.AuthorizationService;

@RestController
@RequestMapping("/recovery")
public class PasswordRecoveryController {

	@Autowired
	private AuthorizationService authorizationService;

	@PutMapping("/{email}")
	public ResponseEntity<?> recoveryPassword(@PathVariable String email) {
		ResponseEntity<?> response = authorizationService.resetPassword(email);
		return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
}
