package ru.epatko.summator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         09.03.17.
 */
public class SummatorTest {

    @Test
    public void when___Then___() {
        Summator sr = new Summator();
        assertThat(sr.calculateSumm(4), is(10));
    }

}