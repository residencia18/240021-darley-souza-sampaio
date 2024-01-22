package empresaTransporte;

import java.io.Serializable;

public class Pessoa implements Serializable{
	private static final long serialVersionUID = 9220933879097232206L;
	private String nome;
	private String cpf;

	public Pessoa(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Pessoa() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
