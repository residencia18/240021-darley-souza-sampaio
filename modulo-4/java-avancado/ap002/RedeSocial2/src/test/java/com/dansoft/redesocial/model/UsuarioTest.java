package com.dansoft.redesocial.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class UsuarioTest {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	private final Faker faker = new Faker(new Locale("pt-BR"));

	private Usuario usuario = new Usuario();

	@BeforeEach
	private void geradorDadosUsuario() {
		usuario.setId((long) 1);
		usuario.setNome(faker.name().fullName());
	}

	@Test
	public void testNome() {
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (!violations.isEmpty())
			log.info("Teste para verificação de nome correto falhou: " + violations.toString());

		assertTrue(violations.isEmpty());
	}

	@Test
	public void testNomeVazio() {
		usuario.setNome("");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de nome vazio falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O nome não deve ser vazio")));
	}

	@Test
	public void testNomeNulo() {
		usuario.setNome(null);

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de nome nulo falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O nome não deve ser nulo")));
	}

	@Test
	void testEmailCorreto() {
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (!violations.isEmpty())
			log.info(violations.toString());

		assertTrue(violations.isEmpty());
	}
}
