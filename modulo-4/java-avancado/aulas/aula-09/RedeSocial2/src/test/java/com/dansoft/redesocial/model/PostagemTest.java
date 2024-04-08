package com.dansoft.redesocial.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class PostagemTest {
	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	private final Faker faker = new Faker(new Locale("pt-BR"));

	@Test
	public void testTextoCorreto() {
		Postagem postagem= new Postagem();
		postagem.setId((long) 1);
		postagem.setCodigo(Postagem.gerarCodigoAleatorio());
		postagem.setTexto(faker.lorem().sentence());
		Set<ConstraintViolation<Postagem>> violations = validator.validate(postagem);

		if (!violations.isEmpty())
			System.out.println("Teste para verificação de texto correto falhou: " + violations.toString());

		assertTrue(violations.isEmpty());
	}

	@Test
	public void testTextoVazio() {
		Postagem postagem= new Postagem();
		postagem.setId((long) 1);
		postagem.setCodigo(Postagem.gerarCodigoAleatorio());
		postagem.setTexto("");
		
		Set<ConstraintViolation<Postagem>> violations = validator.validate(postagem);

		if (violations.isEmpty())
			System.out.println("Teste para verificação de texto vazio falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O texto não deve ser vazio")));
	}

	@Test
	public void testTextoNulo() {
		Postagem postagem= new Postagem();
		postagem.setId((long) 1);
		postagem.setCodigo(Postagem.gerarCodigoAleatorio());
		postagem.setTexto(null);
		
		Set<ConstraintViolation<Postagem>> violations = validator.validate(postagem);

		if (violations.isEmpty())
			System.out.println("Teste para verificação de texto nulo falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O texto não deve ser nulo")));
	}
}
