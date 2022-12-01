package test.collatz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.collatz.CollatzSequence;

public class CollatzSequenceTest {

	private CollatzSequence collatzSequence;
	
	@BeforeEach
	public void setup() {
		collatzSequence = new CollatzSequence();
	}
	
	@Test
	void test_next_whenInputIsValid() {
		//GIVEN
		long number1 = 2; 
		long number2 = 15; 
		long number3 = 16; 
		long number4 = 99; 
		long number5 = 100;
		//WHEN
		long actual1 = collatzSequence.next(number1);
		long actual2 = collatzSequence.next(number2);
		long actual3 = collatzSequence.next(number3);
		long actual4 = collatzSequence.next(number4);
		long actual5 = collatzSequence.next(number5);
		//THEN
		assertEquals(1, actual1);
		assertEquals(46, actual2);
		assertEquals(8, actual3);
		assertEquals(298, actual4);
		assertEquals(50, actual5);
	}
	
	@Test
	void test_getSequence_whenInputIsValid() {
		//GIVEN
		long number1 = 2; 
		long number2 = 15; 
		long number3 = 16; 
		long number4 = 100; 
		//WHEN
		List<Long> actual1 = collatzSequence.getSequence(number1);
		List<Long> actual2= collatzSequence.getSequence(number2);
		List<Long> actual3= collatzSequence.getSequence(number3);
		List<Long> actual4 = collatzSequence.getSequence(number4);
		//THEN
		assertEquals(List.of(2L, 1L), actual1);
		assertEquals(List.of(15L, 46L, 23L, 70L, 35L, 106L, 53L, 160L, 80L,
				40L, 20L, 10L, 5L, 16L, 8L, 4L, 2L, 1L), actual2);
		assertEquals(List.of(16L, 8L, 4L, 2L, 1L), actual3);
		assertEquals(List.of(100L, 50L, 25L, 76L, 38L, 19L, 58L, 29L, 88L,
				44L, 22L, 11L, 34L, 17L, 52L, 26L, 13L, 40L, 20L, 10L, 5L, 16L, 8L, 4L, 2L, 1L), actual4);
	}
}