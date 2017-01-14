package ru.epatko.serverSide;

import org.junit.Test;
import ru.epatko.clientSide.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.01.17.
 */
public class StartServerTest {
    @Test
    public void whenStartsClientSettingsThenCanGetIPAddressAndPort() throws Exception {
        ClientSettings cs = new ClientSettings();
        assertThat(cs.getAddress(), is("127.0.0.1"));
        assertThat(cs.getPort(), is(54321));
    }
    @Test
    public void whenStartsServerSettingsThenCanGetIPAddressAndPort() throws Exception {
        ServerSettings ss = new ServerSettings();
        assertThat(ss.getAddress(), is("127.0.0.1"));
        assertThat(ss.getPort(), is(54321));
    }
    @Test
    public void whenSetCommandThenCanGetActionNameAndActionParameter() throws Exception {
        Command command = new Command("11  22");
        assertThat(command.getAction(), is("11"));
        assertThat(command.getParam(), is("22"));
    }

}