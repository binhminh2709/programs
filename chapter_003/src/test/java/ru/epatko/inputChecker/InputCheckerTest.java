package ru.epatko.inputChecker;

import ru.epatko.inputChecker.InputChecker;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         11.12.16.
 */
public class InputCheckerTest {

    @Test
    public void wenGiveEvenIntegerThenGetTrue() {
        InputChecker checker = new InputChecker();
        assertThat(checker.isNumber(new ByteArrayInputStream("212".getBytes())), is(true));
    }

    @Test
    public void wenGiveNotEvenIntegerThenGetFalse() {
        InputChecker checker = new InputChecker();
        assertThat(checker.isNumber(new ByteArrayInputStream("11".getBytes())), is(false));
    }

    @Test
    public void wenGiveNotNumberThenGetFalse() throws Exception {
        InputChecker checker = new InputChecker();
        assertThat(checker.isNumber(new ByteArrayInputStream("4f".getBytes())), is(false));
    }

    @Test
    public void wenGiveEmptyStringThenGetFalse() throws Exception {
        InputChecker checker = new InputChecker();
        assertThat(checker.isNumber(new ByteArrayInputStream("".getBytes())), is(false));
    }

    @Test
    public void wenGiveZeroThenGetFalse() throws Exception {
        InputChecker checker = new InputChecker();
        assertThat(checker.isNumber(new ByteArrayInputStream("0".getBytes())), is(false));
    }

    @Test
    public void wenGiveOneThenGetFalse() throws Exception {
        InputChecker checker = new InputChecker();
        assertThat(checker.isNumber(new ByteArrayInputStream("1".getBytes())), is(false));
    }

    @Test
    public void wenGiveNotEvenAndEvenNumbersThenGetFalse() throws Exception {
        InputChecker checker = new InputChecker();
        assertThat(checker.isNumber(new ByteArrayInputStream("21 2".getBytes())), is(false));
    }

    @Test
    public void wenGiveEvenAndNotEvenNumbersThenGetTrue() throws Exception {
        InputChecker checker = new InputChecker();
        assertThat(checker.isNumber(new ByteArrayInputStream("2 11".getBytes())), is(true));
    }
}
