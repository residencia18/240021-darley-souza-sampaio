package com.dansoft.empresaCoelhoTest;

import com.dansoft.empresaCoelho.Pagamento;
import com.dansoft.empresaCoelho.Reembolso;

import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

class PagamentoTest {

	@Test
	public void testSetDataCorreto() {
		assertDoesNotThrow(() -> {
			Pagamento pagamento = new Pagamento();
			String data = "15/04/1998";
			SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFormatada = formatData.parse(data);
			pagamento.setData(dataFormatada);
		});
	}

	@Test
	public void testSetDataNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Pagamento pagamento = new Pagamento();
			pagamento.setData(null);
		});
		assertEquals("A data não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testSetValorCorreto() {
		assertDoesNotThrow(() -> {
			Pagamento pagamento = new Pagamento();
			pagamento.setValor(123);
		});
	}

	@Test
	public void testSetValorZero() {
		Exception exception = assertThrows(Exception.class, () -> {
			Pagamento pagamento = new Pagamento();
			pagamento.setValor(0);
		});
		assertEquals("O valor tem que ser maior que zero.", exception.getMessage());
	}
	
	@Test
	public void testSetReembolsoNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Pagamento pagamento = new Pagamento();
			Reembolso reembolso = null;
			pagamento.setReembolso(reembolso);
		});
		assertEquals("Reembolso não deve ser nulo.", exception.getMessage());
	}
	
	@Test
	public void testSetReembolsoCorreto() {
		assertDoesNotThrow(() -> {
			Pagamento pagamento = new Pagamento();
			Reembolso reembolso = new Reembolso();
			pagamento.setReembolso(reembolso);
		});
	}
	
	@Test
	public void testAplicarReembolsoCorreto() {
		assertDoesNotThrow(() -> {
			Pagamento pagamento = new Pagamento();
			Date date = new Date();
			pagamento.aplicarReembolso(50, date);
		});
	}
	
	@Test
	public void testAplicarReembolsoValorZero() {
		Exception exception = assertThrows(Exception.class, () -> {
			Pagamento pagamento = new Pagamento();
			Date date = new Date();
			pagamento.aplicarReembolso(0, date);
		});
		assertEquals("O valor deve ser acima de 0.", exception.getMessage());
	}
	
	@Test
	public void testAplicarReembolsoDataNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Pagamento pagamento = new Pagamento();
			Date date = null;
			pagamento.aplicarReembolso(55, date);
		});
		assertEquals("A data não deve ser nula.", exception.getMessage());
	}

}
