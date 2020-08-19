package main;

import java.util.List;

public class PrimeSetFinder {

    private final PrimeSupplier supplier = new PrimeSupplier();
    private final PrimeChecker checker = new PrimeChecker(supplier);

    public boolean isRemarkablePrimeGroup(List<Long> primeGroup) {
        while (primeGroup.size() > 1) {
            long current = primeGroup.remove(0);
            for (long prime : primeGroup) {
                if (!areRemarkablePrimes(current, prime)) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    Two primes are remarkable (considering this approach) if
    both of their concatenated versions are primes as well.
    E.g.: 7 and 3 -> the numbers 37 and 73 are also primes.
     */
    public boolean areRemarkablePrimes(Long a, Long b) {
        boolean result = false;
        long ab = Long.parseLong((a.toString() + b.toString()));
        if (checker.isPrime(ab)) {
            long ba = Long.parseLong((b.toString() + a.toString()));
            if (checker.isPrime(ba)) {
                result = true;
            }
        }
        return result;
    }
}
