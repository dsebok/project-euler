package eratosthenes.supplier;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.function.IntSupplier;

public class PrimeSupplier implements IntSupplier {

    private final LinkedHashSet<Integer> primes = new PrimesOfEratosthenes().getPrimes(100_000_000);
    protected Iterator<Integer> iterator = primes.iterator();

    @Override
    public int getAsInt() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
    
    public boolean contains(int number) {
        return primes.contains(number);
    }
}