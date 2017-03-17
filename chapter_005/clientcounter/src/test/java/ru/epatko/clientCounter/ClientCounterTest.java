package ru.epatko.clientCounter;

import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.03.17.
 */
public class ClientCounterTest {
    @Test
    public void whenAddUsersThenCanGetResult() {
        ClientCounter cc = new ClientCounter();
        cc.prepareCounter();
        cc.addUser(new User(LocalTime.of(9, 15), LocalTime.of(10, 15)));
        cc.addUser(new User(LocalTime.of(9, 00), LocalTime.of(10, 00)));
        cc.addUser(new User(LocalTime.of(10, 00), LocalTime.of(10, 10)));
        cc.addUser(new User(LocalTime.of(11, 45), LocalTime.of(12, 05)));
        cc.addUser(new User(LocalTime.of(11, 55), LocalTime.of(13, 30)));
        cc.showResult();

        assertThat(cc.getResult().firstEntry().getValue(), is(2));

    }

}