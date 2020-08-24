package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongSupplier;

public class PrimeSupplier implements LongSupplier {

    private int index = 0;
    private final long[] counters = {1, 1, 1, 1, 1};
    private final ArrayList<Long> primes = new ArrayList(List.of(2L,3L,5L,7L,11L));

    @Override
    public long getAsLong() {
        primes.add(getNextPrime());
        return primes.get(index++);
    }

    private long getNextPrime() {
        long number = getNextNonTrivialNumber();
        while (!isPrime(number)) {
            number = getNextNonTrivialNumber();
        }
        return number;
    }

    private long getNextNonTrivialNumber() {
        long number = primes.get(index);
        boolean isTrivial = true;
        while (isTrivial) {
            ++number;
            increaseCacheCounters();
            isTrivial = Arrays.stream(counters).anyMatch(counter -> counter == 0);
        }
        return number;
    }

    private void increaseCacheCounters() {
        if (++counters[0] == 2) {
            counters[0] = 0;
        }
        if (++counters[1] == 3) {
            counters[1] = 0;
        }
        if (++counters[2] == 5) {
            counters[2] = 0;
        }
        if (++counters[3] == 7) {
            counters[3] = 0;
        }
        if (++counters[4] == 11) {
            counters[4] = 0;
        }
    }

    private boolean isPrime(long number) {
        return primes.stream()
                .filter(prime -> prime <= Math.sqrt(number))
                .noneMatch(prime -> number % prime == 0);
    }

    public long getCurrent() {
        return primes.get(index);
    }

    public ArrayList<Long> getPrimes() {
        return primes;
    }

}
