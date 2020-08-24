package main;

import java.util.*;
import java.util.stream.Collectors;

public class PrimeSetFinder {

    private static final List<Long> EXCEPTIONS = List.of(2L, 5L);

    private final List<Set<Long>> primePairs = new ArrayList<>();
    private final List<Set<Long>> primeTrios = new ArrayList<>();
    private final List<Set<Long>> primeQuads = new ArrayList<>();
    private final List<Set<Long>> primePentas = new ArrayList<>();
    private final PrimeSupplier supplier = new PrimeSupplier();
    private final PrimeChecker checker = new PrimeChecker(supplier);

    public List<Set<Long>> findPentaPrimes() {
        int index = 0;
        while (primePentas.isEmpty()) {
            long current = getNextPrime(index);
            List<Long> pairsForCurrent = findPairsForCurrent(current);
            saveNewPairs(current, pairsForCurrent);

            if (pairsForCurrent.size() > 1) {
                List<Set<Long>> duosForCurrent = findDuosForCurrent(pairsForCurrent);
                saveNewTrios(current, duosForCurrent);

                if (pairsForCurrent.size() > 2) {
                    List<Set<Long>> triosForCurrent = findTriosForCurrent(pairsForCurrent);
                    saveNewQuads(current, triosForCurrent);

                    if (pairsForCurrent.size() > 3) {
                        List<Set<Long>> quadsForCurrent = findQuadsForCurrent(pairsForCurrent);
                        if (!quadsForCurrent.isEmpty()) {
                            saveNewPentas(current, quadsForCurrent);
                        }
                    }
                }
            }
            ++index;
        }
        return primePentas;
    }

    private List<Long> findPairsForCurrent(long current) {
        return getLowerPrimes(current).stream()
                .filter(prime -> areRemarkablePrimes(current, prime))
                .collect(Collectors.toList());
    }

    private List<Set<Long>> findDuosForCurrent(List<Long> pairsForCurrent) {
        return primePairs.stream()
                .filter(pairsForCurrent::containsAll)
                .collect(Collectors.toList());
    }

    private List<Set<Long>> findTriosForCurrent(List<Long> pairsForCurrent) {
        return primeTrios.stream()
                .filter(pairsForCurrent::containsAll)
                .collect(Collectors.toList());
    }

    private List<Set<Long>> findQuadsForCurrent(List<Long> pairsForCurrent) {
        return primeQuads.stream()
                .filter(pairsForCurrent::containsAll)
                .collect(Collectors.toList());
    }

    private void saveNewPairs(long current, List<Long> pairsForCurrent) {
        pairsForCurrent.forEach(prime -> primePairs.add(Set.of(current, prime)));
    }

    private void saveNewTrios(long current, List<Set<Long>> duosForCurrent) {
        duosForCurrent.forEach(pair -> {
            Set<Long> trio = new HashSet<>(pair);
            trio.add(current);
            primeTrios.add(trio);
        });
    }

    private void saveNewQuads(long current, List<Set<Long>> triosForCurrent) {
        triosForCurrent.forEach(trio -> {
            Set<Long> quad = new HashSet<>(trio);
            quad.add(current);
            primeQuads.add(quad);
        });
    }

    private void saveNewPentas(long current, List<Set<Long>> quadsForCurrent) {
        quadsForCurrent.forEach(quad -> {
            Set<Long> penta = new HashSet<>(quad);
            penta.add(current);
            primePentas.add(penta);
        });
    }

    private List<Long> getLowerPrimes(long current) {
        List<Long> primes = supplier.getPrimes();
        return primes.stream().filter(prime -> prime < current).collect(Collectors.toList());
    }

    private Long getNextPrime(int index) {
        if (supplier.getPrimes().isEmpty()) {
            supplier.getAsLong();
        }
        while (supplier.getPrimes().size() <= index) {
            supplier.getAsLong();
        }
        return supplier.getPrimes().get(index);
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
        if (EXCEPTIONS.contains(a) || EXCEPTIONS.contains(b)) {
            return false;
        }
        long ab = Long.parseLong((a.toString() + b.toString()));
        if (checker.isPrime(ab)) {
            long ba = Long.parseLong((b.toString() + a.toString()));
            return checker.isPrime(ba);
        }
        return false;
    }

    public static void main(String[] args) {
        PrimeSetFinder finder = new PrimeSetFinder();
        System.out.println(finder.findPentaPrimes().get(0));
    }
}
