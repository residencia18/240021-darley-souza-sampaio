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
class UsuarioLoginTest {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	private final Faker faker = new Faker(new Locale("pt-BR"));

	private UsuarioLogin usuario = new UsuarioLogin();

	@BeforeEach
	private void geradorDadosUsuarioLogin() {
		usuario.setId((long) 1);
		usuario.setLogin(faker.name().firstName() + faker.name().firstName());
		// Pode gerar uma senha incorreta, tente rodar novamente
		usuario.setSenha(faker.internet().password(8, 16, true, true, true)); 
		usuario.setRole("normal");
	}
	
	@Test
	public void testLogin_DevePassarNoTesteCasoCorreto() {
		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (!violations.isEmpty())
			log.info("Teste para verificação de nome correto falhou: " + violations.toString());

		assertTrue(violations.isEmpty());
	}

	@Test
	public void testLoginVazio_DeveRetornarExceptionLoginVazio() {
		usuario.setLogin("");

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de nome vazio falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O login não deve ser vazio")));
	}

	@Test
	public void testLoginNulo_DeveRetornarExceptionLoginNulo() {
		usuario.setLogin(null);

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de nome nulo falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O login não deve ser nulo")));
	}

	@Test
	void testSenhaCaractereEspecialIncorreta_DeveRetornarException() {

		usuario.setSenha("123456Teste");

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreto sem caractere especial falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(
				violation -> violation.getMessage().equals("A senha deve conter pelo menos um caractere especial")));

	}

	@Test
	void testSenhaCaractereLetraMaiusculaIncorreta_DeveRetornarException() {
		usuario.setSenha("123456@teste");

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreta sem caractere de letra maiúscula falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(
				violation -> violation.getMessage().equals("A senha deve conter pelo menos uma letra maiúscula")));

	}

	@Test
	void testSenhaCaractereLetraMinusculaIncorreta_DeveRetornarException() {
		usuario.setSenha("123456@TESTE");

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreta sem caractere de letra minúscula falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(
				violation -> violation.getMessage().equals("A senha deve conter pelo menos uma letra minúscula")));

	}

	@Test
	void testSenhaCaractereNumericoIncorreta_DeveRetornarException() {

		usuario.setSenha("Senha@Teste");

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreta sem caractere numérico falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream()
				.anyMatch(violation -> violation.getMessage().equals("A senha deve conter pelo menos um dígito")));

	}

	@Test
	void testSenhaMinima_DeveRetornarException() {

		usuario.setSenha("1@Senha");

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreta com númer mínimo de caracteres falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream()
				.anyMatch(violation -> violation.getMessage().equals("A senha deve ter no mínimo 8 caracteres")));

	}

	@Test
	void testSenhaVazia_DeveRetornarException() {

		usuario.setSenha("");

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste de senha vazia falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("A senha não deve ser vazia")));

	}

	@Test
	void testSenhaNula_DeveRetornarException() {

		usuario.setSenha(null);

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste de senha vazia falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("A senha não deve ser nula")));

	}
	
	@Test
	void testSenhaCorreta() {
		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (!violations.isEmpty())
			log.info("Teste para verificação de senha correta falhou: " + violations.toString());

		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void testRole_DevePassarNoTesteCasoCorreto() {
		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (!violations.isEmpty())
			log.info("Teste para verificação de nome correto falhou: " + violations.toString());

		assertTrue(violations.isEmpty());
	}

	@Test
	public void testRoleVazio_DeveRetornarException() {
		usuario.setRole("");

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de nome vazio falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("A role não deve ser vazia")));
	}

	@Test
	public void testRoleNulo_DeveRetornarException() {
		usuario.setRole(null);

		Set<ConstraintViolation<UsuarioLogin>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de nome nulo falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("A role não deve ser nula")));
	}

}
