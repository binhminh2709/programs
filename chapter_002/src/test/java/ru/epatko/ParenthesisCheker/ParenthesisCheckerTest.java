package ru.epatko.ParenthesisCheker;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         05.12.16.
 */
public class ParenthesisCheckerTest {

    @Test
    public void whenCheckWrongStringThanGetFalse() {
        ParenthesisChecker parChecker = new ParenthesisChecker();
        assertThat(parChecker.check("(+1-s0))"), is(false));
    }

    @Test
    public void whenCheckAnotherWrongStringThanGetFalse() {
        ParenthesisChecker parChecker = new ParenthesisChecker();
        assertThat(parChecker.check(")+1-s0("), is(false));
    }

    @Test
    public void whenCheckAnotherOneWrongStringThanGetFalse() {
        ParenthesisChecker parChecker = new ParenthesisChecker();
        assertThat(parChecker.check("(+1))-((s0)"), is(false));
    }

    @Test
    public void whenCheckRightStringThanGetTrue() {
        ParenthesisChecker parChecker = new ParenthesisChecker();
        assertThat(parChecker.check("(+(1(-s)0))"), is(true));
    }

    @Test
    public void whenCheckStringWithoutParenthesesThanGetTrue() {
        ParenthesisChecker parChecker = new ParenthesisChecker();
        assertThat(parChecker.check("+1-s0"), is(true));
    }



}