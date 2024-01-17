package validateTransporte;

public class VeiculoValidator {
	public boolean isValidPlaca(String placa) {
		if (!placa.matches("[A-Z]{3}-\\d{4}")) {
			return false;
		}

		if (!Character.isLetter(placa.charAt(0)) || !Character.isLetter(placa.charAt(1))
				|| !Character.isLetter(placa.charAt(2))) {
			return false;
		}

		try {
			Integer.parseInt(placa.substring(4));
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public boolean isValidCar(String tipo) {
		if (tipo.equals("BÁSICO") || tipo.equals("PADRON") || tipo.equals("ARTICULADO")
				|| tipo.equals("BIARTICULADO")) {
			return true;
		}
		return false;
	}
}
