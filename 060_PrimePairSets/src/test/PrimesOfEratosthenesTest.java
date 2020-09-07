package test;

import eratosthenes.PrimesOfEratosthenes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PrimesOfEratosthenesTest {

    private PrimesOfEratosthenes underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PrimesOfEratosthenes(5000);
    }

    @Test
    public void testGetForSeveralNumbers() {
        //GIVEN in setup
        //WHEN
        List<Integer> actual = underTest.get();
        //THEN
        assertEquals(2, actual.get(0));
        assertEquals(31, actual.get(10));
        assertEquals(127, actual.get(30));
        assertEquals(547, actual.get(100));
        assertEquals(1999, actual.get(302));
    }
}
