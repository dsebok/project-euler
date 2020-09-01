package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TripletFinder {

    private final int summa;
    private final int max;
    private final List<Integer> squareList = new ArrayList<>();

    public TripletFinder(int summa) {
        this.summa = summa;
        this.max = summa / 2;
    }

    public List<Integer> getFirstMatchingTriplet() {
        initialize();
        List<Integer> result = new ArrayList<>();
        boolean found = false;
        int a = 1;
        while (!found && a < max) {
            int b = 1;
            while (!found && b < max) {
                if (arePartOfTriplet(a, b)) {
                    int c = (int) Math.sqrt(sumOfSquares(a, b));
                    if (a + b + c == summa) {
                        found = true;
                        result.add(a);
                        result.add(b);
                        result.add(c);
                    }
                }
                ++b;
            }
            ++a;
        }
        return result;
    }

    private boolean arePartOfTriplet(int a, int b) {
        return squareList.stream()
                .anyMatch(square -> square == sumOfSquares(a, b));
    }

    private int sumOfSquares(int a, int b) {
        return a * a + b * b;
    }

    private void initialize() {
        IntStream.range(1, max).map(i -> i*i)
                .forEach(squareList::add);
    }

    public static void main(String[] args) {
        TripletFinder finder = new TripletFinder(1000);
        System.out.println(finder.getFirstMatchingTriplet().stream().peek(System.out::println).reduce(1, (a, b) -> a * b));
    }
}
