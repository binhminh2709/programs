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
    public void dropAbuses() throws Exception {

        AbusesDropper dropper = new AbusesDropper();
        InputStream input = (new ByteArrayInputStream("121 2 31".getBytes()));
        OutputStream output = new ByteArrayOutputStream();
        String[] stringArray =  new String[] {"2", "3"};
        dropper.dropAbuses(input, output, stringArray);

        assertThat(output.toString(), is ("1\"BAD_WORD\"1 \"BAD_WORD\" \"BAD_WORD\"1"));



    }

}