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
    private final PrimeSupplier supplier = new PrimeSupplier();
    private final PrimeChecker checker = new PrimeChecker(supplier);

    public List<Set<Integer>> findPentaPrimes() {
        int rndCounter = 0;
        while (primePentas.isEmpty()) {
            int current = supplier.getAsInt();
            List<Integer> pairsForCurrent = findPairsForCurrent(current);
            saveNewPairs(current, pairsForCurrent);
            rndCounter++;

            if (pairsForCurrent.size() > 1) {
                List<Set<Integer>> duosForCurrent = findBiggerSetsForCurrent(primePairs, pairsForCurrent);
                saveNewSets(current, duosForCurrent, primeTrios);

                if (pairsForCurrent.size() > 2) {
                    List<Set<Integer>> triosForCurrent = findBiggerSetsForCurrent(primeTrios, pairsForCurrent);
                    saveNewSets(current, triosForCurrent, primeQuads);

                    if (rndCounter % 10 == 0) {
                        System.out.println(current);
                    }

                    if (pairsForCurrent.size() > 3) {
                        List<Set<Integer>> quadsForCurrent = findBiggerSetsForCurrent(primeQuads, pairsForCurrent);
                        if (!quadsForCurrent.isEmpty()) {
                            saveNewSets(current, quadsForCurrent, primePentas);
                        }
                    }
                }
            }
        }
        return primePentas;
    }

    private List<Integer> findPairsForCurrent(int current) {
        return supplier.getLowerPrimes(current).stream()
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

    public static void main(String[] args) {
        Instant start = Instant.now();
        PrimeSetFinder finder = new PrimeSetFinder();
        System.out.println(finder.findPentaPrimes().get(0));
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

}
