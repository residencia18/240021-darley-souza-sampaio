package project;

public class Calculadora {
	public void soma(double a, double b) {
		printResultado(a + b);
	}

	public void subtracao(double a, double b) {
		printResultado(a - b);
	}

	public void multiplicacao(double a, double b) {
		printResultado(a * b);
	}

	public void divisao(double a, double b) {
		try{
			if (b != 0) {		
				printResultado(a / b); 
			}else {
				throw new DivisionByZeroException();
			}
		}catch (DivisionByZeroException e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void resto(double a, double b) {
		try {
			if(b != 0)
				printResultado(a % b);
			else{
				throw new DivisionByZeroException();
			}
		} catch(DivisionByZeroException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void printResultado(double value) {
		System.out.println("\nResultado: " + value + "\n\n"); 
	}
}
