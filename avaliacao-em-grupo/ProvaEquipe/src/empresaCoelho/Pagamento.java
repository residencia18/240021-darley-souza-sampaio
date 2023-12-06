package empresaCoelho;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pagamento {
	private LocalDate data;
	private double valor;

	public Pagamento(double valor) {
        this.data = LocalDate.now();
        this.valor = valor;
    }

	public LocalDate getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}
	
}
