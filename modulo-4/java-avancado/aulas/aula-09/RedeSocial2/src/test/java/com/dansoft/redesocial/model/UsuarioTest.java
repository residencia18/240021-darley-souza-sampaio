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
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setSenha("123@Teste");
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

	@Test
	void testEmailVazio() {
		usuario.setEmail("");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de email vazio falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O email não deve ser vazio")));

	}

	@Test
	void testEmailNulo() {
		usuario.setEmail(null);

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de email nulo falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("O email não deve ser nulo")));

	}

	@Test
	void testEmailIncorreto() {
		usuario.setEmail("teste@teste");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de email incorreto falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Email inválido")));

	}

	@Test
	void testSenhaCorreta() {
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (!violations.isEmpty())
			log.info("Teste para verificação de senha correta falhou: " + violations.toString());

		assertTrue(violations.isEmpty());
	}

	@Test
	void testSenhaCaractereEspecialIncorreta() {
		
		usuario.setSenha("123456Teste");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreto sem caractere especial falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(
				violation -> violation.getMessage().equals("A senha deve conter pelo menos um caractere especial")));

	}

	@Test
	void testSenhaCaractereLetraMaiusculaIncorreta() {
		usuario.setSenha("123456@teste");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreta sem caractere de letra maiúscula falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(
				violation -> violation.getMessage().equals("A senha deve conter pelo menos uma letra maiúscula")));

	}

	@Test
	void testSenhaCaractereLetraMinusculaIncorreta() {
		usuario.setSenha("123456@TESTE");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreta sem caractere de letra minúscula falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream().anyMatch(
				violation -> violation.getMessage().equals("A senha deve conter pelo menos uma letra minúscula")));

	}

	@Test
	void testSenhaCaractereNumericoIncorreta() {
		
		usuario.setSenha("Senha@Teste");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreta sem caractere numérico falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream()
				.anyMatch(violation -> violation.getMessage().equals("A senha deve conter pelo menos um dígito")));

	}

	@Test
	void testSenhaMinima() {
		
		usuario.setSenha("1@Senha");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste para verificação de senha incorreta com númer mínimo de caracteres falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(violations.stream()
				.anyMatch(violation -> violation.getMessage().equals("A senha deve ter no mínimo 8 caracteres")));

	}

	@Test
	void testSenhaVazia() {
		
		usuario.setSenha("");

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste de senha vazia falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("A senha não deve ser vazia")));

	}

	@Test
	void testSenhaNula() {
		
		usuario.setSenha(null);

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

		if (violations.isEmpty())
			log.info("Teste de senha vazia falhou.");

		assertFalse(violations.isEmpty());
		assertTrue(
				violations.stream().anyMatch(violation -> violation.getMessage().equals("A senha não deve ser nula")));

	}
}
