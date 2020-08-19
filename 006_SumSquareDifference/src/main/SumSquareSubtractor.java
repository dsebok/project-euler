package main;

import java.util.stream.IntStream;

public class SumSquareSubtractor {

    public long subtractSquareSums(int min, int max) {
        return squareOfSum(min, max) - sumOfSquares(min, max);
    }

    public long sumOfSquares(int min, int max) {
        return IntStream.range(min, max+1).map(i -> i*i).sum();
    }

    public long squareOfSum(int min, int max) {
        long sum = IntStream.range(min, max+1).sum();
        return sum*sum;
    }

    public static void main(String[] args) {
        SumSquareSubtractor subtractor = new SumSquareSubtractor();
        System.out.println(subtractor.subtractSquareSums(1,100));
    }
}
