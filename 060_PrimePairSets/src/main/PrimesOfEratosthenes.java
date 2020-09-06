package main;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrimesOfEratosthenes {

    private final int limit;
    private final int crossLimit;

    public PrimesOfEratosthenes(int limit) {
        this.limit = limit;
        this.crossLimit = (int) Math.sqrt(limit) / 2;
    }

    /**
     * Gives back all the primes under limit
     *
     * @return a List that contains all the primes under the limit (given in constructor).
     */
    public List<Integer> get() {
        int[] sieve = initSieve();

        int index = 0;
        while (++index <= crossLimit) {
            if (sieve[index] != 0) {
                int value = index * 2 + 1;
                int cross = (value * value - 1) / 2;
                while (cross < limit / 2) {
                    sieve[cross] = 0;
                    cross += value;
                }
            }
        }
        return Arrays.stream(sieve).filter(number -> number != 0).boxed().collect(Collectors.toList());
    }

    private int[] initSieve() {
        int[] sieve = new int[limit / 2];
        Arrays.setAll(sieve, i -> i * 2 + 1);
        sieve[0] = 2;
        return sieve;
    }

    public static void main(String[] args) {
        PrimesOfEratosthenes primes = new PrimesOfEratosthenes(100000000);
        Instant start = Instant.now();
        primes.get();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

}