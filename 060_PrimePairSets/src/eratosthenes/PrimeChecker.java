package eratosthenes;

import eratosthenes.supplier.PrimeSupplier;

public class PrimeChecker {

    private final PrimeSupplier primeSupplier;

    public PrimeChecker(PrimeSupplier primeSupplier) {
        this.primeSupplier = primeSupplier;
    }

    /**
     * Two primes are remarkable (considering this approach) if
     * both of their concatenated versions are primes as well.
     * E.g.: 7 and 3 -> the numbers 37 and 73 are also primes.
     */
    public boolean areRemarkablePrimes(int a, int b) {
        int ab = concat(a, b);
        if (isPrime(ab)) {
            int ba = concat(b, a);
            return isPrime(ba);
        }
        return false;
    }

    public boolean isPrime(int number) {
        return primeSupplier.contains(number);
    }

    /**
     * This method seems to be ~5% faster than
     * Integer.valueOf("" + n1 + n2)
     * 
     * @param n1 a non-negative integer number
     * @param n2 a non-negative integer number
     * @return the concatenation of n1 and n2
     */
    public int concat(int n1, int n2) {
    	int n3 = n2;
    	while(n3 > 0) {
    		n1 *= 10;
    		n3 /= 10;
    	}
    	return n1 + n2;
    }
}