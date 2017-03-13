package ru.epatko.synchronizing;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         13.03.17.
 */
public class CountThreadTest {

    @Test
    public void whenStartSynchronizedCounterThenGetRightResult() {
        Count count = new Count();
        Thread countThreadA = new Thread(new CountThread(count));
        Thread countThreadB = new Thread(new CountThread(count));

        countThreadA.start();
        countThreadB.start();

        try {
            countThreadA.join();
            countThreadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(count.getCount(), is(2));
    }
}