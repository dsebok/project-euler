package test;

import main.SumSquareSubtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumSquareSubtractorTest {

    private SumSquareSubtractor underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SumSquareSubtractor();
    }

    @Test
    public void testSumOfSquaresShouldWork() {
        //GIVEN in setup
        //WHEN
        long actual = underTest.sumOfSquares(1,3);
        //THEN
        assertEquals(14, actual);
    }

    @Test
    public void testSumOfSquaresShouldWorkWhenStarterIsNotOne() {
        //GIVEN in setup
        //WHEN
        long actual = underTest.sumOfSquares(3,5);
        //THEN
        assertEquals(50, actual);
    }

    @Test
    public void testSquareOfSumShouldWork() {
        //GIVEN in setup
        //WHEN
        long actual = underTest.squareOfSum(1,5);
        //THEN
        assertEquals(225, actual);
    }

    @Test
    public void testSquareOfSumShouldWorkWhenStarterIsNotOne() {
        //GIVEN in setup
        //WHEN
        long actual = underTest.squareOfSum(6,8);
        //THEN
        assertEquals(441, actual);
    }

    @Test
    public void testSubtractSquareSumsShouldWork() {
        //GIVEN in setup
        //WHEN
        long actual = underTest.subtractSquareSums(1,5);
        //THEN
        assertEquals(170, actual);
    }

    @Test
    public void testSubtractSquareSumsShouldWorkWhenStarterIsNotOne() {
        //GIVEN in setup
        //WHEN
        long actual = underTest.subtractSquareSums(4,5);
        //THEN
        assertEquals(40, actual);
    }
}
