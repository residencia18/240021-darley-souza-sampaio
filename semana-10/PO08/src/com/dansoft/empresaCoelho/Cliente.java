package com.dansoft.empresaCoelho;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dansoft.validations.Validations;

public class Cliente implements Serializable {
	private static final long serialVersionUID = -3873854557447093176L;
	private String nome;
	private String cpf;
	private List<Imovel> imoveis = new ArrayList<Imovel>();

	public Cliente() {
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
				throw new Exception("CPF inválido. Formato correto é XXX.XXX.XXX-XX.");
			}

			this.cpf = cpf;

		} else {
			throw new Exception("O CPF não pode ser nulo.");
		}

	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) throws Exception {
		if(imoveis == null)
			throw new Exception("Lista de imóveis não deve ser nula.");
		this.imoveis = imoveis;
	}

	public void adicionarImovel(Imovel imovel) throws Exception {
		if (imovel != null)
			imoveis.add(imovel);
		else
			throw new Exception("Imóvel não deve ser nulo.");
	}
	public void exibirInformacoes() {
		System.out.println("-----------------------------------------------");
		System.out.println("Nome: " + this.getNome() + "\nCPF: " + this.getCpf());
		if (imoveis != null) {
			for (Imovel imovel : imoveis) {
				imovel.exibirInformacoes();
			}
		}
		System.out.println("-----------------------------------------------");
	}

}
