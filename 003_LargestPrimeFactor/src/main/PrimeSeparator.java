package main;

import java.util.ArrayList;

public class PrimeSeparator {

    public ArrayList<Long> separateToPrimes(long number) {
        ArrayList<Long> result = new ArrayList<>();
        if (number < 1) {
            return result;
        }
        PrimeSupplier supplier = new PrimeSupplier();
        long currentPrime = supplier.getAsInt();
        while (number > 1) {
            while (number % currentPrime == 0) {
                number /= currentPrime;
                result.add(currentPrime);
            }
            currentPrime = supplier.getAsInt();
        }
        return result;
    }

}
