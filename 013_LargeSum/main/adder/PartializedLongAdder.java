package main.adder;

import java.util.List;

/**
 * @author Depiphron
 * 
 * When setting the VALID_NUMBER_SIZE variable, consider the
 * max digits a long value can handle. Do not forget to include
 * the space the extra digits will need that are created
 * on the result of the sum.
 */
public class PartializedLongAdder implements LargeNumberAdder {

	private static final int VALID_NUMBER_SIZE = 10;
	
	/**
	 * This method can only function normally, if
	 * - the length of each line is equal and
	 * - the length of each line is divisible by VALID_NUMBER_SIZE.
	 */
	public String sum(List<String> lines) {
		String result = "";
		String excessPart = "";
		long initialSum = 0;
		while (lines.get(0).length() > 0) {			
			long sumOfValidParts = sumLastValidPartOfLines(lines, initialSum);
			excessPart = getExcessPart(sumOfValidParts);
			result = getValidPart(sumOfValidParts) + result;
			lines = cutValidatedPartOfLines(lines);
			initialSum = Integer.valueOf(excessPart);
		}
		return excessPart + result;
	}

	private long sumLastValidPartOfLines(List<String> lines, long initialSum) {
		return lines.stream()
			.map(line -> line.substring(line.length() - VALID_NUMBER_SIZE))
			.map(stringVal -> Long.valueOf(stringVal))
			.reduce(initialSum, (a, b) -> a + b);
	}
	
	private String getExcessPart(long nr) {
		String stringNr = String.valueOf(nr);
		int sizeDiff = stringNr.length() - VALID_NUMBER_SIZE;
		return stringNr.substring(0, sizeDiff);
	}
	
	private String getValidPart(long nr) {
		String stringSum = String.valueOf(nr);
		return stringSum.substring(stringSum.length() - VALID_NUMBER_SIZE);
	}

	private List<String> cutValidatedPartOfLines(List<String> lines) {
		return lines.stream()
			.map(line -> line.substring(0, line.length() - VALID_NUMBER_SIZE))
			.toList();
	}
}