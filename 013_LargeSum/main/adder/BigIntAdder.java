package main.adder;

import java.math.BigInteger;
import java.util.List;

import main.adder.LargeNumberAdder;

public class BigIntAdder implements LargeNumberAdder {
	
	@Override
	public String sum(List<String> lines) {
		BigInteger sum = lines.stream()
			.map(BigInteger::new)
			.reduce(BigInteger.ZERO, (a, b) -> a.add(b));
		return sum.toString();
	}
}