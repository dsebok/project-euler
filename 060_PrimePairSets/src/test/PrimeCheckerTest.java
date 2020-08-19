package test;

import main.PrimeChecker;
import main.PrimeSupplier;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrimeCheckerTest {

    private PrimeSupplier supplier;
    private PrimeChecker underTest;

    @BeforeEach
    public void setUp() {
        supplier = new PrimeSupplier();
        underTest = new PrimeChecker(supplier);
    }

    @Test
    public void testIsPrimeShouldReturnTrueWhenGettingPrimes() {
        //GIVEN in setup
        //WHEN
        boolean actual1 = underTest.isPrime(2);
        boolean actual2 = underTest.isPrime(11);
        boolean actual3 = underTest.isPrime(37);
        //THEN
        assertTrue(actual1);
        assertTrue(actual2);
        assertTrue(actual3);
    }

    @Test
    public void testIsPrimeShouldReturnFalseWhenGettingNonPrimes() {
        //GIVEN in setup
        //WHEN
        boolean actual1 = underTest.isPrime(1);
        boolean actual2 = underTest.isPrime(12);
        boolean actual3 = underTest.isPrime(35);
        //THEN
        assertFalse(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
    }
}
