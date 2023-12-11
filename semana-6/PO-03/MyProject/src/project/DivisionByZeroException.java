package project;

@SuppressWarnings("serial")
public class DivisionByZeroException extends Exception {
	public DivisionByZeroException() {
		super("Impossível divisão por zero!!!\n\n");
	}
}
