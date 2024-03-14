package com.dansoft.redesocial.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PostagemTest {

	 @Test
	    void testCodigo() {
	        Postagem postagem = new Postagem();
	        
	        // Código válido
	        assertDoesNotThrow(() -> postagem.setCodigo("abc123"));
	        
	        // Código nulo
	        Exception codigoNuloException = assertThrows(Exception.class, () -> postagem.setCodigo(null));
	        assertEquals("Erro: Código não deve ser nulo.", codigoNuloException.getMessage());
	    }

	    @Test
	    void testTexto() {
	        Postagem postagem = new Postagem();
	        
	        // Texto válido
	        assertDoesNotThrow(() -> postagem.setTexto("Se o pato perde a pata ele fica manco ou viuvo?."));
	        
	        // Texto nulo
	        Exception textoNuloException = assertThrows(Exception.class, () -> postagem.setTexto(null));
	        assertEquals("Erro: Texto não deve ser nulo.", textoNuloException.getMessage());
	    }

	    @Test
	    void testUsuario() {
	        Postagem postagem = new Postagem();
	        Usuario usuario = new Usuario();
	        
	        // Usuário válido
	        assertDoesNotThrow(() -> postagem.setUsuario(usuario));
	        
	        // Usuário nulo
	        Exception usuarioNuloException = assertThrows(Exception.class, () -> postagem.setUsuario(null));
	        assertEquals("Erro: Usuário não deve ser nulo.", usuarioNuloException.getMessage());
	    }
}
