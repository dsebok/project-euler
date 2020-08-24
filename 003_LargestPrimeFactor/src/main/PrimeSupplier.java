package main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongSupplier;

public class PrimeSupplier implements LongSupplier {

    private int index = 0;
    private final List<Long> primes = new ArrayList(List.of(2L,3L));

    @Override
    public long getAsLong() {
        if (index == primes.size()) {
            primes.add(getNextPrime());
        }
        return primes.get(index++);
    }

    private long getNextPrime() {
        long number = primes.get(primes.size()-1) + 2;
        while (!isPrime(number)) {
            number += 2;
        }
        return number;
    }

    private boolean isPrime(long number) {
        return primes.stream()
                .filter(prime -> prime*prime <= number)
                .noneMatch(prime -> number % prime == 0);
    }

    public long getCurrent() {
        return primes.get(index);
    }

    public List<Long> getPrimes() {
        return primes;
    }

}
