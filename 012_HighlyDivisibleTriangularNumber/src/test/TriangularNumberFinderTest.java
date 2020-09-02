package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import main.TriangularNumberFinder;
import org.junit.Before;
import org.junit.Test;

public class TriangularNumberFinderTest {

    TriangularNumberFinder underTest;

    @Before
    public void setUp() {
        underTest = new TriangularNumberFinder();
    }

    @Test
    public void testCountDivisorsForSmallNumbers() {
        //GIVEN in setup
        //WHEN
        int actual1 = underTest.countDivisors(2);
        int actual2 = underTest.countDivisors(6);
        int actual3 = underTest.countDivisors(15);
        int actual4 = underTest.countDivisors(28);
        //THEN
        assertEquals(2, actual1);
        assertEquals(4, actual2);
        assertEquals(4, actual3);
        assertEquals(6, actual4);
    }

    @Test
    public void testCountDivisorsForSquareNumbers() {
        //GIVEN in setup
        //WHEN
        int actual1 = underTest.countDivisors(25);
        int actual2 = underTest.countDivisors(36);
        //THEN
        assertEquals(3, actual1);
        assertEquals(9, actual2);
    }
}
