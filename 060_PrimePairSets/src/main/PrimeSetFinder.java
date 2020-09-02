package main;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class PrimeSetFinder {

    private final List<Set<Integer>> primePairs = new ArrayList<>();
    private final List<Set<Integer>> primeTrios = new ArrayList<>();
    private final List<Set<Integer>> primeQuads = new ArrayList<>();
    private final List<Set<Integer>> primePentas = new ArrayList<>();
    private final PrimeSupplier supplier = new PrimeSupplier();
    private final PrimeChecker checker = new PrimeChecker(supplier);

    public List<Set<Integer>> findPentaPrimes() {
        int index = 0;
        while (primePentas.isEmpty()) {
            int current = getNextPrime(index);
            List<Integer> pairsForCurrent = findPairsForCurrent(current);
            saveNewPairs(current, pairsForCurrent);

            if (pairsForCurrent.size() > 1) {
                List<Set<Integer>> duosForCurrent = findDuosForCurrent(pairsForCurrent);
                saveNewTrios(current, duosForCurrent);

                if (pairsForCurrent.size() > 2) {
                    List<Set<Integer>> triosForCurrent = findTriosForCurrent(pairsForCurrent);
                    saveNewQuads(current, triosForCurrent);

                    if (!triosForCurrent.isEmpty()) {
                        triosForCurrent.forEach(trio -> {
                            trio.add(current);
                            System.out.println(trio);
                        });
                    }

                    if (pairsForCurrent.size() > 3) {
                        List<Set<Integer>> quadsForCurrent = findQuadsForCurrent(pairsForCurrent);
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

    private List<Integer> findPairsForCurrent(int current) {
        return getLowerPrimes(current).stream()
                .filter(prime -> checker.areRemarkablePrimes(current, prime))
                .collect(Collectors.toList());
    }

    private List<Set<Integer>> findDuosForCurrent(List<Integer> pairsForCurrent) {
        return primePairs.stream()
                .filter(pairsForCurrent::containsAll)
                .collect(Collectors.toList());
    }

    private List<Set<Integer>> findTriosForCurrent(List<Integer> pairsForCurrent) {
        return primeTrios.stream()
                .filter(pairsForCurrent::containsAll)
                .collect(Collectors.toList());
    }

    private List<Set<Integer>> findQuadsForCurrent(List<Integer> pairsForCurrent) {
        return primeQuads.stream()
                .filter(pairsForCurrent::containsAll)
                .collect(Collectors.toList());
    }

    private void saveNewPairs(int current, List<Integer> pairsForCurrent) {
        pairsForCurrent.forEach(prime -> primePairs.add(Set.of(current, prime)));
    }

    private void saveNewTrios(int current, List<Set<Integer>> duosForCurrent) {
        duosForCurrent.forEach(pair -> {
            Set<Integer> trio = new HashSet<>(pair);
            trio.add(current);
            primeTrios.add(trio);
        });
    }

    private void saveNewQuads(int current, List<Set<Integer>> triosForCurrent) {
        triosForCurrent.forEach(trio -> {
            Set<Integer> quad = new HashSet<>(trio);
            quad.add(current);
            primeQuads.add(quad);
        });
    }

    private void saveNewPentas(int current, List<Set<Integer>> quadsForCurrent) {
        quadsForCurrent.forEach(quad -> {
            Set<Integer> penta = new HashSet<>(quad);
            penta.add(current);
            primePentas.add(penta);
        });
    }

    private List<Integer> getLowerPrimes(int current) {
        List<Integer> primes = supplier.getPrimes();
        return primes.stream().filter(prime -> prime < current).collect(Collectors.toList());
    }

    private int getNextPrime(int index) {
        if (supplier.getPrimes().isEmpty()) {
            supplier.get();
        }
        while (supplier.getPrimes().size() <= index) {
            supplier.get();
        }
        return supplier.getPrimes().get(index);
    }

    public boolean isRemarkablePrimeGroup(List<Integer> original) {
        List<Integer> primeGroup = new ArrayList<>(original);
        while (primeGroup.size() > 1) {
            int current = primeGroup.remove(0);
            for (int prime : primeGroup) {
                if (!checker.areRemarkablePrimes(current, prime)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        PrimeSetFinder finder = new PrimeSetFinder();
        System.out.println(finder.findPentaPrimes().get(0));
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }
    //[5701, 8389, 13, 5197, 6733]
    //4482617 milliseconds
}
