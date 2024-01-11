package empresaTransporte;

public class Motorista extends Pessoa {
	private String jornadaTrabalho;
	private Jornada jornada;
	
	public Motorista(String nome, String cpf, String jornadaTrabalho, Jornada jornada) {
		super(nome, cpf);
		this.jornadaTrabalho = jornadaTrabalho;
		this.jornada = jornada;
	}

	public String getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public void setJornadaTrabalho(String jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}
}
