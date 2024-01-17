package validateTransporte;

public class CpfValidator {

	public boolean isValidCpf(String cpf) {
		String cpfNumbersOnly = cpf.replaceAll("[^0-9]", "");

		if (cpfNumbersOnly.length() != 11) {
			return false;
		}

		if (isAllDigitsEqual(cpfNumbersOnly)) {
			return false;
		}

		int[] cpfArray = convertStringToArray(cpfNumbersOnly);
		int digit1 = calculateDigit(cpfArray, 9);
		int digit2 = calculateDigit(cpfArray, 10);

		return cpfArray[9] == digit1 && cpfArray[10] == digit2;
	}

	private boolean isAllDigitsEqual(String cpf) {
		return cpf.matches("(\\d)\\1*");
	}

	private int[] convertStringToArray(String cpf) {
		int[] cpfArray = new int[11];
		for (int i = 0; i < 11; i++) {
			cpfArray[i] = Character.getNumericValue(cpf.charAt(i));
		}
		return cpfArray;
	}

	private int calculateDigit(int[] cpfArray, int position) {
		int sum = 0;
		for (int i = 0; i < position; i++) {
			sum += cpfArray[i] * (position + 1 - i);
		}
		int remainder = sum % 11;
		return remainder < 2 ? 0 : 11 - remainder;
	}

}
