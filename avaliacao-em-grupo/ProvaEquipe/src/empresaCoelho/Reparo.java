package empresaCoelho;
import java.time.LocalDate;

public class Reparo {
	private static int contadorReparos = 0;

	private int numero;
	private String descricao;
	private String previsao;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private boolean resolvido;
	private Falha falha;

	public Reparo(Falha falha, String descricao, String previsao, LocalDate dataInicio) {
		this.numero = ++contadorReparos;
		this.falha = falha;
		this.descricao = descricao;
		this.previsao = previsao;
		this.dataInicio = dataInicio;
		this.resolvido = false;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isResolvido() {
		return resolvido;
	}

	public void setResolvido(boolean resolvido) {
		this.resolvido = resolvido;
		if (resolvido) {
			this.dataFim = LocalDate.now();
		}
	}

	public Falha getFalha() {
		return falha;
	}
}
