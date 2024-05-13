package com.dansoft.redesocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dansoft.redesocial.service.PasswordResetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reset-password")
public class PasswordResetController {
	@Autowired
	private PasswordResetService usuarioLoginService;

	@PutMapping
	public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestBody @Valid String novaSenha) {
		try {			
			return usuarioLoginService.updatePassword(token, novaSenha);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
