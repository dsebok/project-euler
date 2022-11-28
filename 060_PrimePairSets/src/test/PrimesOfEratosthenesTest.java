package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eratosthenes.supplier.PrimesOfEratosthenes;

public class PrimesOfEratosthenesTest {

    private PrimesOfEratosthenes underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PrimesOfEratosthenes();
    }

    @Test
    public void testGetForSeveralNumbers() {
        //GIVEN in setup
        //WHEN
        Set<Integer> actual = underTest.getPrimes(20);
        //THEN
        assertEquals(8, actual.size());
        assertTrue(actual.contains(2));
        assertTrue(actual.contains(3));
        assertTrue(actual.contains(5));
        assertTrue(actual.contains(7));
        assertTrue(actual.contains(11));
        assertTrue(actual.contains(13));
        assertTrue(actual.contains(17));
        assertTrue(actual.contains(19));
    }
}
