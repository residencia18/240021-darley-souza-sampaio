package com.dansoft.redesocial.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public enum UsuarioLoginRole {
	ADMIN("admin"), USER("user");

	@NotNull(message = "A role não deve ser nula")
	@NotEmpty(message = "A role não deve ser vazia")
	private String role;

	UsuarioLoginRole(String role) {
		this.role = role;
	}

}
