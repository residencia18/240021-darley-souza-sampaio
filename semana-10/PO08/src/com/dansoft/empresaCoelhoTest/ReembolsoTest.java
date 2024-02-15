package com.dansoft.empresaCoelhoTest;

import com.dansoft.empresaCoelho.Reembolso;
import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

class ReembolsoTest {

	@Test
	public void testDataCorreto() {
		assertDoesNotThrow(() -> {
			Reembolso reembolso = new Reembolso();
			String data = "15/04/1998";
			SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFormatada = formatData.parse(data);
			reembolso.setData(dataFormatada);
		});
	}

	@Test
	public void testDataNulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			Reembolso reembolso = new Reembolso();
			reembolso.setData(null);
		});
		assertEquals("A data não deve ser nula.", exception.getMessage());
	}

	@Test
	public void testValorCorreto() {
		assertDoesNotThrow(() -> {
			Reembolso reembolso = new Reembolso();
			reembolso.setValor(123);
		});
	}

	@Test
	public void testValorZero() {
		Exception exception = assertThrows(Exception.class, () -> {
			Reembolso reembolso = new Reembolso();
			reembolso.setValor(0);
		});
		assertEquals("O valor tem que ser maior que zero.", exception.getMessage());
	}

}
