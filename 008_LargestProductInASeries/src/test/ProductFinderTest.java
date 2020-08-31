package test;

import main.ProductFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductFinderTest {

    ProductFinder underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ProductFinder();
    }

    @Test
    public void testMultiplyDigitsShouldWorkFor5Digits() {
        //GIVEN in setup
        //WHEN
        long actual = underTest.multiplyDigits("46125");
        //THEN
        Assertions.assertEquals(240L, actual);
    }

    @Test
    public void testMultiplyDigitsShouldWorkForLongResult() {
        //GIVEN in setup
        //WHEN
        long actual = underTest.multiplyDigits("88888888882");
        //THEN
        Assertions.assertEquals(Integer.MAX_VALUE+1L, actual);
    }
}
