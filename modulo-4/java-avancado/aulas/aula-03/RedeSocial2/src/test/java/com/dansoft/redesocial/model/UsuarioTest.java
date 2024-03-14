package com.dansoft.redesocial.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class UsuarioTest {

	 @Test
	    void testNome() {
	        Usuario usuario = new Usuario();
	        
	        //Nome correto
	        assertDoesNotThrow(() -> usuario.setNome("João da Silva"));
	        
	        // Nome incorreto
	        Exception nomeIncorretoException = assertThrows(Exception.class, () -> usuario.setNome("João da da Silva")); 
	        assertEquals("Erro: Nome inválido.", nomeIncorretoException.getMessage());
	        
	        // Nome nulo
	        Exception nomeNuloException = assertThrows(Exception.class, () -> usuario.setNome(null));
	        assertEquals("Erro: Nome não deve ser nulo.", nomeNuloException.getMessage());
	    }

	    @Test
	    void testEmail() {
	        Usuario usuario = new Usuario();
	        
	        // Email válido
	        assertDoesNotThrow(() -> usuario.setEmail("joao@example.com"));
	        
	        // Email inválido
	        Exception emailInvalidoException = assertThrows(Exception.class, () -> usuario.setEmail("joao@example"));
	        assertEquals("Erro: Email inválido.", emailInvalidoException.getMessage());
	        
	        // Email nulo
	        Exception emailNuloException = assertThrows(Exception.class, () -> usuario.setEmail(null));
	        assertEquals("Erro: Email não deve ser nulo.", emailNuloException.getMessage());
	    }

	    @Test
	    void testSenha() {
	        Usuario usuario = new Usuario();
	        
	        // Senha válida
	        assertDoesNotThrow(() -> usuario.setSenha("123@Senha"));
	        
	        // Senha inválida
	        Exception senhaInvalidaException = assertThrows(Exception.class, () -> usuario.setSenha("123senha"));
	        assertEquals("Erro: Senha inválida.", senhaInvalidaException.getMessage());
	        
	        // Senha nula
	        Exception senhaNulaException = assertThrows(Exception.class, () -> usuario.setSenha(null));
	        assertEquals("Erro: Senha não deve ser nula.", senhaNulaException.getMessage());
	    }

}
