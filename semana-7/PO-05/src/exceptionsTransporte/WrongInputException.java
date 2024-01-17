package exceptionsTransporte;

public class WrongInputException extends Throwable {
	private static final long serialVersionUID = 2670185936603379523L;

	public WrongInputException() {
		super("- !!! Erro !!! -\nCertifique-se de que a entrada está correta.\n");
    }
}