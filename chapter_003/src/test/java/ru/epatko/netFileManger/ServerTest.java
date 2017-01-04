package ru.epatko.netFileManger;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.01.17.
 */
public class ServerTest {
    @Test
    public void whenClientStartsThenCanGetIPAddress() throws Exception {
        Client client = new Client();
        assertThat(client.getAddress(), is("127.0.0.1"));
    }
    @Test
    public void whenClientStartsThenCanGetPort() throws Exception {
        Client client = new Client();
        assertThat(client.getPort(), is(54321));
    }

    @Test
    public void whenServerStartsThenCanGetPortNumber() throws Exception {
        Server server = new Server();
        assertThat(server.getPort(), is(54321));
    }
}