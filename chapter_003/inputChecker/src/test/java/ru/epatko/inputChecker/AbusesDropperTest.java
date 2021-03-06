package ru.epatko.inputChecker;

import org.junit.Test;

import java.io.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         13.12.16.
 */
public class AbusesDropperTest {
    @Test
    public void whenSetAbusesThenGetStringWithChangedAbusesToBadWord() throws Exception {

        try (InputStream input = (new ByteArrayInputStream("121 2 31".getBytes()));
                                 OutputStream output = new ByteArrayOutputStream()) {
            AbusesDropper dropper = new AbusesDropper();
            String[] stringArray = new String[] {"2", "3"};
            dropper.dropAbuses(input, output, stringArray);

            assertThat(output.toString(), is("11  1"));
        }



    }

}