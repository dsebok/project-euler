package main;

import java.util.ArrayList;
import java.util.function.LongSupplier;

public class PrimeSupplier implements LongSupplier {
//2,3,5,7,11 caches for instant skips
    private long current = 1;
    private final ArrayList<Long> primes = new ArrayList<>();

    @Override
    public long getAsLong() {
        long result = getNextPrime();
        save(result);
        return result;
    }

    private long getNextPrime() {
        long number = ++current;
        while (!isPrime(number)) {
            number++;
        }
        return number;
    }

    private void save(long number) {
        current = number;
        primes.add(number);
    }

    private boolean isPrime(long number) {
        if (number == 2) {
            return true;
        }
        return primes.stream()
                .filter(prime -> prime <= Math.sqrt(number))
                .noneMatch(prime -> number % prime == 0);
    }

    public long getCurrent() {
        return current;
    }

    public ArrayList<Long> getPrimes() {
        return primes;
    }

}
