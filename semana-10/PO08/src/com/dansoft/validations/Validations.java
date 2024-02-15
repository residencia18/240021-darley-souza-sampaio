package com.dansoft.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

	public boolean isValidCpf(String cpf) {
		String regex = "^((\\d{3}).(\\d{3}).(\\d{3})-(\\d{2}))*$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cpf);

		return matcher.matches();
	}

	public boolean isValidLeitura(String leitura) {
		String regex = "\\d+";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(leitura);

		return matcher.matches();
	}

}
