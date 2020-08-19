package main;

public class PrimeChecker {

    private final PrimeSupplier supplier;

    public PrimeChecker(PrimeSupplier supplier) {
        this.supplier = supplier;
    }

    public boolean isPrime(long number) {
        while (number > supplier.getCurrent()) {
            supplier.getAsLong();
        }
        return supplier.getPrimes().contains(number);
    }
}
