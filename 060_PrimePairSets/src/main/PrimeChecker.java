package main;

public class PrimeChecker {

    private final PrimeSupplier supplier;

    public PrimeChecker(PrimeSupplier supplier) {
        this.supplier = supplier;
    }

    /*
    Two primes are remarkable (considering this approach) if
    both of their concatenated versions are primes as well.
    E.g.: 7 and 3 -> the numbers 37 and 73 are also primes.
     */
    public boolean areRemarkablePrimes(int a, int b) {
        int ab = concat(a, b);
        if (isPrime(ab)) {
            int ba = concat(b, a);
            return isPrime(ba);
        }
        return false;
    }

    public boolean isPrime(int number) {
        while (number > supplier.getCurrent()) {
            supplier.get();
        }
        return supplier.getPrimes().contains(number);
    }

    public int concat(int n1, int n2) {
        int n3 = n2;
        while(n3 > 0) {
            n1 *= 10;
            n3 /= 10;
        }
        return n1 + n2;
    }
}
