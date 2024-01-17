package exceptionsTransporte;

public class WrongCpfException extends Exception {
	private static final long serialVersionUID = -4506492704113577791L;
	public WrongCpfException() {
		super("CPF inválido. Verifique se o mesmo está correto e válido.\n");
	}
}
