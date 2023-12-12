package calculadoras;

public class Calculadora {
	@SuppressWarnings("unchecked")
	public <T extends Number> T soma(T a, T b) {
		if (a instanceof Integer && b instanceof Integer) {
			return (T) (Object) (a.intValue() + b.intValue());
		} else if (a instanceof Double || b instanceof Double) {
			return (T) (Object) (a.doubleValue() + b.doubleValue());
		} else {
			throw new IllegalArgumentException("Tipos de dados incompatíveis");
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Number> T subtracao(T a, T b) {
		if (a instanceof Integer && b instanceof Integer) {
			return (T) (Object) (a.intValue() - b.intValue());
		} else if (a instanceof Double || b instanceof Double) {
			return (T) (Object) (a.doubleValue() - b.doubleValue());
		} else {
			throw new IllegalArgumentException("Tipos de dados incompatíveis");
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Number> T multiplicacao(T a, T b) {
		if (a instanceof Integer && b instanceof Integer) {
			return (T) (Object) (a.intValue() * b.intValue());
		} else if (a instanceof Double || b instanceof Double) {
			return (T) (Object) (a.doubleValue() * b.doubleValue());
		} else {
			throw new IllegalArgumentException("Tipos de dados incompatíveis");
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Number> T divisao(T a, T b) {
		try {
			if (b.intValue() != 0) {
				if (a instanceof Integer && b instanceof Integer) {
					return (T) (Object) (a.intValue() / b.intValue());
				} else if (a instanceof Double || b instanceof Double) {
					return (T) (Object) (a.doubleValue() / b.doubleValue());
				} else {
					throw new IllegalArgumentException("Tipos de dados incompatíveis");
				}
			} else
				throw new DivisionByZeroException();
		} catch (DivisionByZeroException e) {
			T c = null;
			System.out.println("Error: " + e.getMessage());
			return (c);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Number> T resto(T a, T b) {
		try {
			if (b.intValue() != 0) {
				if (a instanceof Integer && b instanceof Integer) {
					return (T) (Object) (a.intValue() % b.intValue());
				} else {
					throw new IllegalArgumentException("Tipos de dados incompatíveis");
				}
			} else
				throw new DivisionByZeroException();
		} catch (DivisionByZeroException e) {
			T c = null;
			System.out.println("Error: " + e.getMessage() + "\n");
			return c;
		}
	}
}