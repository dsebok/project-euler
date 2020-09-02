package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import main.TriangularSupplier;
import org.junit.Before;
import org.junit.Test;

public class TriangularSupplierTest {

    TriangularSupplier underTest;

    @Before
    public void setUp() {
        underTest = new TriangularSupplier();
    }

    @Test
    public void testGetAsLongShouldWork() {
        //GIVEN in setup
        //WHEN
        long actual1 = underTest.getAsLong();
        long actual2 = underTest.getAsLong();
        long actual3 = underTest.getAsLong();
        long actual4 = underTest.getAsLong();
        long actual5 = underTest.getAsLong();
        //THEN
        assertEquals(1,actual1);
        assertEquals(3,actual2);
        assertEquals(6,actual3);
        assertEquals(10,actual4);
        assertEquals(15,actual5);
    }
}
