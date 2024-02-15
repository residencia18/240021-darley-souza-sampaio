package com.dansoft.empresaCoelhoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dansoft.empresaCoelho.Fatura;
import com.dansoft.empresaCoelho.Pagamento;

class FaturaTest {

	@Test
	public void testDataCorreto() {
		assertDoesNotThrow(() -> {
			Fatura fatura = new Fatura();
			String data = "15/04/1998";
			SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFormatada = formatData.parse(data);
			fatura.setData(dataFormatada);
		});
	}

	@Test
	public void testDataNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Fatura fatura = new Fatura();
			fatura.setData(null);
		});
		assertEquals("A data não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testUltimaLeituraCorreto() {
		assertDoesNotThrow(() -> {
			Fatura fatura = new Fatura();
			fatura.setUltimaLeiutra("123");
		});
	}

	@Test
	public void testUltimaLeituraNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Fatura fatura = new Fatura();
			fatura.setUltimaLeiutra(null);
		});
		assertEquals("Última leitura não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testPenultimaLeituraCorreto() {
		assertDoesNotThrow(() -> {
			Fatura fatura = new Fatura();
			fatura.setPenultimaLeitura("123");
		});
	}

	@Test
	public void testPenultimaLeituraNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Fatura fatura = new Fatura();
			fatura.setPenultimaLeitura(null);
		});
		assertEquals("Penúltima leitura não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testValorCorreto() {
		assertDoesNotThrow(() -> {
			Fatura fatura = new Fatura();
			fatura.setValor(123);
		});
	}

	@Test
	public void testValorZero() {
		Exception exception = assertThrows(Exception.class, () -> {
			Fatura fatura = new Fatura();
			fatura.setValor(0);
		});
		assertEquals("O valor tem que ser maior que zero.", exception.getMessage());
	}

	@Test
	public void testIsQuitadoNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Fatura fatura = new Fatura();
			fatura.setQuitado((Boolean) null);
		});
		assertEquals("Quitado não deve ser nulo.", exception.getMessage());
	}

	@Test
	public void testIsQuitadoCorreto() {
		assertDoesNotThrow(() -> {
			Fatura fatura = new Fatura();
			fatura.setQuitado(false);
		});
	}

	@Test
	public void testCalculaValor() {
		Fatura fatura = new Fatura();
		fatura.calculaValor(52340, 53430);
	}

	@Test
	public void testPagamentosNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Fatura fatura = new Fatura();
			List<Pagamento> pagamento = null;
			fatura.setPagamentos(pagamento);
		});
		assertEquals("Pagamentos não deve ser nulo.", exception.getMessage());
	}

	@Test
	public void testRealizarPagamentosValorZero() {
		Exception exception = assertThrows(Exception.class, () -> {
			Fatura fatura = new Fatura();
			Pagamento pagamento = new Pagamento();
			fatura.realizarPagamento(0, pagamento);
		});
		assertEquals("O valor a ser pago tem que ser maior que 0.", exception.getMessage());
	}

	@Test
	public void testRealizarPagamentoPagamentoNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Fatura fatura = new Fatura();
			Pagamento pagamento = null;
			fatura.realizarPagamento(55, pagamento);
		});
		assertEquals("Pagamento não deve ser nulo.", exception.getMessage());
	}

	@Test
	public void testRealizarPagamentoCorreto() {
		assertDoesNotThrow(() -> {
			Fatura fatura = new Fatura();
			Pagamento pagamento = new Pagamento();
			fatura.realizarPagamento(55, pagamento);
		});
	}
	
	@Test
	public void testRealizarPagamentoPagamentosRealizados() {
		assertDoesNotThrow(() -> {
			Fatura fatura = new Fatura();
			Pagamento pagamento = new Pagamento();
			Date date = new Date();
			pagamento.setValor(35);
			pagamento.setData(date);
			fatura.realizarPagamento(55, pagamento);
			fatura.realizarPagamento(35, pagamento);
		});
	}
	
}
