package main;

import java.util.stream.IntStream;

public class MultipleSummer {
    private static final int START = 1;
    private int number1;
    private int number2;
    private int limit;

    public MultipleSummer(int number1, int number2, int limit) {
        this.number1 = number1;
        this.number2 = number2;
        this.limit = limit;
    }

    public int sumMultiples() {
        return IntStream.range(START, limit)
                .filter(number -> (number % number1) == 0 || (number % number2) == 0)
                .sum();
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
