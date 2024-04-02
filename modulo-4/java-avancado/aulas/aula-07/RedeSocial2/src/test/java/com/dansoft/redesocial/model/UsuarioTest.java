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
	public void testNomeCorreto() {
		Usuario usuario = new Usuario();
		usuario.setNome(faker.name().fullName());

		Set<ConstraintViolation<String>> violations = validator.validate(usuario.getNome());		
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testNomeIncorreto() {
		Usuario usuario = new Usuario();
		usuario.setNome("João da da Silva");

		Set<ConstraintViolation<String>> violations = validator.validate(usuario.getNome());
		
		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Nome inválido")));
	}

	@Test
	public void testNomeVazio() {
		Usuario usuario = new Usuario();
		usuario.setNome("");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O nome não deve ser vazio")));
	}

	@Test
	public void testNomeNulo() {
		Usuario usuario = new Usuario();
		usuario.setNome(null);

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O nome não deve ser nulo")));
	}

	@Test
	void testEmailCorreto() {
		Usuario usuario = new Usuario();
		usuario.setNome("joaocapixaba@gmail.com");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		assertTrue(violations.isEmpty());
	}

}
