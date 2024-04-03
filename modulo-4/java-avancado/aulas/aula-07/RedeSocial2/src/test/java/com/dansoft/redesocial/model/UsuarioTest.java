package com.dansoft.redesocial.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class UsuarioTest {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	private final Faker faker = new Faker();

	@Test
	public void testNome() {
		Usuario usuario = new Usuario();
		usuario.setId((long) 1);
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setSenha(faker.internet().password(8, 32, true, true, true));
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (!violations.isEmpty())
			System.out.println(violations.toString());

		assertTrue(violations.isEmpty());
	}

	@Test
	public void testNomeVazio() {
		Usuario usuario = new Usuario();
		usuario.setId((long) 1);
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setSenha(faker.internet().password(8, 32, true, true, true));
		usuario.setNome("");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			System.out.println("Teste de nome vazio falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O nome não deve ser vazio")));
	}

	@Test
	public void testNomeNulo() {
		Usuario usuario = new Usuario();
		usuario.setId((long) 1);
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setSenha(faker.internet().password(8, 32, true, true, true));
		usuario.setNome(null);

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			System.out.println("Teste de nome nulo falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O nome não deve ser nulo")));
	}

}
