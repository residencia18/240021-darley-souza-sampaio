package com.example.atividades.atividade08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

class StatisticsTest {

	private Statistics statistics = new Statistics();
	private String expectedMessage = "The list of numbers cannot be empty";

	@Test
	void testCalculateAverageWithValidNumbers() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		double expectedAverage = 3.0;
		double actualAverage = statistics.calculateAverage(numbers);

		assertEquals(expectedAverage, actualAverage);
	}

	@Test
	void testCalculateAverageWithSingleNumber() {
		List<Integer> numbers = Collections.singletonList(10);

		double expectedAverage = 10.0;
		double actualAverage = statistics.calculateAverage(numbers);

		assertEquals(expectedAverage, actualAverage);
	}

	@Test
	void testCalculateAverageWithNegativeNumbers() {
		List<Integer> numbers = Arrays.asList(-1, -2, -3, -4, -5);

		double expectedAverage = -3.0;
		double actualAverage = statistics.calculateAverage(numbers);

		assertEquals(expectedAverage, actualAverage);
	}

	@Test
	void testCalculateAverageWithMixedNumbers() {
		List<Integer> numbers = Arrays.asList(-1, 0, 1, 2, -2);

		double expectedAverage = 0.0;
		double actualAverage = statistics.calculateAverage(numbers);

		assertEquals(expectedAverage, actualAverage);
	}

	@Test
	void testCalculateAverageWithEmptyList() {
		List<Integer> numbers = Collections.emptyList();

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			statistics.calculateAverage(numbers);
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCalculateAverageWithNullList() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			statistics.calculateAverage(null);
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
