package com.dansoft.empresaCoelhoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dansoft.empresaCoelho.Cliente;
import com.dansoft.empresaCoelho.Imovel;

public class ClienteTest {

	@Test
	public void testNomeCorreto() {
		assertDoesNotThrow(() -> {
			Cliente cliente = new Cliente();
			cliente.setNome("João da Silva");
			System.out.println(cliente.getNome());
		});
	}

	@Test
	public void testNomeComCaractereEspecial() {
		Exception exception = assertThrows(Exception.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setNome("João-da-Silva");
		});

		assertEquals(
				"O nome não pode conter:\nCaracteres especiais(exceto espaços)\nMúltiplos conectivos seguidos\nIniciais minúsculas\nNomes Únicos.",
				exception.getMessage());
	}

	@Test
	public void testNomeComMultiplosConectivos() {
		Exception exception = assertThrows(Exception.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setNome("João da da Silva");
		});

		assertEquals(
				"O nome não pode conter:\nCaracteres especiais(exceto espaços)\nMúltiplos conectivos seguidos\nIniciais minúsculas\nNomes Únicos.",
				exception.getMessage());
	}

	@Test
	public void testNomeComLetraMinuscula() {
		Exception exception = assertThrows(Exception.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setNome("joão da silva");
		});
		assertEquals(
				"O nome não pode conter:\nCaracteres especiais(exceto espaços)\nMúltiplos conectivos seguidos\nIniciais minúsculas\nNomes Únicos.",
				exception.getMessage());
	}

	@Test
	public void testeNomeNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setNome(null);
		});
		assertEquals("O nome não pode ser nulo.", exception.getMessage());
	}

	@Test
	public void testCpfCorreto() {
		assertDoesNotThrow(() -> {
			Cliente cliente = new Cliente();
			cliente.setCpf("111.111.111-11");
			System.out.println(cliente.getCpf());
		});
	}

	@Test
	public void testCpfFormatoIncorreto() {
		Exception exception = assertThrows(Exception.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setCpf("111.11.111-11");
		});
		assertEquals("CPF inválido. Formato correto é XXX.XXX.XXX-XX.", exception.getMessage());
	}

	@Test
	public void testCpfNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setCpf(null);
		});
		assertEquals("O CPF não pode ser nulo.", exception.getMessage());
	}

	@Test
	public void testListaImoveisNula() {
		Exception exception = assertThrows(Exception.class, () -> {
			Cliente cliente = new Cliente();
			List<Imovel> imoveis = null;
			cliente.setImoveis(imoveis);
		});
		assertEquals("Lista de imóveis não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testListaImoveisCorreto() {
		assertDoesNotThrow(() -> {
			Cliente cliente = new Cliente();
			List<Imovel> imoveis = new ArrayList<Imovel>();
			cliente.setImoveis(imoveis);
		});
	}

	@Test
	public void testAdicionarImovelNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Cliente cliente = new Cliente();
			Imovel imovel = null;
			cliente.adicionarImovel(imovel);
		});
		assertEquals("Imóvel não deve ser nulo.", exception.getMessage());
	}

	@Test
	public void testAdicionarImovelCorreto() {
		assertDoesNotThrow(() -> {
			Cliente cliente = new Cliente();
			Imovel imovel = new Imovel();
			cliente.adicionarImovel(imovel);
			System.out.println(cliente.getImoveis());
		});
	}
	
	@Test
	public void testExibirInformacoes() {
		assertDoesNotThrow(() -> {
			Cliente cliente = new Cliente();
			Imovel imovel = new Imovel();
			cliente.adicionarImovel(imovel);
			cliente.exibirInformacoes();
		});
	}
}
