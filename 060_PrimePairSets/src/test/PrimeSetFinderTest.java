package test;

import main.PrimeSetFinder;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PrimeSetFinderTest {

    private PrimeSetFinder underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PrimeSetFinder();
    }

    @Test
    public void testIsRemarkablePrimeGroupForWorkingPrimeGroup() {
        //GIVEN
        List<Integer> primes = List.of(3, 7, 109, 673);
        //WHEN
        boolean actual = underTest.isRemarkablePrimeGroup(primes);
        //THEN
        assertTrue(actual);
    }

    @Test
    public void testIsRemarkablePrimeGroupForNotWorkingPrimeGroup() {
        //GIVEN
        List<Integer> primes = List.of(3, 7, 19, 67);
        //WHEN
        boolean actual = underTest.isRemarkablePrimeGroup(primes);
        //THEN
        assertFalse(actual);
    }
}
