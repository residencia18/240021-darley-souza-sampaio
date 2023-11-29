package po_02;

import java.util.Random;
import java.util.Scanner;

public class ArrayHandler {
	public static int[] createArrayFromUserInput() {
		Scanner scanner2 = new Scanner(System.in);

		System.out.print("Informe o tamanho do array: ");
		int size = scanner2.nextInt();

		int[] array = new int[size];

		System.out.println("Digite os elementos do array:");

		for (int i = 0; i < size; i++) {
			System.out.print((i+1) + "° Elemento: ");
			array[i] = scanner2.nextInt();
		}

		System.out.println();
		return array;
	}

	public static int[] createRandomArray() {
		int size, min, max;
		Scanner scanner3 = new Scanner(System.in);

		System.out.print("Tamanho do Array Randômico: ");
		size = scanner3.nextInt();

		System.out.print("Valor Mínimo: ");
		min = scanner3.nextInt();

		System.out.print("Valor Máximo: ");
		max = scanner3.nextInt();

		Random random = new Random();
		int[] array = new int[size];

		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(max - min + 1) + min;
		}

		return array;
	}

	public static int calculateSum(int[] array) {
		int sum = 0;

		for (int value : array)
			sum += value;

		return sum;
	}

	public static int findMax(int[] array) {
		int max = array[0];

		for (int value : array) {
			if (value > max)
				max = value;
		}

		return max;
	}

	public static int findMin(int[] array) {
		int min = array[0];

		for (int value : array) {
			if (value < min)
				min = value;
		}

		return min;
	}
}
