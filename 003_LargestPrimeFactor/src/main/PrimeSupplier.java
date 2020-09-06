package main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;

public class PrimeSupplier implements IntSupplier {

    private int index = 0;
    private final List<Integer> primes = new ArrayList(List.of(2,3));

    @Override
    public int getAsInt() {
        primes.add(getNextPrime());
        return primes.get(index++);
    }

    private int getNextPrime() {
        int number = primes.get(primes.size()-1) + 2;
        while (isNotPrime(number)) {
            number += 2;
        }
        return number;
    }

    private boolean isNotPrime(long number) {
        return primes.stream()
                .takeWhile(prime -> prime*prime <= number)
                .anyMatch(prime -> number % prime == 0);
    }

    public int getCurrent() {
        return primes.get(index);
    }

    public List<Integer> getPrimes() {
        return primes;
    }

}
