package test;

import main.PalindromeChecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PalindromeCheckerTest {

    private PalindromeChecker underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PalindromeChecker();
    }

    @Test
    public void testIsPalindromeWorksForSingleDigits() {
        //GIVEN in setup
        //WHEN
        boolean actual1 = underTest.isPalindrome(0);
        boolean actual2 = underTest.isPalindrome(6);
        //THEN
        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    public void testIsPalindromeWorksForEvenDigits() {
        //GIVEN in setup
        //WHEN
        boolean actual1 = underTest.isPalindrome(123321);
        boolean actual2 = underTest.isPalindrome(456754);
        //THEN
        assertTrue(actual1);
        assertFalse(actual2);
    }

    @Test
    public void testIsPalindromeWorksForOddDigits() {
        //GIVEN in setup
        //WHEN
        boolean actual1 = underTest.isPalindrome(1239321);
        boolean actual2 = underTest.isPalindrome(4567651);
        //THEN
        assertTrue(actual1);
        assertFalse(actual2);
    }
}
