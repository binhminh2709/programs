package ru.epatko.taskMenu;

import com.google.common.base.Joiner;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Test;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         04.02.17.
 */
public class TaskManagerTest {
    public static final String LS = System.getProperty("line.separator");

    @Test
    public void whenCreateTaskManagerThenCanGetTasksMenu() {
        MenuManager manager = new MenuManager();
        manager.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        String result = Joiner.on(LS).join(
                "1. TaskOne:",
                "   1.1. TaskOneOne - execute TaskOneOne.",
                "2. TaskTwo:",
                "   2.1. TaskTwoOne:",
                "      2.1.1. TaskTwoOneOne - execute TaskTwoOneOne.",
                "      2.1.2. TaskTwoOneTwo - execute TaskTwoOneTwo.",
                "3. TaskThree - execute TaskThree.",
                ""
        );
        manager.showMenu();
        assertThat(output.toString(), is(result));
    }

    @Test
    public void whenCallTaskOneThenGetResultOfTaskExecution() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("1");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskOneZeroThenGetError() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("1.0");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskOneOneThenGetResultOfTaskExecution() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("1.1");
        assertThat(output.toString(), is(String.format("Executed TaskOneOne.%s", LS)));
    }
    @Test
    public void whenCallTaskOneOneOneThenGetError() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("1.1.1");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskTwoThenGetResultOfTaskExecution() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("2");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskTwoZeroThenGetError() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("2.0");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskTwoOneThenGetResultOfTaskExecution() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("2.1");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskTwoOneZeroThenGetError() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("2.1.0");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskTwoOneOneThenGetResultOfTaskExecution() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("2.1.1");
        assertThat(output.toString(), is(String.format("Executed TaskTwoOneOne.%s", LS)));
    }
    @Test
    public void whenCallTaskTwoOneOneOneThenGetError() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("2.1.1.1");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskTwoOneTwoThenGetResultOfTaskExecution() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("2.1.2");
        assertThat(output.toString(), is(String.format("Executed TaskTwoOneTwo.%s", LS)));
    }
    @Test
    public void whenCallTaskTwoOneTwoOneThenGetError() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("2.1.2.1");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskThreeThenGetResultOfTaskExecution() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("3");
        assertThat(output.toString(), is(String.format("Executed TaskThree.%s", LS)));
    }
    @Test
    public void whenCallTaskThreeThreeThenGetError() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("3.3");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }
    @Test
    public void whenCallTaskFourThenGetError() {
        MenuManager menu = new MenuManager();
        menu.fillMenu();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut (new PrintStream(output));
        menu.executeTask("4");
        assertThat(output.toString(), is(String.format("Incorrect input.%s", LS)));
    }

}
