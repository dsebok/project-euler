package main;

import java.util.ArrayList;
import java.util.List;

public class PrimeSetFinder {

    private final PrimeSupplier supplier = new PrimeSupplier();
    private final PrimeChecker checker = new PrimeChecker(supplier);

    public long findTheFifthMember() {
        List<Long> primes = new ArrayList(List.of(3l, 7l, 109l, 673l));
        while (supplier.getAsLong() < 673l) {}
        long fifth = supplier.getAsLong();
        primes.add(fifth);
        int index = supplier.getPrimes().indexOf(fifth);
        while (!isRemarkablePrimeGroup(primes)) {
            index++;
            fifth = supplier.getPrimes().get(index);
            primes.set(4, fifth);
        }
        return fifth;
    }

    public boolean isRemarkablePrimeGroup(List<Long> original) {
        List<Long> primeGroup = new ArrayList<>(original);
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

    public static void main(String[] args) {
        PrimeSetFinder finder = new PrimeSetFinder();
        System.out.println(finder.findTheFifthMember());
    }
}
