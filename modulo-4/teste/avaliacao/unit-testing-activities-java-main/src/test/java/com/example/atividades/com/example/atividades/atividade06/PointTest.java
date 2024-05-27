package com.example.atividades.atividade06;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PointTest {
	@Test
	void testDistanceToWithValidPoints() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(3, 4);

		double expectedDistance = 5.0;
		double actualDistance = p1.distanceTo(p2);

		assertEquals(expectedDistance, actualDistance);
	}

	@Test
	void testDistanceToSamePoint() {
		Point p1 = new Point(1, 1);

		double expectedDistance = 0.0;
		double actualDistance = p1.distanceTo(p1);

		assertEquals(expectedDistance, actualDistance);
	}

	@Test
	void testDistanceToWithNegativeCoordinates() {
		Point p1 = new Point(-1, -1);
		Point p2 = new Point(-4, -5);

		double expectedDistance = 5.0;
		double actualDistance = p1.distanceTo(p2);

		assertEquals(expectedDistance, actualDistance);
	}

	@Test
	void testDistanceToWithNullPoint() {
		Point p1 = new Point(0, 0);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			p1.distanceTo(null);
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains("Argument must be a Point"));
	}

}
