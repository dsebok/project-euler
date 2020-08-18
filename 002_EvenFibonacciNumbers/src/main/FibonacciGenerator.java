package main;

import java.util.stream.IntStream;

public class FibonacciGenerator {
    private static final int START = 1;
    private static final int LIMIT = 4000000;

    public int sumEvenElements() {
        return IntStream.generate(new FibonacciSupplier()).takeWhile(element -> element < LIMIT)
                .filter(number -> (number % 2) == 0)
                .sum();
    }
}