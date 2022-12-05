package main;

import java.util.ArrayList;
import java.util.List;

public class LexicographicPermutations {

    private static final List<Integer> NUMBERS = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    public static final int TARGET = 1000_000;
    private static int counter;

    public static void main(String[] args) {
        permute(List.of(), NUMBERS);
    }

    private static void permute(List<Integer> currentPermutation, List<Integer> numberPool) {
        if (numberPool.isEmpty()) {
            if (++counter == TARGET) {
                System.out.println(currentPermutation);
                System.exit(0);
            }
            return;
        }
        numberPool.forEach(nextNumber -> {
            List<Integer> nextPermutation = new ArrayList<>(currentPermutation);
            List<Integer> remainingNumbers = new ArrayList<>(numberPool);
            nextPermutation.add(nextNumber);
            remainingNumbers.remove(nextNumber);
            permute(nextPermutation, remainingNumbers);
        });
    }
}