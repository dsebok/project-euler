package main;

import java.util.ArrayList;

public class SmallestMultipleFinder {

    public long smallestMultipleOf(int max) {
        ArrayList<Long> factors = createFactorList(max);
        return multiply(factors);
    }

    private Long multiply(ArrayList<Long> factors) {
        return factors.stream().reduce(1l, (a, b) -> a*b);
    }

    private ArrayList<Long> createFactorList(int max) {
        ArrayList<Long> factors = new ArrayList<>();
        for (long i = 2l; i <= max; i++) {
            long reduced = reduce(i, factors);
            if (reduced > 1) {
                factors.add(reduced);
            }
        }
        return factors;
    }

    private long reduce(long current, ArrayList<Long> factors) {
        return factors.stream()
                .reduce(current, (a, b) -> {
                    if (a % b == 0) {
                        return a / b;
                    } else {
                        return a;
                    }
                });
    }

    public static void main(String[] args) {
        SmallestMultipleFinder finder = new SmallestMultipleFinder();
        System.out.println(finder.smallestMultipleOf(20));
    }

}
