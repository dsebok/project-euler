package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(Set.of(2, 3, 5, 7, 11, 13, 17, 19), actual);
    }
}
