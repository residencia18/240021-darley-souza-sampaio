package com.dansoft.empresaCoelhoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dansoft.empresaCoelho.Fatura;
import com.dansoft.empresaCoelho.Imovel;

class ImovelTest {

	@Test
	public void testMatriculaCorreto() {
		assertDoesNotThrow(() -> {
			Imovel imovel = new Imovel();
			imovel.setMatricula("123");
		});
	}

	@Test
	public void testMatriculaNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Imovel imovel = new Imovel();
			imovel.setMatricula(null);
		});
		assertEquals("A matrícula não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testEnderecoCorreto() {
		assertDoesNotThrow(() -> {
			Imovel imovel = new Imovel();
			imovel.setEndereco("123");
		});
	}

	@Test
	public void testEnderecoNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Imovel imovel = new Imovel();
			imovel.setEndereco(null);
		});
		assertEquals("O endereço não deve ser nulo.", exception.getMessage());
	}

	@Test
	public void testUltimaLeituraCorreto() {
		assertDoesNotThrow(() -> {
			Imovel imovel = new Imovel();
			imovel.setUltimaLeitura("123");
		});
	}

	@Test
	public void testUltimaLeituraIncorreto() {
		Exception exception = assertThrows(Exception.class, () -> {
			Imovel imovel = new Imovel();
			imovel.setUltimaLeitura("123asd");
		});

		assertEquals("A leitura deve conter somente números.", exception.getMessage());
	}

	@Test
	public void testUltimaLeituraNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Imovel imovel = new Imovel();
			imovel.setUltimaLeitura(null);
		});
		assertEquals("Última leitura não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testPenultimaLeituraCorreto() {
		assertDoesNotThrow(() -> {
			Imovel imovel = new Imovel();
			imovel.setPenultimaLeitura("123");
		});
	}

	@Test
	public void testPenltimaLeituraIncorreto() {
		Exception exception = assertThrows(Exception.class, () -> {
			Imovel imovel = new Imovel();
			imovel.setPenultimaLeitura("123asd");
		});

		assertEquals("A leitura deve conter somente números.", exception.getMessage());
	}

	@Test
	public void testPenultimaLeituraNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Imovel imovel = new Imovel();
			imovel.setPenultimaLeitura(null);
		});
		assertEquals("Penúltima leitura não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testAdicionarFaturaNula() {
		Exception exception = assertThrows(Exception.class, () -> {
			Imovel imovel = new Imovel();
			Fatura fatura = null;
			imovel.adicionarFatura(fatura);
		});
		assertEquals("Fatura não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testSetFaturasNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Imovel imovel = new Imovel();
			List<Fatura> faturas = null;
			imovel.setFaturas(faturas);
		});
		assertEquals("Faturas não deve ser nulo.", exception.getMessage());
	}
	
	@Test
	public void testSetFaturasCorreto() {
		assertDoesNotThrow( () -> {
			Imovel imovel = new Imovel();
			List<Fatura> faturas = new ArrayList<Fatura>();
			imovel.setFaturas(faturas);
		});
	}

	@Test
	public void testAdicionarFaturaCorreto() {
		assertDoesNotThrow(() -> {
			Imovel imovel = new Imovel();
			Fatura fatura = new Fatura();
			Date data = new Date();
			String ultimaLeitura = "6";
			String penultimaLeitura = "5006";
			fatura.setData(data);
			fatura.setPenultimaLeitura(penultimaLeitura);
			fatura.setUltimaLeiutra(ultimaLeitura);
			fatura.calculaValor(Integer.parseInt(ultimaLeitura), Integer.parseInt(penultimaLeitura));
			fatura.setQuitado(false);
			imovel.adicionarFatura(fatura);
		});
	}

}
