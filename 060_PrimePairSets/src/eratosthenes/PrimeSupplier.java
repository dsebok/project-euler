package eratosthenes;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

public class PrimeSupplier implements IntSupplier {

    public final List<Integer> primes = new PrimesOfEratosthenes(10000000).get();
    private int i = 0;

    @Override
    public int getAsInt() {
        return primes.get(i++);
    }

    public List<Integer> getLowerPrimes(int current) {
        return primes.stream().takeWhile(prime -> prime < current).collect(Collectors.toList());
    }

    public boolean primeListContains(int number) {
        return primes.stream().takeWhile(prime -> prime <= number).anyMatch(prime -> prime == number);
    }
}
