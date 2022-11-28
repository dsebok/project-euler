package eratosthenes.supplier;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PrimesOfEratosthenes {

	/**
	 * @return a List that contains all the primes under the limit.
	 */
	public Set<Integer> getPrimes(int limit) {
		int[] sieve = getPrefilteredSieve(limit);
		crossOutNonPrimes(limit, sieve);
		return extractNonZeroNumbers(sieve);
	}

	/**
	 * @return the sieve that consists the odd numbers below the limit and 2.
	 */
	private static int[] getPrefilteredSieve(int limit) {
		int[] numbers = new int[limit / 2];
		Arrays.setAll(numbers, i -> i * 2 + 1);
		numbers[0] = 2;
		return numbers;
	}

	/**
	 * Crosses out (sets to 0) all the non-prime numbers in the given array (prefilteredSieve).
	 * Prerequisite: the prefilteredSieve should only contain 2 and all the odd numbers between 2
	 * and the limit.
	 * The crossing is intentionally started from the square of the crosser prime, because below that
	 * there are only multiples of lesser primes that have already been checked.
	 */
	private void crossOutNonPrimes(int limit, int[] prefilteredSieve) {
		int crossIndexLimit = (int) Math.sqrt(limit) / 2;
		int index = 0;
		while (++index <= crossIndexLimit) {
			if (prefilteredSieve[index] != 0) {
				int crosserPrime = prefilteredSieve[index];
				int crossedNumberIndex = (crosserPrime * crosserPrime - 1) / 2;
				while (crossedNumberIndex < prefilteredSieve.length) {
					prefilteredSieve[crossedNumberIndex] = 0;
					crossedNumberIndex += crosserPrime;
				}
			}
		}
	}

	/**
	 * @return a List containing only the non-zero numbers from the given sieve.
	 */
	private Set<Integer> extractNonZeroNumbers(int[] numbers) {
		return Arrays.stream(numbers)
				.filter(number -> number != 0)
				.boxed()
				.collect(Collectors.toCollection(LinkedHashSet<Integer>::new));
	}

	public static void main(String[] args) {
		PrimesOfEratosthenes primes = new PrimesOfEratosthenes();
		Set<Integer> primeList = (Set<Integer>) benchmark(() -> primes.getPrimes(100_000_000));
		benchmark(() -> primeList.contains(99999989));
		System.out.println();
		// Lowest duration for limit 100_000_000: 863 ms
	}
	
	private static Object benchmark(Supplier<?> supplier) {
		Instant start = Instant.now();
		Object result = supplier.get();
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		System.out.println(duration.toMillis());
		return result;
	}
}