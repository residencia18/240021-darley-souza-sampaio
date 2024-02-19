package com.dansoft.exceptions;

public class MenuWrongInputException extends Exception {
	private static final long serialVersionUID = 1203575265065716629L;

	public MenuWrongInputException() {
		super("Opção inválida. Tente novamente.\n");
	}
}