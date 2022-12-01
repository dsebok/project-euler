package main.collatz;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CollatzSequence {
	
	public long next(long current) {
		if (current == 1) {
			throw new InvalidElementException("There are no more elements after 1.");
		}
		if (current < 1) {
			throw new InvalidElementException("Negative numbers are not part of the sequence.");
		}
		if (current % 2 == 0) {
			return current / 2;
		} else {
			long result = 3 * current + 1;
			if (result < current) {				
				throw new InvalidElementException("An overflow occured during the calculation of the next element.");
			}
			return result;
		}
	}
	
	public List<Long> getSequence(long start) {
		List<Long> result = getSequenceWithout1(start);
		result.add(1L);
		return result;
	}

	private List<Long> getSequenceWithout1(long start) {
		return LongStream.iterate(start, this::next)
				.takeWhile(i -> i > 1)
				.boxed()
				.collect(Collectors.toCollection(ArrayList::new));
	}
}