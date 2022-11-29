package eratosthenes;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import eratosthenes.supplier.PrimeSupplier;
import eratosthenes.supplier.RemarkablePrimeSupplier;

/**
 * Exercise: https://projecteuler.net/problem=60
 * @author Depiphron
 *
 */
public class PrimeSetFinder {

	private static final int MIN_PAIR_NUMBER = 4;
	private final PrimeSupplier supplier = new RemarkablePrimeSupplier();
	private final PrimeChecker checker = new PrimeChecker(supplier);

	public static void main(String[] args) {
		Instant start = Instant.now();
		PrimeSetFinder finder = new PrimeSetFinder();
		List<Integer> solution = finder.findPrimeGroup();
		System.out.println(solution);
		Instant end = Instant.now();
		System.out.println(solution.stream().mapToInt(i -> i).sum());
		Duration duration = Duration.between(start, end);
		System.out.println(duration.toMillis());
	}

	public List<Integer> findPrimeGroup() {
		Map<Integer, Set<Integer>> primePairs = new HashMap<>();
		while (supplier.hasNext()) {
			int current = supplier.getAsInt();
			Set<Integer> lowerPairsForCurrent = findLowerPairsForCurrent(current, primePairs.keySet());
			primePairs.put(current, lowerPairsForCurrent);
			lowerPairsForCurrent.forEach(pair -> primePairs.get(pair).add(current));
			if (lowerPairsForCurrent.size() >= MIN_PAIR_NUMBER) {
				Optional<List<Integer>> solutionPairs = getSolutionPairs(primePairs, lowerPairsForCurrent);
				if (solutionPairs.isPresent()) {
					List<Integer> result = new ArrayList<>(solutionPairs.get());
					result.add(current);
					return result;
				}
			}
		}
		return List.of();
	}

	private HashSet<Integer> findLowerPairsForCurrent(int current, Set<Integer> lowerPrimes) {
		return lowerPrimes.stream()
				.filter(prime -> checker.areRemarkablePrimes(current, prime))
				.collect(Collectors.toCollection(HashSet::new));
	}

	/**
	 * 
	 * @param primePairs - the map that contains the currently checked primes and their current pairs
	 * @param lowerPairsForCurrent - all the lower primes that are pairs for the current prime
	 * @return true if there are at least GROUP_SIZE - 1 primes amongst lowerPairsForCurrent that are all pairs to each other as well.
	 */
	private Optional<List<Integer>> getSolutionPairs(Map<Integer, Set<Integer>> primePairs, Set<Integer> lowerPairsForCurrent) {
		List<List<Integer>> possibleRemarkableGroups = getCombinations(lowerPairsForCurrent);
		return possibleRemarkableGroups.stream()
				.filter(primeGroup -> groupIsRemarkable(primePairs, primeGroup))
				.findAny();
	}

	private List<List<Integer>> getCombinations(Set<Integer> numbers) {
		List<Integer> indexedNumbers = List.copyOf(numbers);
		if (numbers.size() == MIN_PAIR_NUMBER) {
			return List.of(indexedNumbers);
		}
		Combinator combinator = new Combinator(numbers.size(), MIN_PAIR_NUMBER);
		List<List<Integer>> indexCombos = combinator.getCombinations();
		return mapIndicesToElements(indexCombos, indexedNumbers);
	}

	private List<List<Integer>> mapIndicesToElements(List<List<Integer>> indexCombos, List<Integer> indexedNumbers) {
		return indexCombos.stream()
				.map(indexCombo -> indexCombo.stream()
						.map(index -> indexedNumbers.get(index))
						.toList())
				.toList();
	}

	private boolean groupIsRemarkable(Map<Integer, Set<Integer>> primePairs, List<Integer> primeGroup) {
		return primeGroup.stream()
				.allMatch(prime -> {
					Set<Integer> pairsOfPrime = primePairs.get(prime);
					Set<Integer> groupWithoutCurrent = new HashSet<>(primeGroup);
					groupWithoutCurrent.remove(prime);
					return pairsOfPrime.containsAll(groupWithoutCurrent);
				});
	}
}