package test;

import main.PrimeSetFinder;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PrimeSetFinderTest {

    private PrimeSetFinder underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PrimeSetFinder();
    }

    @Test
    public void testAreRemarkablePrimesForWorkingPrimes() {
        //GIVEN in setup
        //WHEN
        boolean actual = underTest.areRemarkablePrimes( 3l,7l);
        //THEN
        assertTrue(actual);
    }

    @Test
    public void testAreRemarkablePrimesForNotWorkingPrimes() {
        //GIVEN in setup
        //WHEN
        boolean actual = underTest.areRemarkablePrimes( 2l,7l);
        //THEN
        assertFalse(actual);
    }

    @Test
    public void testIsRemarkablePrimeGroupForWorkingPrimeGroup() {
        //GIVEN
        List<Long> primes = new ArrayList(List.of(3l, 7l, 109l, 673l));
        //WHEN
        boolean actual = underTest.isRemarkablePrimeGroup(primes);
        //THEN
        assertTrue(actual);
    }

    @Test
    public void testIsRemarkablePrimeGroupForNotWorkingPrimeGroup() {
        //GIVEN
        List<Long> primes = new ArrayList(List.of(3l, 7l, 19l, 67l));
        //WHEN
        boolean actual = underTest.isRemarkablePrimeGroup(primes);
        //THEN
        assertFalse(actual);
    }
}
