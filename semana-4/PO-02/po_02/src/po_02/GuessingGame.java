package po_02;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
	private int randomNumber;
	private int guesses;

	public GuessingGame() {
		Random random = new Random();
		this.randomNumber = random.nextInt(100) + 1;
		this.guesses = 0;
	}

	public void playGame() {
		Scanner scanner2 = new Scanner(System.in);
		int guess;

		System.out.println("---- Jogo da Adivinhação ---- ");

		do {
			System.out.print("Entre com o valor: ");
			guess = scanner2.nextInt();
			guesses++;

			if (guess > randomNumber) {
				System.out.println("Alto!! Tente de Novo!!.");
				System.out.println();
			} else if (guess < randomNumber) {
				System.out.println("Baixo!! Tente de Novo!!.");
				System.out.println();
			} else {
				System.out.println("Parabéns, você adivinhou o número em " + guesses + " tentativas.");
				System.out.println();
			}

		} while (guess != randomNumber);

	}
}
