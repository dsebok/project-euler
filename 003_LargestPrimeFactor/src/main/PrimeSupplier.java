package main;

import java.util.ArrayList;
import java.util.function.LongSupplier;

public class PrimeSupplier implements LongSupplier {

    private long current = 1;
    private final ArrayList<Long> primes = new ArrayList<>();

    @Override
    public long getAsLong() {
        long result = getNextPrime();
        save(result);
        return result;
    }

    private long getNextPrime() {
        long result = ++current;
        while (isDividableByRegisteredPrimes(result)) {
            result++;
        }
        return result;
    }

    private void save(long number) {
        current = number;
        primes.add(number);
    }

    private boolean isDividableByRegisteredPrimes(long number) {
        if (number == 2) {
            return false;
        }
        return primes.stream()
                .anyMatch(prime -> number % prime == 0);
    }


}
