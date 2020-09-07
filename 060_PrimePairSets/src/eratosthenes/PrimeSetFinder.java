package eratosthenes;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PrimeSetFinder {

    private final List<Set<Integer>> primePairs = new ArrayList<>();
    private final List<Set<Integer>> primeTrios = new ArrayList<>();
    private final List<Set<Integer>> primeQuads = new ArrayList<>();
    private final List<Set<Integer>> primePentas = new ArrayList<>();
    private final PrimesOfEratosthenes primes = new PrimesOfEratosthenes(10000000);

    public List<Set<Integer>> findPentaPrimes() {
        int index = 0;
        while (primePentas.isEmpty()) {
            int current = getNextPrime(index);
            List<Integer> pairsForCurrent = findPairsForCurrent(current);
            saveNewPairs(current, pairsForCurrent);

            if (pairsForCurrent.size() > 1) {
                List<Set<Integer>> duosForCurrent = findBiggerSetsForCurrent(primePairs, pairsForCurrent);
                saveNewSets(current, duosForCurrent, primeTrios);

                if (pairsForCurrent.size() > 2) {
                    List<Set<Integer>> triosForCurrent = findBiggerSetsForCurrent(primeTrios, pairsForCurrent);
                    saveNewSets(current, triosForCurrent, primeQuads);

                    if (!triosForCurrent.isEmpty()) {
                        triosForCurrent.forEach(trio -> {
                            trio.add(current);
                            System.out.println(trio);
                        });
                    }

                    if (pairsForCurrent.size() > 3) {
                        List<Set<Integer>> quadsForCurrent = findBiggerSetsForCurrent(primeQuads, pairsForCurrent);
                        if (!quadsForCurrent.isEmpty()) {
                            saveNewSets(current, quadsForCurrent, primePentas);
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

    private List<Set<Integer>> findBiggerSetsForCurrent(List<Set<Integer>> primeSet, List<Integer> pairsForCurrent) {
        return primeSet.stream()
                .filter(pairsForCurrent::containsAll)
                .collect(Collectors.toList());
    }

    private void saveNewPairs(int current, List<Integer> pairsForCurrent) {
        pairsForCurrent.forEach(prime -> primePairs.add(Set.of(current, prime)));
    }

    private void saveNewSets(int current, List<Set<Integer>> setsForCurrent, List<Set<Integer>> container) {
        setsForCurrent.forEach(set -> {
            Set<Integer> biggerSet = new HashSet<>(set);
            biggerSet.add(current);
            container.add(biggerSet);
        });
    }

    private List<Integer> getLowerPrimes(int current) {
        List<Integer> primes = supplier.getPrimes();
        return primes.stream().takeWhile(prime -> prime < current).collect(Collectors.toList());
    }

    private int getNextPrime(int index) {
        if (supplier.getPrimes().isEmpty()) {
            supplier.getAsInt();
        }
        while (supplier.getPrimes().size() <= index) {
            supplier.getAsInt();
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
        main.PrimeSetFinder finder = new main.PrimeSetFinder();
        System.out.println(finder.findPentaPrimes().get(0));
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }
    //[5701, 8389, 13, 5197, 6733]
    //4482617 milliseconds
}
