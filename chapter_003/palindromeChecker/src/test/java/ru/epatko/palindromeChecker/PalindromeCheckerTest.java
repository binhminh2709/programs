package ru.epatko.palindromeChecker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         22.12.16.
 */
public class PalindromeCheckerTest {
    @Test
    public void whenGivePalindromeThenGetTrue() throws Exception {
        PalindromeChecker checker = new PalindromeChecker();
        assertThat(checker.check("cbcBc"), is(true));
    }
    @Test
    public void whenGivePalindromeNumberThenGetTrue() throws Exception {
        PalindromeChecker <Integer> checker = new PalindromeChecker();
        assertThat(checker.check(12321), is(true));
    }
    @Test
    public void whenGiveNotPalindromeNumberThenGetFalse() throws Exception {
        PalindromeChecker checker = new PalindromeChecker();
        assertThat(checker.check(12325), is(false));
    }
    @Test
    public void whenGiveNotPalindromeThenGetFalse() throws Exception {
        PalindromeChecker <String> checker = new PalindromeChecker();
        assertThat(checker.check("cbcAc"), is(false));
    }
    @Test
    public void whenGiveMoreThenFiveCharsWordThenGetFalse() throws Exception {
        PalindromeChecker checker = new PalindromeChecker();
        assertThat(checker.check("cbcscbc"), is(false));
    }
    @Test
    public void whenGiveLessThenFiveCharsWordThenGetFalse() throws Exception {
        PalindromeChecker checker = new PalindromeChecker();
        assertThat(checker.check("cbc"), is(false));
    }
}