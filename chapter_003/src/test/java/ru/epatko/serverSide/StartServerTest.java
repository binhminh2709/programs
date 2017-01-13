package ru.epatko.serverSide;

import org.junit.Test;
import ru.epatko.clientSide.Client;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.01.17.
 */
public class StartServerTest {
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
        StartServer startServer = new StartServer();
        assertThat(startServer.getPort(), is(54321));
    }

    @Test
    public void whenSetCommandThenCanGetActionNameAndActionParameter() throws Exception {
        Command command = new Command("11  22");
        assertThat(command.getAction(), is("11"));
        assertThat(command.getParam(), is("22"));
    }


}