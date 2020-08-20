package test;

import main.PrimeSupplier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrimeSupplierTest {

    private PrimeSupplier underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PrimeSupplier();
    }

    @Test
    public void testGetAsLongShouldGivePrimes() {
        //GIVEN in setup
        //WHEN
        long actual1 = underTest.getAsLong();
        long actual2 = underTest.getAsLong();
        long actual3 = underTest.getAsLong();
        long actual4 = underTest.getAsLong();
        long actual5 = underTest.getAsLong();
        //THEN
        assertEquals(2, actual1);
        assertEquals(3, actual2);
        assertEquals(5, actual3);
        assertEquals(7, actual4);
        assertEquals(11, actual5);
    }

}
