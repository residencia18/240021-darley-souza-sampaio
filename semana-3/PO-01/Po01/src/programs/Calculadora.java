package programs;

public class Calculadora {
	private float value1;
	private float value2;

	public Calculadora(float value1, float value2) {
		super();
		this.value1 = value1;
		this.value2 = value2;
	}

	public float getValue1() {
		return value1;
	}

	public void setValue1(float value1) {
		this.value1 = value1;
	}

	public float getValue2() {
		return value2;
	}

	public void setValue2(float value2) {
		this.value2 = value2;
	}

	public float sum() {
		return getValue1() + getValue2();
	}

	public float sub() {
		return getValue1() - getValue2();
	}

	public float div() {
		return getValue1() / getValue2();
	}

	public float mult() {
		return getValue1() * getValue2();
	}
}
