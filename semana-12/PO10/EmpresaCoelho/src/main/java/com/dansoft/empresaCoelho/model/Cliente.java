
package com.dansoft.empresaCoelho.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dansoft.validations.Validations;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cpf", nullable = false, length = 14, unique = true)
	private String cpf;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@OneToMany(mappedBy = "cliente")
	private List<Imovel> imoveis;

	public Cliente() {
	}

	public Cliente(Integer id, String cpf, String nome, List<Imovel> imoveis) {
		this.cpf = cpf;
		this.nome = nome;
		this.imoveis = imoveis;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		if (nome == null)
			throw new Exception("O nome não pode ser nulo.");

		String regex = "^(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+(?:\\-(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+)*(?: (?:(?:e|y|de(?:(?: la| las| lo| los))?|do|dos|da|das|del|van|von|bin|le) )?(?:(?:(?:d'|D'|O'|Mc|Mac|al\\-))?(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+|(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+(?:\\-(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+)*))+(?: (?:Jr\\.|II|III|IV))?$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(nome);

		if (!matcher.matches()) {
			throw new Exception(
					"O nome não pode conter:\nCaracteres especiais(exceto espaços)\nMúltiplos conectivos seguidos\nIniciais minúsculas\nNomes Únicos.");
		}
		this.nome = nome;

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws Exception {
		Validations validationCpf = new Validations();
		if (cpf == null)
			throw new Exception("O CPF não pode ser nulo.\n");

		if (!validationCpf.isValidCpf(cpf)) {
			throw new Exception("CPF inválido. Verifique se o CPF é válido ou se está no formato correto.\n");
		}
		this.cpf = cpf;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) throws Exception {
		if (imoveis == null)
			throw new Exception("Lista de imóveis não pode ser nula.\n");
		this.imoveis = imoveis;
	}

	public void exibirInformacoes() {
		System.out.println("-----------------------------------------------");
		System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf());
		System.out.println("-----------------------------------------------");
	}
}
