package main;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import main.collatz.CollatzSequence;

public class LongestCollatzSequnceFinder {

	private static final int LIMIT = 1_000_000;
	private final CollatzSequence collatzSequence = new CollatzSequence();

	public static void main(String[] args) {
		new LongestCollatzSequnceFinder().run();
	}
	
	public void run() {
		Map<Long, Integer> sequenceSizes = calculateAllSequenceSizes();
		long result = getKeyOfMaxValue(sequenceSizes);
		System.out.println(result);
	}

	private Map<Long, Integer> calculateAllSequenceSizes() {
		Map<Long, Integer> sequenceSizes = new HashMap<>();
		sequenceSizes.put(1L, 1);
		int current = 2;
		while (current++ < LIMIT) {
			Deque<Long> newSequencePart = getNewSequencePart(sequenceSizes, current);
			saveNewSequenceSizes(sequenceSizes, newSequencePart);
		}
		return sequenceSizes;
	}

	private Deque<Long> getNewSequencePart(Map<Long, Integer> sequenceSizes, int current) {
		return LongStream.iterate(current, i -> collatzSequence.next(i))
			.takeWhile(i -> !sequenceSizes.containsKey(i))
			.boxed()
			.collect(Collectors.toCollection(ArrayDeque<Long>::new));
	}

	private void saveNewSequenceSizes(Map<Long, Integer> sequenceSizes, Deque<Long> newSequencePart) {
		int newPartSize = newSequencePart.size();
		if (newPartSize > 0) {
			long lastNewInSequence = newSequencePart.getLast();
			long firstInRemainingSequence = collatzSequence.next(lastNewInSequence);
			int remainingSequenceSize = sequenceSizes.get(firstInRemainingSequence);
			Iterator<Long> descIterator = newSequencePart.descendingIterator();
			IntStream.rangeClosed(1, newPartSize)
				.forEach(additionalSequenceSize -> sequenceSizes.put(descIterator.next(), additionalSequenceSize + remainingSequenceSize));
		}	
	}

	private Long getKeyOfMaxValue(Map<Long, Integer> numberMap) {
		return numberMap.entrySet().stream()
				.reduce((entry1, entry2) -> entry1.getValue() < entry2.getValue() ? entry2 : entry1)
				.get().getKey();
	}
}