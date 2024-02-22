package com.dansoft.empresaCoelhoClasses;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dansoft.validations.Validations;

public class Cliente implements Serializable {
	private static final long serialVersionUID = -3873854557447093176L;
	private Integer id;
	private String nome;
	private String cpf;

	public Cliente(Integer id, String nome, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}

	public Cliente() {
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		if (nome != null) {
			String regex = "^(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+(?:\\-(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+)*(?: (?:(?:e|y|de(?:(?: la| las| lo| los))?|do|dos|da|das|del|van|von|bin|le) )?(?:(?:(?:d'|D'|O'|Mc|Mac|al\\-))?(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+|(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+(?:\\-(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+)*))+(?: (?:Jr\\.|II|III|IV))?$";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(nome);

			if (!matcher.matches()) {
				throw new Exception(
						"O nome não pode conter:\nCaracteres especiais(exceto espaços)\nMúltiplos conectivos seguidos\nIniciais minúsculas\nNomes Únicos.");
			}
			this.nome = nome;

		} else {
			throw new Exception("O nome não pode ser nulo.");
		}

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws Exception {
		Validations validationCpf = new Validations();
		if (cpf != null) {

			if (!validationCpf.isValidCpf(cpf)) {
				throw new Exception("CPF inválido. Verifique se o CPF é válido ou se está no formato correto.\n");
			}

			this.cpf = cpf;

		} else {
			throw new Exception("O CPF não pode ser nulo.\n");
		}

	}

	public void exibirInformacoes() {
		System.out.println("-----------------------------------------------");
		System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf());
		System.out.println("-----------------------------------------------");
	}

}
