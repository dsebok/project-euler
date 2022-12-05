package main;

import java.math.BigInteger;
import java.util.LinkedList;

public class PowerDigitSum {


	private static final int POWER = 1000;

	public static void main(String[] args) {
		new PowerDigitSum().run();
	}

	private void run () {
		String numberAsString = getPowerOf2_(POWER);
		long sum = sumDigits(numberAsString);
		System.out.println(sum);
	}

	private long sumDigits(String numberAsString) {
		long sum = 0;
		for (char ch : numberAsString.toCharArray()) {
			sum += Integer.parseInt(String.valueOf(ch));
		}
		return sum;
	}

	private String getPowerOf2(int power) {
		return BigInteger.TWO.pow(power).toString();
	}

	private String getPowerOf2_(int power) {
		PowerNumber powerNumber = new PowerNumber(2);
		powerNumber.setNthPower(power);
		return powerNumber.toString();
	}

	private static final class PowerNumber {

		private static final int DIGIT_LIMIT = 10;
		private final LinkedList<Long> numberParts = new LinkedList<>();
		private final int base;

		public PowerNumber(int base) {
			this.base = base;
		}

		public void setNthPower(int power) {
			numberParts.clear();
			numberParts.add(1L);
			while (power-- > 0) {
				long excess = 0;
				for (int i = 0; i < numberParts.size(); ++i) {
					long current = numberParts.get(i);
					current *= base;
					current += excess;
					if (current >= Math.pow(10, DIGIT_LIMIT)) {
						String strVal = String.valueOf(current);
						String validPart = strVal.substring(strVal.length() - DIGIT_LIMIT);
						String excessPart = strVal.substring(0, strVal.length() - DIGIT_LIMIT);
						current = Long.parseLong(validPart);
						excess = Long.parseLong(excessPart);
					} else {
						excess = 0;
					}
					numberParts.set(i, current);
				}
				if (excess > 0) {
					numberParts.add(excess);
				}
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			numberParts.descendingIterator().forEachRemaining(sb::append);
			return sb.toString();
		}
	}
}