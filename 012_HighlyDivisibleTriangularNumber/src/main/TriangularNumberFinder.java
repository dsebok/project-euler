package main;

public class TriangularNumberFinder {

    public static final int DIVISOR_LIMIT = 500;
    TriangularSupplier supplier = new TriangularSupplier();

    public long findFirst() {
        long triangular = supplier.getAsLong();
        while (countDivisors(triangular) <= DIVISOR_LIMIT) {
            triangular = supplier.getAsLong();
        }
        return triangular;
    }

    public int countDivisors(long number) {
        if (number < 1) {
            throw new RuntimeException("Given number: " + number + ". This method only works for positive numbers.");
        }
        int divisors = 2;
        int divisor = 1;
        double root = Math.sqrt(number);
        int roundedRoot = (int) root;
        while (++divisor < root) {
            if (number % divisor == 0) {
                divisors += 2;
            }
        }
        if (root - roundedRoot == 0) {
            ++divisors;
        }
        return divisors;
    }

    public static void main(String[] args) {
        TriangularNumberFinder finder = new TriangularNumberFinder();
        System.out.println(finder.findFirst());
    }
}
