package main;

public class SpiralDiagonals {

    public long sumDiagonals(int size) {
        checkSize(size);
        long sum = 1;
        while (1 < size) {
            sum += countCorners(size);
            size -= 2;
        }
        return sum;
    }

    private void checkSize(int size) {
        if (size % 2 == 0) {
            throw new IllegalArgumentException("The size of the spiral must be odd.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("The size of the spiral must be positive.");
        }
    }

    private long countCorners(int size) {
        long current = size * size;
        long sum = 0;
        for (int i = 0; i < 4; i++) {
            sum+=current;
            current -= size - 1;
        }
        return sum;
    }
}
