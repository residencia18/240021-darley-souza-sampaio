package empresaCoelho;
import java.time.LocalDate;

public class Falha {
	
	private static int contadorFalhas = 0;

	private int numero;
	private String descricao;
	private String previsao;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private boolean resolvido;
	private Imovel imovel;

	public Falha(Imovel imovel, String descricao, String previsao, LocalDate dataInicio) {
		this.numero = ++contadorFalhas;
		this.imovel = imovel;
		this.descricao = descricao;
		this.previsao = previsao;
		this.dataInicio = dataInicio;
		this.resolvido = false;
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

}
