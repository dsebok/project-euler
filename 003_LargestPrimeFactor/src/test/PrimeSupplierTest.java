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
        long actual1 = underTest.get();
        long actual2 = underTest.get();
        long actual3 = underTest.get();
        long actual4 = underTest.get();
        long actual5 = underTest.get();
        long actual6 = underTest.get();
        long actual7 = underTest.get();
        long actual8 = underTest.get();
        long actual9 = underTest.get();
        long actual10 = underTest.get();
        //THEN
        assertEquals(2, actual1);
        assertEquals(3, actual2);
        assertEquals(5, actual3);
        assertEquals(7, actual4);
        assertEquals(11, actual5);
        assertEquals(13, actual6);
        assertEquals(17, actual7);
        assertEquals(19, actual8);
        assertEquals(23, actual9);
        assertEquals(29, actual10);
    }

}
